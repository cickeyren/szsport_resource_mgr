package com.digitalchina.sport.mgr.resource.controller.discount;

import com.digitalchina.common.data.RtnData;
import com.digitalchina.common.pagination.Page;
import com.digitalchina.common.pagination.PaginationUtils;
import com.digitalchina.common.utils.StringUtil;
import com.digitalchina.common.utils.UUIDUtil;
import com.digitalchina.sport.mgr.resource.model.DiscountConfigure;
import com.digitalchina.sport.mgr.resource.service.DiscountService;
import com.digitalchina.sport.mgr.resource.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单
 */
@Controller
@RequestMapping(value = "/discount")
public class DiscountController {

    public static final Logger logger = LoggerFactory.getLogger(DiscountController.class);
    @Autowired
    private DiscountService discountService;



    /**
     * 进入主页面
     * @param map
     * @return
     */
    @RequestMapping(value = "/discount.html")
    public String discount(ModelMap map) {
        return "discount/discount";
    }

    /**
     * 订单列表
     * @return
     */
    @RequestMapping(value = "/discountList.html")
    public String discountList(@RequestParam(required = false, defaultValue = "10") long pageSize,
                               @RequestParam(required = false, defaultValue = "1") long page,
                               ModelMap map,HttpServletRequest request){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("mainStadiumId", request.getParameter("mainStadiumId"));
        params.put("subStadiumId", request.getParameter("subStadiumId"));
        params.put("status", request.getParameter("status"));
        try {
            int totalSize = discountService.getCountByMap(params);
            Page pagination = PaginationUtils.getPageParam(totalSize, pageSize, page); //计算出分页查询时需要使用的索引
            params.put("startIndex", pagination.getStartIndex());
            params.put("pageSize", pageSize);
            List<Map<String,Object>> list = discountService.getListByMap(params);
            pagination.setUrl(request.getRequestURI());
            map.put("pageModel", pagination);
            map.put("pageSize",String.valueOf(pageSize));
            map.put("page",String.valueOf(page));

            map.put("mainStadiumId", request.getParameter("mainStadiumId"));
            map.put("subStadiumId", request.getParameter("subStadiumId"));
            map.put("status", request.getParameter("status"));
            //回到页面,保留搜索关键字
            map.put("list",list);

            //支付方式
            List<Map<String,String>> payTypeList = discountService.getPayType();
            //所有开放场馆
            List<Map<String,String>> mainStadiumList = discountService.getMainStadium();
            map.put("payType",payTypeList);
            map.put("mainStadium",mainStadiumList);

            return "discount/discount";
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取列表失败");
            map.put("url",request.getRequestURI());
            map.put("exception",e);
            return "error";
        }

    }
    /**
     * 获取子场馆列表
     * @param map
     * @param request
     * @return
     */
    @RequestMapping(value = "/getSubStadiumList.do")
    @ResponseBody
    public RtnData getSubStadiumList(ModelMap map, HttpServletRequest request) {
        try {
            String mainStadiumId = request.getParameter("mainStadiumId");
            //根据选择的主场馆获取子场馆
            List<Map<String,String>> subStadiumList = discountService.getSubStadium(mainStadiumId);
            map.put("subStadiumList",subStadiumList);
            return RtnData.ok(subStadiumList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取子场馆列表失败",e);
            return RtnData.fail("获取子场馆列表失败");
        }
    }

    /**
     * 进入新增页面
     * @return
     */
    @RequestMapping(value = "/add.html")
    public String toAdd(ModelMap map) {
        try {
            //优惠类型
            List<Map<String,String>> discountType = discountService.getDiscountType();
            //支付方式
            List<Map<String,String>> payTypeList = discountService.getPayType();
            //所有开放场馆
            List<Map<String,String>> mainStadiumList = discountService.getMainStadium();
            map.put("discountType",discountType);
            map.put("payType",payTypeList);
            map.put("mainStadium",mainStadiumList);

            return "discount/add";//进入对应的页面
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("进入新增页面失败");
            map.put("exception",e);
            return "error";
        }
    }

    /**
     * 新增
     * @return
     */
    @RequestMapping(value = "/add.do", method = RequestMethod.POST)
    @ResponseBody
    public RtnData add(DiscountConfigure discount, ModelMap map) {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("mainStadiumId",discount.getMainStadiumId());
            params.put("subStadiumId",discount.getSubStadiumId());
            params.put("startTime",discount.getStartTime());
            params.put("endTime",discount.getEndTime());
            if (discountService.getSameCountByParams(params) >0){
                return RtnData.fail("同一场馆有效期内只能有一个优惠策略!");
            }else {
                discount.setId(UUIDUtil.generateUUID());
                discount.setStatus("1");//新增为使用中，超过结束时间为已过期，未到开始时间为未使用
                if (discountService.insert(discount) > 0) {
                    return RtnData.ok("新增优惠配置成功");
                }else {
                    return RtnData.fail("新增优惠配置失败");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("新增优惠配置失败", e);
            return RtnData.fail("新增优惠配置失败");
        }
    }

    /**
     * 作废
     * @return
     */
    @RequestMapping(value = "/delete.do", method = RequestMethod.GET)
    @ResponseBody
    public RtnData delete(@RequestParam String id) {
        try {
            if (discountService.zuofei(id) > 0) {
                return RtnData.ok("优惠配置作废成功");
            }else {
                return RtnData.fail("优惠配置作废失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("优惠配置作废失败", e);
            return RtnData.fail("优惠配置作废失败");
        }

    }


    /**
     * 进入编辑页面
     * @return
     */
    @RequestMapping(value = "/edit.html", method = RequestMethod.GET)
    public String toEdit(ModelMap map,@RequestParam(required = true) String id,HttpServletRequest req){
        try {
            DiscountConfigure discount = discountService.getDiscountById(id);
            //优惠类型
            List<Map<String,String>> discountType = discountService.getDiscountType();
            //支付方式
            List<Map<String,String>> payTypeList = discountService.getPayType();
            //所有开放场馆
            List<Map<String,String>> mainStadiumList = discountService.getMainStadium();
            map.put("discountType",discountType);
            map.put("payType",payTypeList);
            map.put("mainStadium",mainStadiumList);

            map.put("discount", discount);
            return "discount/edit";
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("进入编辑页面失败", e);
            return "error";
        }

    }

    /**
     * 更新
     * @return
     */
    @RequestMapping(value = "/update.do", method = RequestMethod.POST)
    @ResponseBody
    public RtnData update(DiscountConfigure discount, ModelMap map) {
        try {
            if (discountService.update(discount) > 0) {
                return RtnData.ok("修改优惠配置成功");
            }else {
                return RtnData.fail("修改优惠配置失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("修改优惠配置失败", e);
            return RtnData.fail("修改优惠配置");
        }
    }

    /**
     * 进入查看页面
     * @return
     */
    @RequestMapping(value = "/view.html", method = RequestMethod.GET)
    public String toView(ModelMap map,@RequestParam(required = true) String id,HttpServletRequest req){
        try {
            DiscountConfigure discount = discountService.getDetailById(id);

            map.put("discount",discount);
            return "discount/view";
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("进入查看页面失败", e);
            return "error";
        }

    }
}

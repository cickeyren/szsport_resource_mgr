package com.digitalchina.sport.mgr.resource.controller.windowpayment;

import com.digitalchina.common.data.RtnData;
import com.digitalchina.common.pagination.Page;
import com.digitalchina.common.pagination.PaginationUtils;
import com.digitalchina.common.utils.UUIDUtil;
import com.digitalchina.sport.mgr.resource.model.EquipmentModel;
import com.digitalchina.sport.mgr.resource.service.MerchantService;
import com.digitalchina.sport.mgr.resource.service.WindowPaymentService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:wangw
 * @Description:窗口支付管理
 * @Date:Created on 2017/4/5.
 */
@Controller
@RequestMapping(value = "/windowPayment")
public class WindowPaymentController {
    public static final Logger logger = LoggerFactory.getLogger(WindowPaymentController.class);

    @Autowired
    private WindowPaymentService windowPaymentService;
    @Autowired
    private MerchantService merchantService;

    @RequestMapping(value = "/windowPaymentList.html")
    public String equipmentList(@RequestParam(required = false, defaultValue = "10") long pageSize,
                                @RequestParam(required = false, defaultValue = "1") long page,
                                HttpServletRequest request, ModelMap map){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
        paramMap.put("mainStadium", request.getParameter("mainstadium_id"));
        try {
            int totalSize = windowPaymentService.getWindowPaymentTotalCount(paramMap);
            Page pagination = PaginationUtils.getPageParam(totalSize, pageSize, page); //计算出分页查询时需要使用的索引
            pagination.setUrl(request.getRequestURI());
            paramMap.put("pageIndex", pagination.getStartIndex());
            paramMap.put("pageSize", pageSize);
            list = windowPaymentService.getWindowPaymentList(paramMap);
            map.put("windowPaymentList", list);
            map.put("mainStadium", request.getParameter("mainstadium_id"));
            map.put("pageModel", pagination);
            map.put("pageSize",String.valueOf(pageSize));
            map.put("page",String.valueOf(page));
        }catch (Exception e){
            e.printStackTrace();
            logger.error("========查询窗口支付信息失败=========",e);
        }
        return "windowpayment/window_payment_list";
    }

    /**
     * 进入窗口支付新增页面
     * @param map
     * @return
     */
    @RequestMapping(value = "/addWindowPayment.html")
    public String addWindowPayment(ModelMap map, HttpServletRequest request){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        String mainStadiumId = request.getParameter("mainStadiumId");
        List<Map<String,Object>> merchantList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> paymentList = new ArrayList<Map<String, Object>>();
        try {
            paramMap.put("mainStadiumId", mainStadiumId);
            merchantList = merchantService.getMerchantListByParam(paramMap);
            paymentList = windowPaymentService.getAllPaymentList();
        }catch (Exception e){
            e.printStackTrace();
            logger.error("========进入窗口支付新增页面失败=========",e);
        }
        map.put("mainStadium", mainStadiumId);
        map.put("merchantList", merchantList);
        map.put("paymentList", paymentList);
        return "windowpayment/add_window_payment";
    }

    /**
     * 添加窗口支付信息
     * @param mainStadium
     * @param payment
     * @param mainStadium
     * @param remark
     * @return
     */
    @RequestMapping(value = "/addWindowPayment.json", method = RequestMethod.POST)
    @ResponseBody
    public RtnData addWindowPayment(String mainStadium, String merchant, String payment, String remark){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        try {
            String id = UUIDUtil.generateUUID();
            paramMap.put("id", id);
            paramMap.put("mainStadiumId", mainStadium);
            paramMap.put("merchantId", merchant);
            paramMap.put("paymentId", payment);
            paramMap.put("remark", remark);
            if(windowPaymentService.addWindowPayment(paramMap) > 0) {
                return RtnData.ok("添加窗口支付成功");
            }
        } catch (Exception e){
            e.printStackTrace();
            logger.error("========添加窗口支付失败=========",e);
        }
        return RtnData.fail("添加窗口支付失败");
    }

    /**
     * 进入窗口支付编辑页面
     * @param map
     * @return
     */
    @RequestMapping(value = "/editWindowPayment.html")
    public String editWindowPayment(ModelMap map, HttpServletRequest request){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        String id = request.getParameter("id");
        paramMap.put("id", id);
        List<Map<String,Object>> merchantList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> paymentList = new ArrayList<Map<String, Object>>();
        try {
            Map<String, Object> windowPayment = windowPaymentService.getWindowPaymentById(paramMap);
            map.put("windowPayment", windowPayment);
            String mainStadiumId = windowPayment.get("mainStadium").toString();
            paramMap.put("mainStadiumId", mainStadiumId);
            merchantList = merchantService.getMerchantListByParam(paramMap);
            paymentList = windowPaymentService.getAllPaymentList();
        }catch (Exception e){
            e.printStackTrace();
            logger.error("========进入窗口支付编辑页面失败=========",e);
        }
        map.put("merchantList", merchantList);
        map.put("paymentList", paymentList);
        return "windowpayment/edit_window_payment";
    }

    /**
     * 根据id获取窗口支付信息
     * @param id
     * @return
     */
    /*@RequestMapping(value = "/getWindowPaymentById.json", method = RequestMethod.POST)
    @ResponseBody
    public RtnData getWindowPaymentById(String id){
        return RtnData.fail("添加窗口支付失败");
    }*/

    /**
     * 编辑窗口支付信息
     * @param mainStadium
     * @param payment
     * @param mainStadium
     * @param remark
     * @return
     */
    @RequestMapping(value = "/editWindowPayment.json", method = RequestMethod.POST)
    @ResponseBody
    public RtnData editWindowPayment(String id, String mainStadium, String merchant, String payment, String remark){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        try {
            paramMap.put("id", id);
            paramMap.put("mainStadiumId", mainStadium);
            paramMap.put("merchantId", merchant);
            paramMap.put("paymentId", payment);
            paramMap.put("remark", remark);
            if(windowPaymentService.editWindowPayment(paramMap) > 0) {
                return RtnData.ok("编辑窗口支付成功");
            }
        } catch (Exception e){
            e.printStackTrace();
            logger.error("========编辑窗口支付失败=========",e);
        }
        return RtnData.fail("编辑窗口支付失败");
    }

    /**
     * 删除窗口支付信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/delWindowPayment.json", method = RequestMethod.POST)
    @ResponseBody
    public RtnData delWindowPayment(String id){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        try {
            windowPaymentService.delWindowPayment(paramMap);
            return RtnData.ok("删除窗口支付成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("========删除窗口支付失败=========",e);
        }
        return RtnData.fail("删除窗口支付失败");
    }
}

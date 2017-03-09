package com.digitalchina.sport.mgr.resource.controller.merchant;

import com.digitalchina.common.data.RtnData;
import com.digitalchina.common.pagination.Page;
import com.digitalchina.common.pagination.PaginationUtils;
import com.digitalchina.sport.mgr.resource.model.Merchant;
import com.digitalchina.sport.mgr.resource.model.SubStadium;
import com.digitalchina.sport.mgr.resource.model.TimeFrame;
import com.digitalchina.sport.mgr.resource.service.MainStadiumService;
import com.digitalchina.sport.mgr.resource.service.MerchantService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * （一句话描述）
 * 作用：
 *
 * @author 王功文
 * @date 2017/2/27 14:06
 * @see
 */
@Controller
@RequestMapping(value = "/MerchantController")
public class MerchantController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MerchantController.class);

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private MainStadiumService mainStadiumService;

    /**
     * 进入合作商户页面
     *
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/merchant.html")
    public String getAllStadiumList(@RequestParam(required = false, defaultValue = "10") long pageSize,
                                    @RequestParam(required = false, defaultValue = "1") long page,
                                    ModelMap map, HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        String mainstadium_id = request.getParameter("mainstadium_id");//主场馆ID
//        mainstadium_id = "1001";
        try {
            params.put("mainstadium_id",mainstadium_id);
            int totalSize = merchantService.findTotalCount(params);
            Page pagination = PaginationUtils.getPageParam(totalSize, pageSize, page); //计算出分页查询时需要使用的索引
            params.put("startIndex", pagination.getStartIndex());
            params.put("endIndex", pageSize);
            List<Map<String, Object>> merchantList = merchantService.getmerchantList(params);

            pagination.setUrl(request.getRequestURI());
            map.put("pageModel", pagination);
            map.put("pageSize",String.valueOf(pageSize));
            map.put("page",String.valueOf(page));
            map.put("mainstadium_id",mainstadium_id);//将主场馆数据传入到前台页面，用于在新增数据时将值传入到后台
            map.put("merchantList", merchantList);
            return "merchant/merchant";
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========查询合作商户失败=========", e);
            map.put("url", request.getRequestURL());
            map.put("exception", e);
            return "error";
        }
    }

    /**
     * 进入新增页面
     *
     * @param map
     * @param request
     * @return
     */
    @RequestMapping(value = "/add.html", method = RequestMethod.GET)
    public String add(HttpServletRequest request, ModelMap map) {
        Map<String, Object> params = new HashMap<>();
        //获取所有省份列表
        List<Map<String, Object>> provinceLise = mainStadiumService.getAllProvince();
        //获取所有市区数据(北京市区列表)
        params.put("provinceID", "110000");
        List<Map<String, Object>> cityList = mainStadiumService.getAllCity(params);
        //获取所有县区数据（北京市辖区列表）
        params.put("cityID", "110100");
        List<Map<String, Object>> areaList = mainStadiumService.getAllArea(params);
        map.put("provinceLise", provinceLise);
        map.put("cityList", cityList);
        map.put("areaList", areaList);
        //主场馆id
        map.put("mainstadium_id", request.getParameter("mainstadium_id"));

        return "merchant/add_merchant";//进入对应的页面
    }

    /**
     * 新增
     *
     * @param
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/addMerchant.do", method = RequestMethod.POST)
    @ResponseBody
    public RtnData add(Merchant merchant, ModelMap map, @RequestParam(required = true) String mainstadiumId) {
        try {
            merchant.setId(UUID.randomUUID().toString());
            merchant.setMainstadiumId(mainstadiumId);
            if (merchantService.insertMerchant(merchant)>0){
                return RtnData.ok("新增合作商家成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========新增合作商家失败=========", e);
        }
        return RtnData.fail("新增合作商家失败");
    }


    /**
     * 进入编辑页面
     *
     * @param map
     * @param request
     * @return
     */
    @RequestMapping(value = "/edit.html", method = RequestMethod.GET)
    public String editTimeFrame(HttpServletRequest request, ModelMap map) {
        Map<String, Object> param = new HashMap<>();
        //子场馆时段实体
        Merchant merchant = new Merchant();
        merchant.setId(request.getParameter("merchantId"));
        merchant = merchantService.selectMerchantById(merchant);
        //获取所有省份列表
        List<Map<String, Object>> provinceLise = mainStadiumService.getAllProvince();
        //获取所有市区数据(北京市区列表)
        param.put("provinceID",merchant.getProvincialLevel());
        List<Map<String, Object>> cityList = mainStadiumService.getAllCity(param);
        //获取所有县区数据（北京市辖区列表）
        param.put("cityID", merchant.getCityLevel());
        List<Map<String, Object>> areaList = mainStadiumService.getAllArea(param);
        map.put("provinceLise", provinceLise);
        map.put("cityList", cityList);
        map.put("areaList", areaList);

        map.put("merchant", merchant);
        map.put("mainstadium_id",request.getParameter("mainstadium_id"));
        return "merchant/edit_merchant";//进入对应的页面
    }


    /**
     * 更新
     *
     * @param
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/updatemerchant.do", method = RequestMethod.POST)
    @ResponseBody
    public RtnData update(Merchant merchant, ModelMap map) {
        try {
            if (merchantService.updatemerchant(merchant)>0){
                return RtnData.ok("修改合作商户成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error("========修改合作商户失败=========", e);
        }
        return RtnData.fail("修改合作商户失败");
    }


















}

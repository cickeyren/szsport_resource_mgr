package com.digitalchina.sport.mgr.resource.controller.mainstadiummerchant;

import com.digitalchina.common.data.RtnData;
import com.digitalchina.common.pagination.Page;
import com.digitalchina.common.pagination.PaginationUtils;
import com.digitalchina.sport.mgr.resource.service.EquipmentService;
import com.digitalchina.sport.mgr.resource.service.MainStadiumMerchantService;
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

/**
 * @Author:wangw
 * @Description:主场馆合作商家管理
 * @Date:Created in 2017/6/2.
 */
@Controller
@RequestMapping(value = "/mainStadiumMerchant")
public class MainStadiumMerchantController {
    public static final Logger logger = LoggerFactory.getLogger(MainStadiumMerchantController.class);

    @Autowired
    private MainStadiumMerchantService mainStadiumMerchantService;
    @Autowired
    private EquipmentService equipmentService;

    /**
     * 进入合作商户列表页面
     * @param map
     * @param request
     * @return
     */
    @RequestMapping(value = "/merchantList.html")
    public String merchantList(@RequestParam(required = false, defaultValue = "10") long pageSize,
                                      @RequestParam(required = false, defaultValue = "1") long page,
                                      ModelMap map, HttpServletRequest request){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        String mainStadiumId = request.getParameter("mainstadium_id");//主场馆ID
        paramMap.put("mainStadiumId", mainStadiumId);
        try {
            int totalSize = mainStadiumMerchantService.getMerchantTotalCount(paramMap);
            Page pagination = PaginationUtils.getPageParam(totalSize, pageSize, page); //计算出分页查询时需要使用的索引
            pagination.setUrl(request.getRequestURI());
            paramMap.put("pageIndex", pagination.getStartIndex());
            paramMap.put("pageSize", pageSize);
            List<Map<String,Object>> merchantList = mainStadiumMerchantService.getMerchantList(paramMap);
            map.put("merchantList", merchantList);
            map.put("mainStadiumId", mainStadiumId);
            map.put("pageModel", pagination);
            map.put("pageSize",String.valueOf(pageSize));
            map.put("page",String.valueOf(page));
        }catch (Exception e){
            e.printStackTrace();
            logger.error("========进入合作商户账户列表页面=========",e);
        }
        return "mainstadiummerchant/main_stadium_merchant";
    }

    /**
     * 进入合作商家新增页面
     * @return
     */
    @RequestMapping(value = "/addMerchant.html")
    public String addMerchant(ModelMap map, HttpServletRequest request){
        String mainStadiumId = request.getParameter("mainStadiumId");
        map.put("merchantId", request.getParameter("merchantId"));
        map.put("mainStadiumId", mainStadiumId);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        //子场馆信息
        paramMap.put("parent_id", mainStadiumId);
        List<Map<String, Object>> subStadiumList = equipmentService.getSubStadiumByParentId(paramMap);
        map.put("subStadiumList", subStadiumList);
        try{
            //账户信息
            map.put("accountList", mainStadiumMerchantService.getAllValidUser());
        }catch (Exception e){
            e.printStackTrace();
            logger.error("========进入合作商户账户新增页面=========",e);
        }
        return "merchantaccount/add_merchant_account";
    }

    /**
     * 添加合作商家账户信息
     * @return
     */
    @RequestMapping(value = "/addMerchant.json", method = RequestMethod.POST)
    @ResponseBody
    public RtnData addMerchant(String mainStadiumId,String merchantId, String loginId, String subStadiumId, HttpServletRequest request){
        try {
            boolean isExist = mainStadiumMerchantService.verifyMerchantAccount(mainStadiumId, merchantId, loginId);
            if(isExist){
                return RtnData.ok("账户已被添加");
            }else{
                int isSuccess = mainStadiumMerchantService.addMerchantAccount(mainStadiumId, merchantId, loginId, subStadiumId);
                if (isSuccess > 0){
                    return RtnData.ok("添加合作商家账户信息成功");
                }
            }
        } catch (Exception e){
            e.printStackTrace();
            logger.error("========添加合作商家账户信息失败=========",e);
        }
        return RtnData.fail("添加合作商家账户信息失败");
    }

    /**
     * 删除合作商户账户信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/delMerchantAccount.json", method=RequestMethod.POST)
    @ResponseBody
    public RtnData delMerchantAccount(String id){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        try {
            mainStadiumMerchantService.delMerchantAccount(paramMap);
            return RtnData.ok("删除合作商户账户信息成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("========删除合作商户账户信息失败=========",e);
        }
        return RtnData.fail("删除合作商户账户信息失败");
    }
}

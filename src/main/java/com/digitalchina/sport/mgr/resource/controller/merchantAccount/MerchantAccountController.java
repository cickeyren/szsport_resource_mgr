package com.digitalchina.sport.mgr.resource.controller.merchantAccount;

import com.digitalchina.common.data.RtnData;
import com.digitalchina.common.pagination.Page;
import com.digitalchina.common.pagination.PaginationUtils;
import com.digitalchina.common.utils.DateUtil;
import com.digitalchina.sport.mgr.resource.dao.FieldMapper;
import com.digitalchina.sport.mgr.resource.dao.TimeIntervalMapper;
import com.digitalchina.sport.mgr.resource.model.SiteTicketBasicInfoModel;
import com.digitalchina.sport.mgr.resource.model.SiteTicketStrategyInfoModel;
import com.digitalchina.sport.mgr.resource.service.*;
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
import java.util.*;

/**
 * @Author:wangw
 * @Description:合作商家账户管理
 * @Date:Created in 2017/4/18.
 */
@Controller
@RequestMapping(value = "/merchantAccount")
public class MerchantAccountController {
    public static final Logger logger = LoggerFactory.getLogger(MerchantAccountController.class);

    @Autowired
    private MerchantAccountService merchantAccountService;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private MerchantService merchantService;

    /**
     * 进入合作商户账户列表页面
     * @param map
     * @param request
     * @return
     */
    @RequestMapping(value = "/merchantAccountList.html")
    public String merchantAccountList(@RequestParam(required = false, defaultValue = "10") long pageSize,
                                      @RequestParam(required = false, defaultValue = "1") long page,
                                      ModelMap map, HttpServletRequest request){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        String mainStadiumId = request.getParameter("mainstadium_id");//主场馆ID
        paramMap.put("mainStadiumId", mainStadiumId);
        String merchantId = request.getParameter("merchantId");//合作商户ID
        paramMap.put("merchantId", merchantId);
        try {
            int totalSize = merchantAccountService.getMerchantAccountTotalCount(paramMap);
            Page pagination = PaginationUtils.getPageParam(totalSize, pageSize, page); //计算出分页查询时需要使用的索引
            pagination.setUrl(request.getRequestURI());
            paramMap.put("pageIndex", pagination.getStartIndex());
            paramMap.put("pageSize", pageSize);
            List<Map<String,Object>> merchantAccountList = merchantAccountService.getMerchantAccountList(paramMap);
            map.put("merchantAccountList", merchantAccountList);
            map.put("mainStadiumId", mainStadiumId);
            map.put("merchantId", merchantId);
            map.put("pageModel", pagination);
            map.put("pageSize",String.valueOf(pageSize));
            map.put("page",String.valueOf(page));
        }catch (Exception e){
            e.printStackTrace();
            logger.error("========进入合作商户账户列表页面=========",e);
        }
        return "merchantaccount/merchant_account_list";
    }

    /**
     * 进入合作商家账户新增页面
     * @return
     */
    @RequestMapping(value = "/addMerchantAccount.html")
    public String addMerchantAccount(ModelMap map, HttpServletRequest request){
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
            map.put("accountList", merchantAccountService.getAllValidUser());
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
    @RequestMapping(value = "/addMerchantAccount.json", method = RequestMethod.POST)
    @ResponseBody
    public RtnData addMerchantAccount(String mainStadiumId,String merchantId, String loginId, String subStadiumId, HttpServletRequest request){
        try {
            boolean isExist = merchantAccountService.verifyMerchantAccount(mainStadiumId, merchantId, loginId);
            if(isExist){
                return RtnData.ok("账户已被添加");
            }else{
                int isSuccess = merchantAccountService.addMerchantAccount(mainStadiumId, merchantId, loginId, subStadiumId);
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
     * 进入合作商家账户信息编辑页面
     * @param map
     * @return
     */
    @RequestMapping(value = "/editMerchantAccount.html")
    public String editMerchantAccount(ModelMap map, HttpServletRequest request){
        String id = request.getParameter("id");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        try {
            //账户信息
            Map<String, Object> accountMap =  merchantAccountService.getMerchantAccountById(paramMap);
            map.put("merchantAccount", accountMap);
            String mainStadiumId = accountMap.get("mainStadiumId").toString();
            //子场馆信息
            paramMap.put("parent_id", mainStadiumId);
            List<Map<String, Object>> subStadiumList = equipmentService.getSubStadiumByParentId(paramMap);
            map.put("subStadiumList", subStadiumList);
            map.put("merchantId", accountMap.get("merchantId"));
            map.put("mainStadiumId", mainStadiumId);
            return "merchantaccount/edit_merchant_account";
        }catch (Exception e){
            e.printStackTrace();
            logger.error("========进入合作商家账户信息编辑页面=========",e);
            map.put("url",request.getRequestURL());
            map.put("exception",e);
            return "error";
        }
    }

    /**
     * 修改合作商家账户信息
     * @return
     */
    @RequestMapping(value = "/editMerchantAccount.json", method = RequestMethod.POST)
    @ResponseBody
    public RtnData editMerchantAccount(String id, String mainStadiumId, String merchantId, String subStadiumId){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        try {
            paramMap.put("id", id);
            paramMap.put("mainStadiumId", mainStadiumId);
            paramMap.put("merchantId", merchantId);
            paramMap.put("subStadiumId", subStadiumId);
            if(merchantAccountService.editMerchantAccount(paramMap) > 0) {
                return RtnData.ok("编辑合作商户账户信息成功");
            }
        } catch (Exception e){
            e.printStackTrace();
            logger.error("========编辑合作商户账户信息失败=========",e);
        }
        return RtnData.fail("编辑合作商户账户信息失败");
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
            merchantAccountService.delMerchantAccount(paramMap);
            return RtnData.ok("删除合作商户账户信息成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("========删除合作商户账户信息失败=========",e);
        }
        return RtnData.fail("删除合作商户账户信息失败");
    }
}

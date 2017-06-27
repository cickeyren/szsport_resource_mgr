package com.digitalchina.sport.mgr.resource.controller.role;

import com.digitalchina.common.data.RtnData;
import com.digitalchina.common.pagination.Page;
import com.digitalchina.common.pagination.PaginationUtils;
import com.digitalchina.sport.mgr.resource.service.EquipmentService;
import com.digitalchina.sport.mgr.resource.service.MerchantAccountService;
import com.digitalchina.sport.mgr.resource.service.MerchantService;
import com.digitalchina.sport.mgr.resource.service.RoleService;
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
 * @Description:合作商家账户权限管理
 * @Date:Created in 2017/6/23.
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController {
    public static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private MerchantAccountService merchantAccountService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private MerchantService merchantService;

    /**
     * 进入合作商户账户权限页面
     * @param map
     * @param request
     * @return
     */
    @RequestMapping(value = "/role.html")
    public String role(ModelMap map, HttpServletRequest request){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        String account = request.getParameter("account");//合作商户ID
        paramMap.put("account", account);
        try {
            List<Map<String, Object>> stadiumList = roleService.getMainStadiumListByMerchant(paramMap);
            map.put("stadiumList", stadiumList);
            String mainStadiumId = request.getParameter("mainStadiumId");//主场馆ID
            map.put("mainStadiumId", mainStadiumId);
            String merchantId = request.getParameter("merchantId");//合作商户ID
            map.put("merchantId", merchantId);
            String loginId = request.getParameter("loginId");//账户ID
            map.put("loginId", loginId);
            map.put("account", account);//账户
        }catch (Exception e){
            e.printStackTrace();
            logger.error("========进入合作商户账户权限管理页面失败=========",e);
        }
        return "role/role";
    }

    /**
     * 添加账户场馆权限信息
     * @return
     */
    @RequestMapping(value = "/addStadiumRoleInfo.json", method = RequestMethod.POST)
    @ResponseBody
    public RtnData addStadiumRoleInfo(HttpServletRequest request){
        try {
            int count = roleService.addStadiumRoleInfo(request);
            if(count > 0) {
                return RtnData.ok("添加合作商家账户权限信息成功");
            }
        } catch (Exception e){
            e.printStackTrace();
            logger.error("========添加合作商家账户权限信息失败=========",e);
        }
        return RtnData.fail("添加合作商家账户权限信息失败");
    }
}

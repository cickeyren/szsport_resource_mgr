package com.digitalchina.sport.mgr.resource.controller.merchantuser;

import com.digitalchina.common.data.RtnData;
import com.digitalchina.common.pagination.Page;
import com.digitalchina.common.pagination.PaginationUtils;
import com.digitalchina.common.utils.StringUtils;
import com.digitalchina.sport.mgr.resource.model.MerchantPayAccount;
import com.digitalchina.sport.mgr.resource.model.MerchantUser;
import com.digitalchina.sport.mgr.resource.service.MerchantUserService;
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
 * （一句话描述）
 * 作用：
 *
 * @author 王功文
 * @date 2017/3/2 16:16
 * @see
 */
@Controller
@RequestMapping(value = "MerchantUserController")
public class MerchantUserController {
    private final static Logger LOGGER = LoggerFactory.getLogger(MerchantUserController.class);
    @Autowired
    private MerchantUserService merchantUserService;

    /**
     * 进入合作商户支付页面
     *
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/merchantuser.html")
    public String getAllStadiumList(@RequestParam(required = false, defaultValue = "10") long pageSize,
                                    @RequestParam(required = false, defaultValue = "1") long page,
                                    ModelMap map, HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        String mainstadium_id = request.getParameter("mainstadium_id");//主场馆ID
        String merchantId = request.getParameter("merchantId");//合作商户ID
        if (StringUtils.isNotBlank(merchantId)) {
            params.put("merchant_id", merchantId);
        } else {
            params.put("merchant_id", "00001");
        }
        try {
            int totalSize = merchantUserService.findTotalCount(params);
            Page pagination = PaginationUtils.getPageParam(totalSize, pageSize, page); //计算出分页查询时需要使用的索引
            params.put("startIndex", pagination.getStartIndex());
            params.put("endIndex", pageSize);
            List<Map<String, Object>> merchantuser = merchantUserService.getmerchantuser(params);

            pagination.setUrl(request.getRequestURI());
            map.put("pageModel", pagination);
            map.put("pageSize", String.valueOf(pageSize));
            map.put("page", String.valueOf(page));
            map.put("merchant_id", merchantId);//合作商户id
            map.put("mainstadium_id", mainstadium_id);//主场馆id
            map.put("merchantuser", merchantuser);
            return "merchantuser/merchantuser";
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========查询账户信息失败=========", e);
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
     *
     * @return
     */
    @RequestMapping(value = "/add.html", method = RequestMethod.GET)
    public String add(HttpServletRequest request, ModelMap map) {

        //主场馆id
        map.put("mainstadium_id", request.getParameter("mainstadium_id"));
        //合作商户id
        map.put("merchant_id", request.getParameter("merchant_id"));

        return "merchantuser/add_merchantuser";//进入对应的页面
    }

    /**
     * 新增數據
     *
     * @param
     * @param map
     * @param
     *
     * @return
     */
    @RequestMapping(value = "/add.do", method = RequestMethod.POST)
    @ResponseBody
    public RtnData add(MerchantUser MerchantUser, ModelMap map) {
        try {
            MerchantUser.setId(UUID.randomUUID().toString());
            MerchantUser.setCreateTime(new Date());
            if (merchantUserService.insert(MerchantUser) > 0) {
                return RtnData.ok("新增合作商户账户成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========新增合作商户账户失败=========", e);
        }

        return RtnData.fail("新增商户账户失败");
    }

    /**
     * 进入编辑页面
     * @param request
     * @param map
     * @return
     */
    @RequestMapping(value = "/edit.html", method = RequestMethod.GET)
    public String edit(HttpServletRequest request, ModelMap map) {
        String id = request.getParameter("id");
        MerchantUser merchantUser = new MerchantUser();
        merchantUser.setId(id);

        MerchantUser merchantUser1 = merchantUserService.selectByID(merchantUser);
        map.put("merchantUser", merchantUser1);
        map.put("mainstadium_id",request.getParameter("mainstadium_id"));
        map.put("merchantId",request.getParameter("merchantId"));
        map.put("id",id);
        return "merchantuser/edit_merchantuser";//进入对应的页面
    }

    /**
     * 更新数据
     * @param
     * @param map
     * @return
     */
    @RequestMapping(value = "/update.do", method = RequestMethod.POST)
    @ResponseBody
    public RtnData update(MerchantUser merchantUser, ModelMap map) {
        try {
            merchantUser.setUpdateTime(new Date());
            if (merchantUserService.updateByID(merchantUser)>0){
                return RtnData.ok("修改账户信息成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error("========修改账户信息失败=========", e);
        }
        return RtnData.fail("修改账户信息失败");
    }


}




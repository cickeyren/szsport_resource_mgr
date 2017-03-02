package com.digitalchina.sport.mgr.resource.controller.merchant_pay;

import com.digitalchina.common.data.RtnData;
import com.digitalchina.common.pagination.Page;
import com.digitalchina.common.pagination.PaginationUtils;
import com.digitalchina.common.utils.StringUtils;
import com.digitalchina.sport.mgr.resource.model.Merchant;
import com.digitalchina.sport.mgr.resource.model.MerchantPayAccount;
import com.digitalchina.sport.mgr.resource.service.MerchantPayAccountService;
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
 * @date 2017/2/27 14:06
 * @see
 */
@Controller
@RequestMapping(value = "/MerchantPayController")
public class MerchantPayController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MerchantPayController.class);

    @Autowired
    private MerchantPayAccountService merchantPayAccountService;

    /**
     * 进入合作商户支付页面
     *
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/merchant_pay.html")
    public String getAllStadiumList(@RequestParam(required = false, defaultValue = "10") long pageSize,
                                    @RequestParam(required = false, defaultValue = "1") long page,
                                    ModelMap map, HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        String mainstadium_id = request.getParameter("mainstadium_id");//主场馆ID
        String merchantId = request.getParameter("merchantId");//合作商户ID
        if (StringUtils.isNotBlank(merchantId)) {
            params.put("merchant_id", merchantId);
        }else {
            params.put("merchant_id", "00001");
        }
        try {
            int totalSize = merchantPayAccountService.findTotalCount(params);
            Page pagination = PaginationUtils.getPageParam(totalSize, pageSize, page); //计算出分页查询时需要使用的索引
            params.put("startIndex", pagination.getStartIndex());
            params.put("endIndex", pageSize);
            List<Map<String, Object>> merchant_payList = merchantPayAccountService.getmerchant_payList(params);

            pagination.setUrl(request.getRequestURI());
            map.put("pageModel", pagination);
            map.put("pageSize", String.valueOf(pageSize));
            map.put("page", String.valueOf(page));
            map.put("merchant_id", merchantId);//合作商户id
            map.put("mainstadium_id", mainstadium_id);//主场馆id
            map.put("merchant_payList", merchant_payList);
            return "merchant_pay/merchant_pay";
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========查询合作商户支付信息失败=========", e);
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

        //主场馆id
        map.put("mainstadium_id", request.getParameter("mainstadium_id"));
        //合作商户id
        map.put("merchantId", request.getParameter("merchantId"));

        return "merchant_pay/add_merchant_pay";//进入对应的页面
    }

    /**
     * 新增
     *
     * @param
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/add.do", method = RequestMethod.POST)
    @ResponseBody
    public RtnData add(MerchantPayAccount merchantPayAccount, ModelMap map, @RequestParam(required = false) String mainstadiumId) {
        Map<String,Object> params = new HashMap<>();
        params.put("merchantId",merchantPayAccount.getMerchantId());
        params.put("payWay",merchantPayAccount.getPayWay());
        Integer count  = merchantPayAccountService.selectByParams(params);
        if (count>0){
            return RtnData.ok("该合作方式中已存在支付宝账号");
        }else {
            try {
                merchantPayAccount.setId(UUID.randomUUID().toString());
                merchantPayAccount.setCreateTime(new Date());
                if (merchantPayAccountService.insert(merchantPayAccount)>0){
                    return RtnData.ok("新增支付宝账户成功");
                }
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("========新增支付宝账户失败=========", e);
            }
        }
        return RtnData.fail("新增支付宝账户失败");
    }


    /**
     * 进入编辑页面
     * @param request
     * @param map
     * @return
     */
    @RequestMapping(value = "/edit.html", method = RequestMethod.GET)
    public String editMerchantPay(HttpServletRequest request, ModelMap map) {
        String id = request.getParameter("id");
        MerchantPayAccount merchantPayAccount = new MerchantPayAccount();
        merchantPayAccount.setId(id);

        MerchantPayAccount merchantPayAccount1 = merchantPayAccountService.getmerchant_payByparams(merchantPayAccount);
        map.put("merchantPayAccount", merchantPayAccount1);
        map.put("mainstadium_id",request.getParameter("mainstadium_id"));
        map.put("merchantId",request.getParameter("merchantId"));
        map.put("id",id);
        return "merchant_pay/edit_merchant_pay";//进入对应的页面
    }




    /**
     * 更新
     *
     * @param
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/updateByzhifubao.do", method = RequestMethod.POST)
    @ResponseBody
    public RtnData update(MerchantPayAccount merchantPayAccount, ModelMap map) {
        try {
            if (merchantPayAccountService.update(merchantPayAccount)>0){
                return RtnData.ok("修改支付方式信息成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error("========修改支付方式信息失败=========", e);
        }
        return RtnData.fail("修改支付方式信息失败");
    }

}

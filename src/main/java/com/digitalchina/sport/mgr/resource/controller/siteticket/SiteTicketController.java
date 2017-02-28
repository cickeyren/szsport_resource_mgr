package com.digitalchina.sport.mgr.resource.controller.siteticket;

import com.digitalchina.common.data.RtnData;
import com.digitalchina.common.utils.UUIDUtil;
import com.digitalchina.sport.mgr.resource.model.SiteTicketBasicInfoModel;
import com.digitalchina.sport.mgr.resource.service.MainStadiumService;
import com.digitalchina.sport.mgr.resource.service.SiteTicketService;
import com.digitalchina.sport.mgr.resource.service.YearStrategyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author:wangw
 * @Description:场地票管理
 * @Date:Created in 2017/2/23.
 */
@Controller
@RequestMapping(value = "/siteTicket")
public class SiteTicketController {
    public static final Logger logger = LoggerFactory.getLogger(SiteTicketController.class);

    @Autowired
    private SiteTicketService siteTicketService;
    @Autowired
    private MainStadiumService mainStadiumService;
    @Autowired
    private YearStrategyService yearStrategyService;

    /**
     * 进入场地票新增页面
     * @return
     */
    @RequestMapping(value = "/addSiteTicket.html")
    public String addSiteTicket(ModelMap map){
        //主场馆信息
        map.put("mainStadiumList", mainStadiumService.findStadiumModel());
        //合作商信息
        map.put("merchantList", yearStrategyService.getAllMerchant());
        return "siteticket/add_site_ticket";
    }

    /**
     * 添加场地票基本信息
     * @return
     */
    @RequestMapping(value = "/addSiteTicketBasicInfo.json", method = RequestMethod.POST)
    @ResponseBody
    public RtnData addSiteTicketBasicInfo(SiteTicketBasicInfoModel siteTicketBasicInfoModel, HttpServletRequest request){
        try {
            boolean isSuccess = siteTicketService.addSiteTicketBasicInfo(siteTicketBasicInfoModel, request);
            if (isSuccess){
                return RtnData.ok("添加场地票基本信息成功");
            }
        } catch (Exception e){
            e.printStackTrace();
            logger.error("========添加场地票基本信息失败=========",e);
        }
        return RtnData.fail("添加场地票基本信息失败");
    }
}

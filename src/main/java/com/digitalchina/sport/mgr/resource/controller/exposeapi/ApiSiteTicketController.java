package com.digitalchina.sport.mgr.resource.controller.exposeapi;

import com.digitalchina.common.data.RtnData;
import com.digitalchina.sport.mgr.resource.service.SiteTicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author:wangw
 * @Description:场地票管理
 * @Date:Created in 2017/3/14
 */
@Controller
@RequestMapping(value = "/siteTicket/api")
public class ApiSiteTicketController {
    public static final Logger logger = LoggerFactory.getLogger(ApiSiteTicketController.class);

    @Autowired
    private SiteTicketService siteTicketService;

    @RequestMapping(value = "/getSiteTicketInfoToOrder.json")
    @ResponseBody
    public RtnData getSiteTicketInfoToOrder(@RequestParam(required = true) String ticketId){
        try {
            Map<String,Object> paramMap = new HashMap<String,Object>();
            paramMap.put("ticketId", ticketId);
            return RtnData.ok(siteTicketService.getSiteTicketInfoToOrder(paramMap));
        }catch (Exception e){
            e.printStackTrace();
            logger.error("========根据场地票ID获取场地票详情失败=========",e);
        }
        return RtnData.fail("根据场地票ID获取场地票详情失败");
    }
}

package com.digitalchina.sport.mgr.resource.controller.siteticket;

import com.digitalchina.common.data.RtnData;
import com.digitalchina.common.utils.UUIDUtil;
import com.digitalchina.sport.mgr.resource.dao.FieldMapper;
import com.digitalchina.sport.mgr.resource.dao.TimeIntervalMapper;
import com.digitalchina.sport.mgr.resource.model.SiteTicketBasicInfoModel;
import com.digitalchina.sport.mgr.resource.model.SiteTicketStrategyInfoModel;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private FieldMapper fieldMapper;
    @Autowired
    private TimeIntervalMapper timeInteralMapper;

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

    /**
     * 修改场地票基本信息
     * @param siteTicketBasicInfoModel
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateSiteTicket.json", method = RequestMethod.POST)
    @ResponseBody
    public RtnData updateSiteTicketBasicInfo(SiteTicketBasicInfoModel siteTicketBasicInfoModel, HttpServletRequest request){
        try {
            int num = siteTicketService.updateSiteTicketBasicInfo(siteTicketBasicInfoModel);
            if(num > 0) {
                return RtnData.ok("修改场地票基本信息成功");
            }
        } catch (Exception e){
            e.printStackTrace();
            logger.error("========修改场地票基本信息失败=========",e);
        }
        return RtnData.fail("修改场地票基本信息失败");
    }

    /**
     * 进入场地票编辑页面
     * @param map
     * @return
     */
    @RequestMapping(value = "/editSiteTicket.html")
    public String editSiteTicket(ModelMap map, HttpServletRequest request){
        //主场馆信息
        map.put("mainStadiumList", mainStadiumService.findStadiumModel());
        //合作商信息
        map.put("merchantList", yearStrategyService.getAllMerchant());
        //场地票基本信息
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id",request.getParameter("ticketId"));
        map.put("siteTicket", siteTicketService.getSiteTicketInfoByParam(paramMap));
        return "siteticket/edit_site_ticket";
    }

    /**
     * 编辑场地票基本信息
     * @return
     */
    @RequestMapping(value = "/editSiteTicketBasicInfo.json", method = RequestMethod.POST)
    @ResponseBody
    public RtnData editSiteTicketBasicInfo(SiteTicketBasicInfoModel siteTicketBasicInfoModel, HttpServletRequest request){
        try {
            boolean isSuccess = siteTicketService.editSiteTicketBasicInfo(siteTicketBasicInfoModel, request);
            if (isSuccess){
                return RtnData.ok("编辑场地票基本信息成功");
            }
        } catch (Exception e){
            e.printStackTrace();
            logger.error("========编辑场地票基本信息失败=========",e);
        }
        return RtnData.fail("编辑场地票基本信息失败");
    }

    /**
     * 进入场地票价格策略新增页面
     * @return
     */
    @RequestMapping(value = "/addSiteTicketStrategy.html")
    public String addSiteTicketStrategy(ModelMap map,HttpServletRequest request){
        //子场馆中场地列表
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("stadium_id",request.getParameter("subStadium"));
        map.put("fieldList", fieldMapper.getAllByid(paramMap));
        //子场馆中时段策略列表
        List<Map<String, Object>> timeFrame = timeInteralMapper.getTimeIntervalByStadiumid(paramMap);
        map.put("timeFrameList",timeFrame);
        //时段策略中的时间列表
        paramMap.put("time_code", timeFrame.get(0).get("id"));
        map.put("timeIntervalList", timeInteralMapper.getTimeIntervalByTimecode(paramMap));
        map.put("ticketId", request.getParameter("ticketId"));
        return "siteticket/add_site_strategy";
    }

    /**
     * 新增场地票价格策略
     * @param siteTicketStrategyInfoModel
     * @param request
     * @return
     */
    @RequestMapping(value = "/addStrategyInfo", method=RequestMethod.POST)
    @ResponseBody
    public RtnData addStrategyInfo(SiteTicketStrategyInfoModel siteTicketStrategyInfoModel, HttpServletRequest request){
        try{
            boolean isSuccess = siteTicketService.addStrategyInfo(siteTicketStrategyInfoModel, request);
            if(isSuccess){
                return RtnData.ok("新增场地票策略信息成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("========新增场地票策略信息失败=========",e);
        }
        return RtnData.fail("新增场地票策略信息失败");
    }
}

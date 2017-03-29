package com.digitalchina.sport.mgr.resource.controller.siteticket;

import com.digitalchina.common.data.RtnData;
import com.digitalchina.common.pagination.Page;
import com.digitalchina.common.pagination.PaginationUtils;
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
import org.springframework.web.bind.annotation.RequestParam;
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
    public String editSiteTicket(@RequestParam(required = false, defaultValue = "10") long pageSize,
                                 @RequestParam(required = false, defaultValue = "1") long page,
                                 ModelMap map, HttpServletRequest request){
        //主场馆信息
        map.put("mainStadiumList", mainStadiumService.findStadiumModel());
        //合作商信息
        map.put("merchantList", yearStrategyService.getAllMerchant());
        //场地票基本信息
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id",request.getParameter("ticketId"));
        map.put("siteTicket", siteTicketService.getSiteTicketInfoByParam(paramMap));
        //场地票策略信息列表
        paramMap.put("ticketId",request.getParameter("ticketId"));
        try {
            int totalSize = siteTicketService.getSiteTicketStrategyCount(paramMap);
            Page pagination = PaginationUtils.getPageParam(totalSize, pageSize, page); //计算出分页查询时需要使用的索引
            pagination.setUrl(request.getRequestURI());
            paramMap.put("pageIndex", pagination.getStartIndex());
            paramMap.put("pageSize", pageSize);
            map.put("strategyList",siteTicketService.getSiteTicketStrategyInfoList(paramMap));
            map.put("pageModel", pagination);
            map.put("pageSize",String.valueOf(pageSize));
            map.put("page",String.valueOf(page));
            return "siteticket/edit_site_ticket";
        }catch (Exception e){
            e.printStackTrace();
            logger.error("========查询年卡策略列表失败=========",e);
            map.put("url",request.getRequestURL());
            map.put("exception",e);
            return "error";
        }
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
        map.put("subStadium",request.getParameter("subStadium"));
        //子场馆中场地列表
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("stadium_id",request.getParameter("subStadium"));
        map.put("fieldList", fieldMapper.getAllByid(paramMap));
        //子场馆中正常状态时段策略列表
        List<Map<String, Object>> timeFrame = timeInteralMapper.getNormalTimeIntervalByStadiumid(paramMap);
        map.put("timeFrameList",timeFrame);
        //时段策略中的时间列表
        paramMap.put("time_code", timeFrame.get(0).get("id"));
        map.put("timeIntervalList", timeInteralMapper.getTimeIntervalByTimecode(paramMap));
        map.put("ticketId", request.getParameter("ticketId"));
        return "siteticket/add_site_strategy";
    }

    /**
     * 根据timeCode查询时段信息
     * @param timeCode
     * @return
     */
    @RequestMapping(value = "/getTimeIntervalByTimeCode.json",method = RequestMethod.POST)
    @ResponseBody
    public RtnData getTimeIntervalByTimeCode(String timeCode){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("time_code", timeCode);
        try {
            return RtnData.ok(timeInteralMapper.getTimeIntervalByTimecode(paramMap));
        } catch (Exception e){
            e.printStackTrace();
            logger.error("========根据timeCode查询时段信息失败=========",e);
        }
        return RtnData.fail("根据timeCode查询时段信息失败");
    }

    /**
     * 新增场地票价格策略
     * @param siteTicketStrategyInfoModel
     * @param request
     * @return
     */
    @RequestMapping(value = "/addStrategyInfo.json", method=RequestMethod.POST)
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

    /**
     * 分页查询价格策略列表
     * @param pageSize
     * @param page
     * @param ticketId
     * @param request
     * @return
     */
    @RequestMapping(value = "/getStrategyInfoByPage.json", method=RequestMethod.POST)
    @ResponseBody
    public RtnData getStrategyInfoByPage(@RequestParam(required = false, defaultValue = "10") long pageSize,
                                         @RequestParam(required = false, defaultValue = "1") long page,
                                         @RequestParam String ticketId,
                                         HttpServletRequest request){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("ticketId", ticketId);
        try {
            int totalSize = siteTicketService.getSiteTicketStrategyCount(paramMap);
            Page pagination = PaginationUtils.getPageParam(totalSize, pageSize, page); //计算出分页查询时需要使用的索引
            //pagination.setUrl(request.getRequestURI());
            paramMap.put("pageIndex", pagination.getStartIndex());
            paramMap.put("pageSize", pageSize);
            return RtnData.ok(siteTicketService.getSiteTicketStrategyInfoList(paramMap));
        }catch (Exception e){
            e.printStackTrace();
            logger.error("========新增场地票策略信息失败=========",e);
        }
        return RtnData.fail("新增场地票策略信息失败");
    }

    /**
     * 删除场地票价格策略
     * @param id
     * @return
     */
    @RequestMapping(value = "/delStrategyInfo.json", method=RequestMethod.POST)
    @ResponseBody
    public RtnData delStrategyInfo(String id){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        try {
            siteTicketService.delStrategyInfo(paramMap);
            return RtnData.ok("删除场地票策略信息成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("========删除场地票策略信息失败=========",e);
        }
        return RtnData.fail("删除场地票策略信息失败");
    }

    /**
     * 进入场地票价格策略编辑页面
     * @param map
     * @param request
     * @return
     */
    @RequestMapping(value = "/editStrategyInfo.html")
    public String editStrategyInfo(ModelMap map,HttpServletRequest request){
        try {
            //场地票价格策略信息
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("id", request.getParameter("id"));
            SiteTicketStrategyInfoModel strategyInfo = siteTicketService.getStrategyInfoByParam(paramMap);
            map.put("strategy", strategyInfo);
            //子场馆中场地列表
            paramMap.put("stadium_id",strategyInfo.getSubStadium());
            map.put("fieldList", fieldMapper.getAllByid(paramMap));
            //子场馆中正常状态时段策略列表
            List<Map<String, Object>> timeFrame = timeInteralMapper.getNormalTimeIntervalByStadiumid(paramMap);
            map.put("timeFrameList",timeFrame);
            //时段策略中的时间列表
            paramMap.put("time_code", timeFrame.get(0).get("id"));
            map.put("timeIntervalList", timeInteralMapper.getTimeIntervalByTimecode(paramMap));
            map.put("ticketId", request.getParameter("ticketId"));
        } catch (Exception e){
            e.printStackTrace();
            logger.error("========查询场地票价格策略信息失败=========",e);
            map.put("url",request.getRequestURL());
            map.put("exception",e);
            return "error";
        }
        return "siteticket/edit_site_strategy";
    }

    /**
     * 编辑场地票价格策略
     * @param siteTicketStrategyInfoModel
     * @param request
     * @return
     */
    @RequestMapping(value = "/editStrategyInfo.json", method=RequestMethod.POST)
    @ResponseBody
    public RtnData editStrategyInfo(SiteTicketStrategyInfoModel siteTicketStrategyInfoModel, HttpServletRequest request){
        try{
            siteTicketService.updateStrategyInfo(siteTicketStrategyInfoModel);
            return RtnData.ok("编辑场地票策略信息成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("========编辑场地票策略信息失败=========",e);
        }
        return RtnData.fail("编辑场地票策略信息失败");
    }

    /**
     * 查看场地票价格策略信息
     * @param map
     * @param request
     * @return
     */
    @RequestMapping(value = "/lookStrategyInfo.html")
    public String lookStrategyInfo(ModelMap map,HttpServletRequest request){
        try {
            //场地票价格策略信息
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("id", request.getParameter("id"));
            SiteTicketStrategyInfoModel strategyInfo = siteTicketService.getStrategyInfoByParam(paramMap);
            map.put("strategy", strategyInfo);
            //日期类型转换成中文注解
            String dateTypeValue = siteTicketService.dateTypeExplain(strategyInfo.getDateType());
            if("1".equals(strategyInfo.getDateType())){
                //每周信息转换成中文注解
                dateTypeValue += siteTicketService.weekExplain(strategyInfo.getWeekDetails());
            } else if("2".equals(strategyInfo.getDateType())){
                //每月暂时没有
            } else if("3".equals(strategyInfo.getDateType())){
                //指定日信息转换成中文注解
            }
            map.put("dateValue", "日期类型（" + dateTypeValue + "）");
            //价格策略中场地列表
            paramMap.put("idList",strategyInfo.getSite().split(","));
            map.put("fieldList", fieldMapper.getFieldByIds(paramMap));
            //时段策略中的时间列表
            String[] timeInterval = strategyInfo.getTimeInterval().split(",");
            paramMap.put("timeInterval", timeInterval);
            map.put("timeIntervalList", siteTicketService.getTimeIntervalList(paramMap));
            map.put("ticketId", request.getParameter("ticketId"));
        } catch (Exception e){
            e.printStackTrace();
            logger.error("========查询场地票价格策略信息失败=========",e);
            map.put("url",request.getRequestURL());
            map.put("exception",e);
            return "error";
        }
        return "siteticket/look_site_strategy";
    }
}

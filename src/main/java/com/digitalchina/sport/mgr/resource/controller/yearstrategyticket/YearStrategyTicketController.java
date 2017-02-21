package com.digitalchina.sport.mgr.resource.controller.yearstrategyticket;

import com.digitalchina.common.data.RtnData;
import com.digitalchina.sport.mgr.resource.dao.YearStrategyDao;
import com.digitalchina.sport.mgr.resource.model.TicketStrategyCommonCheckShieldTimeModel;
import com.digitalchina.sport.mgr.resource.model.YearStrategyTicketCheckUseableTimeModel;
import com.digitalchina.sport.mgr.resource.model.YearStrategyTicketModel;
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
import org.thymeleaf.util.ListUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 页面跳转测试
 * Created by xingding on 2016/8/17.
 */
@Controller
@RequestMapping(value = "/yearstrategyticket")
public class YearStrategyTicketController {

    public static final Logger logger = LoggerFactory.getLogger(YearStrategyTicketController.class);

    @Autowired
    private YearStrategyService yearStrategyService;
    @Autowired
    private YearStrategyDao yearStrategyDao;


    /**
     * 进入新增页面
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/add.html")
    public String addIndex(ModelMap map) {
//        List<Category> categorys = bookService.findCategorys();
        map.put("mainStadiumList", yearStrategyService.getAllMainStadium());
        map.put("merchantList", yearStrategyService.getAllMerchant());
        return "yearstrategyticket/add_year_strategy_ticket";
    }

    /**
     * 新增
     * @param yearStrategyTicket
     * @param request
     * @return
     */
    @RequestMapping(value = "/addYearStrategyTicket.json", method = RequestMethod.POST)
    @ResponseBody
    public RtnData add(YearStrategyTicketModel yearStrategyTicket,HttpServletRequest request) {
        boolean result = false;
        try {
            if(yearStrategyService.doInserYearStrategyTicketAndRelateInfo(yearStrategyTicket,request)) {
                return RtnData.ok("新增年卡成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("========新增年卡策略失败=========",e);
        }
        return RtnData.fail("新增年卡策略失败");
    }

    /**
     * 根据主场馆ID获取主场馆列表
     * @param mainStadiumId
     * @return
     */
    @RequestMapping(value = "/getSubStadiumListByMainId.json", method = RequestMethod.POST)
    @ResponseBody
    public RtnData getSubStadiumListByMainId(@RequestParam(required = true) String mainStadiumId) {
        try {
            Map<String,Object> resultMap = new HashMap<String,Object>();
            resultMap.put("subStadiumList",yearStrategyService.getSubStadiumListByMainId(mainStadiumId));
            return RtnData.ok(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("========根据主场馆ID获取子场馆列表失败=========",e);
        }
        return RtnData.fail("根据主场馆ID获取子场馆列表失败");
    }

    /**
     * 进入修改页面
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/modify.html")
    public String modifyIndex(ModelMap map,@RequestParam(required = true) String yearStrategyId,HttpServletRequest req) {
        YearStrategyTicketModel yearStrategyTicketModel = null;
        List<Map<String,Object>> stadiumList = null;
        List<TicketStrategyCommonCheckShieldTimeModel> shieldTimeList = null;
        List<YearStrategyTicketCheckUseableTimeModel>  useableTimeList = null;
        List<Map<String,Object>> relateStadiumList = null;

        try {
            yearStrategyTicketModel = yearStrategyDao.getYearStrategyTicketModelById(yearStrategyId);
            stadiumList = yearStrategyDao.getYearStrategyStadiumRelationsModelByYearStrategyId(yearStrategyId);
            shieldTimeList = yearStrategyDao.getTicketStrategyCommonCheckShieldTimeModelList(yearStrategyId);
            useableTimeList = yearStrategyDao.getYearStrategyTicketCheckUseableTimeModelList(yearStrategyId);
            relateStadiumList = yearStrategyDao.getYearStrategyStadiumRelationsModelByYearStrategyId(yearStrategyId);
            if(!ListUtils.isEmpty(relateStadiumList)) {
                Map<String,Object> tempMap = relateStadiumList.get(0);
                map.put("mainStadiumSelected",(String)tempMap.get("mainStadiumId"));
                map.put("subStadiumSelected",(String)tempMap.get("subStadiumId"));
            }
         } catch (Exception e) {
            e.printStackTrace();
            logger.error("========根据年票策略ID获取主场馆信息失败=========",e);
            map.put("url",req.getRequestURL());
            map.put("exception",e);
            return "error";
        }
        if(null == yearStrategyTicketModel) {
            map.put("url",req.getRequestURL());
            Exception exception = new Exception("查询不到该年票策略");
            map.put("exception",exception);
            return "error";
        }

        map.put("model",yearStrategyTicketModel);
        map.put("stadiumList",shieldTimeList);
        //生成屏蔽时间格式
        StringBuffer shieldTimeArrayStr = new StringBuffer("");
        if(!ListUtils.isEmpty(shieldTimeList)) {
            for(int i= 0;i < shieldTimeList.size();i++) {
                TicketStrategyCommonCheckShieldTimeModel shiledModel = shieldTimeList.get(i);
                shieldTimeArrayStr.append(shiledModel.getShieldStartTime()+"$" + shiledModel.getShieldEndTime());
                if(i != shieldTimeList.size()-1) {
                    shieldTimeArrayStr.append(",");
                }
            }
        }
        //使用时间列表
        StringBuffer useableTimeArrayStr = new StringBuffer("");
        if (!ListUtils.isEmpty(useableTimeList)) {
            for(int i= 0;i < useableTimeList.size();i++) {
                YearStrategyTicketCheckUseableTimeModel useableModel = useableTimeList.get(i);
                useableTimeArrayStr.append(useableModel.getUseableStartTime()+"$" + useableModel.getUseableEndTime());
                if(i != useableTimeList.size()-1) {
                    useableTimeArrayStr.append(",");
                }
            }
        }
        map.put("shieldTimeArrayStr",shieldTimeArrayStr.toString());
        map.put("useableTimeArrayStr",useableTimeArrayStr.toString());
        map.put("mainStadiumList", yearStrategyService.getAllMainStadium());
        map.put("merchantList", yearStrategyService.getAllMerchant());
        return "yearstrategyticket/modify_year_strategy_ticket";
    }


    /**
     * 新增
     * @param yearStrategyTicket
     * @param request
     * @return
     */
    @RequestMapping(value = "/modifyYearStrategyTicket.json", method = RequestMethod.POST)
    @ResponseBody
    public RtnData modifyYearStrategyTicket(YearStrategyTicketModel yearStrategyTicket,HttpServletRequest request) {
        boolean result = false;
        try {
            if(yearStrategyService.doModifyYearStrategyTicket(yearStrategyTicket,request)) {
                return RtnData.ok("新增年卡成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("========新增年卡策略失败=========",e);
        }
        return RtnData.fail("新增年卡策略失败");
    }
}

package com.digitalchina.sport.mgr.resource.controller.statistic;

import com.digitalchina.common.data.RtnData;
import com.digitalchina.sport.mgr.resource.service.StatisticsService;
import com.google.common.collect.Maps;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by yang_ on 2017/4/18.
 */
@Controller
@RequestMapping(value = "/statisticControler")
public class StatisticControler {
    @Autowired
    private StatisticsService statisticsService;
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(StatisticControler.class);
    @RequestMapping(value = "/getStatisticsByPaytypes.do")
    @ResponseBody
    public RtnData getStatisticsByPaytypes(String mainStadiumId,String subStadiumId,String sDate,String eDate,String userId){
        Map<String,Object> args = Maps.newHashMap();
        args.put("stadiumId",mainStadiumId);
        args.put("subStadiumId",subStadiumId);
        args.put("sDate",sDate);
        args.put("eDate",eDate);
        args.put("userId",userId);
        try {
            return RtnData.ok(statisticsService.getStatisticsByPaytypes(args));
        } catch (Exception e) {
            e.printStackTrace();
            return RtnData.fail("未知异常");
        }
    }
    @RequestMapping(value = "/getStatisticsByTicketTypes.do")
    @ResponseBody
    public RtnData getStatisticsByTicketTypes(String mainStadiumId,String subStadiumId,String sDate,String eDate,String userId){
        Map<String,Object> args = Maps.newHashMap();
        args.put("stadiumId",mainStadiumId);
        args.put("subStadiumId",subStadiumId);
        args.put("sDate",sDate);
        args.put("eDate",eDate);
        args.put("userId",userId);
        try {
            return RtnData.ok(statisticsService.getStatisticsByTicketTypes(args));
        } catch (Exception e) {
            e.printStackTrace();
            return RtnData.fail("未知异常");
        }
    }
    @RequestMapping(value = "/getStatisticsInfo.do")
    @ResponseBody
    public RtnData getStatisticsInfo(String ticketType,String mainStadiumId,String sDate,String eDate,String userId){
        Map<String,Object> args = Maps.newHashMap();
        args.put("ticketType",ticketType);
        args.put("stadiumId",mainStadiumId);
        args.put("sDate",sDate);
        args.put("eDate",eDate);
        args.put("userId",userId);
        try {
            return RtnData.ok(statisticsService.getStatisticsInfo(args));
        } catch (Exception e) {
            e.printStackTrace();
            return RtnData.fail("未知异常");
        }
    }

}

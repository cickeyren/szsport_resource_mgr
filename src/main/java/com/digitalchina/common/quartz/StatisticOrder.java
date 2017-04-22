package com.digitalchina.common.quartz;

import com.digitalchina.sport.mgr.resource.service.StatisticsService;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 日报统计
 * Created by yang_ on 2017/4/20.
 */
public class StatisticOrder implements Runnable {
    /**
     * 商户id
     */
    private String id;
    /**
     * 主场馆id
     */
    private String mainStadiumId;
    /**
     * 日结开始时间
     */
    private String sTime;
    /**
     * 日结结束时间
     */
    private String eTime;
    /**
     * 统计类型（1临时统计，0日结）
     */
    private String ifDaily="0";
    private StatisticsService statisticsService;

    public StatisticOrder(String id, String mainStadiumId,String sTime,String eTime,StatisticsService statisticsService) {
        this.id = id;
        this.mainStadiumId = mainStadiumId;
        this.sTime = sTime;
        this.eTime = eTime;
        this.statisticsService = statisticsService;
    }
    @Override
    public void run() {
        Map<String,Object> map = Maps.newHashMap();
        map.put("userId",id);
        map.put("stadiumId",mainStadiumId);
        map.put("sTime",sTime);
        map.put("eTime",eTime);
        map.put("ifDaily",ifDaily);
        statisticsService.statisticsByPaytype(map,"0");
        System.out.println(map);
    }
}

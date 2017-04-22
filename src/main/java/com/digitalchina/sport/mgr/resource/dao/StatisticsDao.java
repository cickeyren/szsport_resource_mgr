package com.digitalchina.sport.mgr.resource.dao;

import com.digitalchina.sport.mgr.resource.model.StatisticsByPaytype;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by yang_ on 2017/4/17.
 */
@Mapper
public interface StatisticsDao {
    List<Map<String, Object>> getStatisticsByPaytypes(Map<String,Object> args);
    List<Map<String, Object>> getStatisticsDaily(Map<String,Object> args);
    List<Map<String,Object>> getStatisticsByTicketTypes(Map<String,Object> args);
    List<Map<String, Object>> getStatisticsInfo(Map<String,Object> args);
    List<String> getPayment(Map<String,Object> args);
    List<String> getTicketType(Map<String,Object> args);
    void callStatisticByPayType(Map<String,Object> args);
}

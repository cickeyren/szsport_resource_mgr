package com.digitalchina.sport.mgr.resource.dao;

import com.digitalchina.sport.mgr.resource.model.TimeInterval;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@org.apache.ibatis.annotations.Mapper
public interface TimeIntervalMapper extends Mapper<TimeInterval> {
    /**
     * 根据场馆查询时间策略
     * @param params
     * @return
     */
    List<Map<String,Object>>  getTimeIntervalByStadiumid(Map<String,Object> params);

    /**
     * 根据时段编号查询时间策略
     * @param params
     * @return
     */
    List<Map<String,Object>>  getTimeIntervalByTimecode(Map<String,Object> params);

    /**
     * 根据场馆查询状态正常的时间策略
     * @param params
     * @return
     */
    List<Map<String,Object>>  getNormalTimeIntervalByStadiumid(Map<String,Object> params);

    /**
     * 格局时段编号查询时段策略条数
     * @param params
     * @return
     */
    int findTotalCountByInterval(Map<String, Object> params);

    /**
     * 根据时段编号分页查询时间策略
     * @param params
     * @return
     */
    List<Map<String,Object>> getAllTimeIntervalList(Map<String, Object> params);
}
package com.digitalchina.sport.mgr.resource.dao;

import com.digitalchina.sport.mgr.resource.model.TimeInterval;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@org.apache.ibatis.annotations.Mapper
public interface TimeIntervalMapper extends Mapper<TimeInterval> {
    List<Map<String,Object>>  getTimeIntervalByStadiumid(Map<String,Object> params);
    List<Map<String,Object>>  getTimeIntervalByTimecode(Map<String,Object> params);
}
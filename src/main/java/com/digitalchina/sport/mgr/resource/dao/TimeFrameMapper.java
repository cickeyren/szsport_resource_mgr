package com.digitalchina.sport.mgr.resource.dao;

import com.digitalchina.sport.mgr.resource.model.TimeFrame;
import tk.mybatis.mapper.common.Mapper;

import java.util.Map;
@org.apache.ibatis.annotations.Mapper
public interface TimeFrameMapper extends Mapper<TimeFrame> {
    //获取时段总条数
    int findTotalCount(Map<String, Object> params);
}
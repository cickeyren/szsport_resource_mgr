package com.digitalchina.sport.mgr.resource.dao;

import com.digitalchina.sport.mgr.resource.model.TimeFrame;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@org.apache.ibatis.annotations.Mapper
public interface TimeFrameMapper extends Mapper<TimeFrame> {
    //获取时段总条数
    int findTotalCount(Map<String, Object> params);

    /**
     * 根据查询条件查询出相关数据，没有查询条件时查出全部
     *
     * @param param
     * @return
     */
    List<Map<String, Object>> getAllTimeFrame(Map<String, Object> param);

    /**
     * 获取最大id
     *
     * @return
     */
    String getMaxId();

}
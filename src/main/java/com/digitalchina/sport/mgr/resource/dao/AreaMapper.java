package com.digitalchina.sport.mgr.resource.dao;

import com.digitalchina.sport.mgr.resource.model.Area;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@org.apache.ibatis.annotations.Mapper
public interface AreaMapper extends Mapper<Area> {
    List<Map<String,Object>> getAllArea(Map<String, Object> params);
}
package com.digitalchina.sport.mgr.resource.dao;

import com.digitalchina.sport.mgr.resource.model.City;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@org.apache.ibatis.annotations.Mapper
public interface CityMapper extends Mapper<City> {
    List<Map<String,Object>> getAllCity(Map<String, Object> params);
}
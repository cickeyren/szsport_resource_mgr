package com.digitalchina.sport.mgr.resource.dao;

import com.digitalchina.sport.mgr.resource.model.Province;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@org.apache.ibatis.annotations.Mapper
public interface ProvinceMapper extends Mapper<Province> {
    List<Map<String,Object>> getAllProvince();
}
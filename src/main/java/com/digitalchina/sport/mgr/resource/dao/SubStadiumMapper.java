package com.digitalchina.sport.mgr.resource.dao;

import com.digitalchina.sport.mgr.resource.model.SubStadium;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;
@org.apache.ibatis.annotations.Mapper
public interface SubStadiumMapper extends Mapper<SubStadium> {
    List<Map<String,Object>> getAllSubStadiumByParentId(Map<String,Object> params);
}
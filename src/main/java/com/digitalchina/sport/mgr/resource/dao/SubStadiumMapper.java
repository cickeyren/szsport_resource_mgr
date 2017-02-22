package com.digitalchina.sport.mgr.resource.dao;

import com.digitalchina.sport.mgr.resource.model.SubStadium;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface SubStadiumMapper extends Mapper<SubStadium> {
    public List<Map<String,Object>> getAllSubStadiumByParent_id(Map<String,Object> params);
}
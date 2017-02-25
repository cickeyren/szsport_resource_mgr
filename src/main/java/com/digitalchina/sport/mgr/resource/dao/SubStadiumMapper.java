package com.digitalchina.sport.mgr.resource.dao;

import com.digitalchina.sport.mgr.resource.model.SubStadium;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@org.apache.ibatis.annotations.Mapper
public interface SubStadiumMapper extends Mapper<SubStadium> {
    //根据主场馆id获取所有状态为正常子场馆
    List<Map<String, Object>> getAllSubStadiumByParentId(Map<String, Object> params);

    //分页查询总条数
    int findTotalCount(Map<String, Object> params);

    //分页查询所有子场馆信息
    List<Map<String,Object>> getAllsubStadiumList(Map<String, Object> params);

    //查询 所有子场馆信息
    List<Map<String,Object>> findsubStadium();

    //根据id查询子场馆信息
    SubStadium selectsubStadiumId(Map<String, Object> param);
}
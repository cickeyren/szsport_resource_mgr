package com.digitalchina.sport.mgr.resource.dao;

import com.digitalchina.sport.mgr.resource.model.Classify;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@org.apache.ibatis.annotations.Mapper
public interface ClassifyMapper extends Mapper<Classify> {
    //升序查询所有场馆分类
    List<Map<String, Object>> findAllClassify();

    //获取所有体育项目的父级菜单（）
    List<Map<String, Object>> findAllClassifyByMain();

    List<Map<String,Object>> findAllClassifyByCid(Map<String, Object> param);
}
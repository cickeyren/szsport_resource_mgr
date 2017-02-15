package com.digitalchina.sport.mgr.resource.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by xuxueyuan on 2017/2/15.
 */
@Mapper
public interface StadiumDao {
    /**
     * 查询省份列表
     */
    public List<Map<String,Object>> findProvienceList();
}

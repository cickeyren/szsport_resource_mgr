package com.digitalchina.sport.mgr.resource.dao;

import com.digitalchina.sport.mgr.resource.model.Field;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@org.apache.ibatis.annotations.Mapper
public interface FieldMapper extends Mapper<Field> {

    /**
     * 根据子场馆ID查询出子场馆场地的所有信息
     *
     * @param param 子场馆ID
     * @return
     */
    List<Map<String, Object>> getAllSubField(Map<String, Object> param);

    /**
     * 根据子场馆ID查询出子场馆场地的所有信息
     * @param param
     * @return
     */
    List<Map<String, Object>> getAllByid(Map<String, Object> param);

    /**
     * 根据子场馆ID查询子场地总数
     *
     * @param param 子场馆ID
     * @return
     */
    int getTotalCount(Map<String, Object> param);

    /**
     * 获取ID最大值
     *
     * @return
     */
    String getMaxId();


}
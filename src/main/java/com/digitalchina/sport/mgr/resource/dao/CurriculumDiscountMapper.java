package com.digitalchina.sport.mgr.resource.dao;


import com.digitalchina.sport.mgr.resource.model.CurriculumDiscount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


@Mapper
public interface CurriculumDiscountMapper {

    int deleteByPrimaryKey(String id);


    int insert(CurriculumDiscount record);


    int insertSelective(CurriculumDiscount record);


    CurriculumDiscount selectByPrimaryKey(String id);


    int updateByPrimaryKeySelective(CurriculumDiscount record);


    int updateByPrimaryKey(CurriculumDiscount record);

    List<Map<String,Object>> queryList(Map<String, Object> params);

    int queryListCount(Map<String, Object> params);

    List<CurriculumDiscount> selectById(@Param("curriculum_id") Integer curriculum_id);

    int deleteById(@Param("curriculum_id")  Integer curriculum_id);
}


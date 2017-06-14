package com.digitalchina.sport.mgr.resource.dao;


import com.digitalchina.sport.mgr.resource.model.TrainingInstitutionDiscountValue;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


@Mapper
public interface TrainingInstitutionDiscountValueMapper {

    int deleteByPrimaryKey(String id);


    int insert(TrainingInstitutionDiscountValue record);


    int insertSelective(TrainingInstitutionDiscountValue record);


    TrainingInstitutionDiscountValue selectByPrimaryKey(String id);


    int updateByPrimaryKeySelective(TrainingInstitutionDiscountValue record);


    int updateByPrimaryKey(TrainingInstitutionDiscountValue record);

    List<Map<String,Object>> getAllDiscountValueList();

    List<Map<String,Object>> getAllDiscountValueListWithChecked(@Param("curriculum_id") Integer curriculum_id);

}


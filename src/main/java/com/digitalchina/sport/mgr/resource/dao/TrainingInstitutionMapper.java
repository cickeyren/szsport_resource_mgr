package com.digitalchina.sport.mgr.resource.dao;


import com.digitalchina.sport.mgr.resource.model.TrainingInstitution;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface TrainingInstitutionMapper {

    int deleteByPrimaryKey(String id);


    int insert(TrainingInstitution record);


    int insertSelective(TrainingInstitution record);


    TrainingInstitution selectByPrimaryKey(String id);


    int updateByPrimaryKeySelective(TrainingInstitution record);


    int updateByPrimaryKey(TrainingInstitution record);

    List<Map<String,Object>> listTrainInstitution();

    List<Map<String,Object>> queryList(Map<String, Object> params);

    Integer queryListCount(Map<String, Object> params);
}


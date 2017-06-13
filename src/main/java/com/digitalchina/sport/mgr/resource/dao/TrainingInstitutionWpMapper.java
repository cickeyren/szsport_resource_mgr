package com.digitalchina.sport.mgr.resource.dao;


import com.digitalchina.sport.mgr.resource.model.TrainingInstitutionWp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


@Mapper
public interface TrainingInstitutionWpMapper {

    int deleteByPrimaryKey(String id);


    int insert(TrainingInstitutionWp record);


    int insertSelective(TrainingInstitutionWp record);


    TrainingInstitutionWp selectByPrimaryKey(String id);


    int updateByPrimaryKeySelective(TrainingInstitutionWp record);


    int updateByPrimaryKeyWithBLOBs(TrainingInstitutionWp record);

    int updateByPrimaryKey(TrainingInstitutionWp record);

    List<Map<String,Object>> queryList(Map<String, Object> params);

    Integer queryListCount(Map<String, Object> params);

    Map<String,Object> selectById(@Param("institution_id") String institution_id,
                                  @Param("merchant_id") String merchant_id,
                                  @Param("payment_id") String payment_id);
}


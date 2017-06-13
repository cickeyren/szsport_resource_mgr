package com.digitalchina.sport.mgr.resource.dao;


import com.digitalchina.sport.mgr.resource.model.TrainingInstitutionPic;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface TrainingInstitutionPicMapper {

    int deleteByPrimaryKey(String id);


    int insert(TrainingInstitutionPic record);


    int insertSelective(TrainingInstitutionPic record);


    TrainingInstitutionPic selectByPrimaryKey(String id);


    int updateByPrimaryKeySelective(TrainingInstitutionPic record);


    int updateByPrimaryKey(TrainingInstitutionPic record);

    List<Map<String,Object>> selectByInstitutionId(String id);

    int updateDefaultStatus(TrainingInstitutionPic record);
}


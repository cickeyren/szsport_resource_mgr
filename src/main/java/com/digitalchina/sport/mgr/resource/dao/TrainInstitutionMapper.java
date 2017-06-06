package com.digitalchina.sport.mgr.resource.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by jack on 2017/6/5.
 */
@Mapper
public interface TrainInstitutionMapper {
    List<Map<String,Object>> listTrainInstitution() throws Exception;
}

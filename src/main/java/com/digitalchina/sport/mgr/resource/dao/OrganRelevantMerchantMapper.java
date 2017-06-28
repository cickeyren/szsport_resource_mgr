package com.digitalchina.sport.mgr.resource.dao;


import com.digitalchina.sport.mgr.resource.model.OrganRelevantMerchant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


@Mapper
public interface OrganRelevantMerchantMapper {

    int insert(OrganRelevantMerchant record);


    int insertSelective(OrganRelevantMerchant record);

    int deleteByPrimaryKey(String id);

    int queryListCount(Map<String, Object> params);

    List<Map<String,Object>> queryList(Map<String, Object> params);

    Map<String,Object> selectById(@Param("organ_id") String organ_id, @Param("merchant_id") String merchant_id);
}


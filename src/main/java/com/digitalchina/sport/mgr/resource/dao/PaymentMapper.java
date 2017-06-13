package com.digitalchina.sport.mgr.resource.dao;


import com.digitalchina.sport.mgr.resource.model.Payment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface PaymentMapper {

    int deleteByPrimaryKey(String id);


    int insert(Payment record);


    int insertSelective(Payment record);


    Payment selectByPrimaryKey(String id);


    int updateByPrimaryKeySelective(Payment record);


    int updateByPrimaryKey(Payment record);

    List<Map<String,String>> getAllPaymentList();

    List<Map<String,String>> getAvailPaymentList(String institutionId, String merchantId);
    
}


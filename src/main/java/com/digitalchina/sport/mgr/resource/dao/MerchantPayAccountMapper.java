package com.digitalchina.sport.mgr.resource.dao;

import com.digitalchina.sport.mgr.resource.model.MerchantPayAccount;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@org.apache.ibatis.annotations.Mapper
public interface MerchantPayAccountMapper extends Mapper<MerchantPayAccount> {
    /**
     * 分页查询合作商户对应的支付方式
     * @param params
     * @return
     */
    List<Map<String,Object>> getmerchant_payList(Map<String, Object> params);

    /**
     * 根据条件查询分页总数
     * @param params
     * @return
     */
    int findTotalCount(Map<String, Object> params);
}
package com.digitalchina.sport.mgr.resource.dao;

import com.digitalchina.sport.mgr.resource.model.Merchant;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@org.apache.ibatis.annotations.Mapper
public interface MerchantMapper extends Mapper<Merchant> {
    /**
     * 查询合作商家总条数
     * @param params
     * @return
     */
    int findTotalCount(Map<String, Object> params);

    /**
     * 分页查询所有合作商户
     * @param params
     * @return
     */
    List<Map<String,Object>> getmerchantList(Map<String, Object> params);
}
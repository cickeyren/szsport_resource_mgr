package com.digitalchina.sport.mgr.resource.dao;

import com.digitalchina.sport.mgr.resource.model.MerchantUser;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@org.apache.ibatis.annotations.Mapper
public interface MerchantUserMapper extends Mapper<MerchantUser> {
    /**
     * 查询所有账户总数
     * @param params
     * @return
     */
    int findTotalCount(Map<String, Object> params);

    /**
     * 分页查询所有账户信息
     * @param params
     * @return
     */
    List<Map<String,Object>> getmerchantuser(Map<String, Object> params);
}
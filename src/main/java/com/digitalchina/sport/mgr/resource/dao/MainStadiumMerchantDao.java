package com.digitalchina.sport.mgr.resource.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author:wangw
 * @Description:主场馆合作商家管理
 * @Date:Created in 2017/6/2.
 */
@Mapper
public interface MainStadiumMerchantDao {
    /**
     * 获取合作商户账户信息总数量
     * @param map
     * @return
     * @throws Exception
     */
    public int getMerchantTotalCount(Map<String, Object> map) throws Exception;

    /**
     * 获取合作商户账户信息列表
     * @param map
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> getMerchantList(Map<String, Object> map) throws Exception;

    /**
     * 查询使用账户的合作商户
     * @param map
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> getMerchantByAccount(Map<String, Object> map) throws Exception;

    /**
     * 添加合作商家账户信息
     * @param map
     * @return
     * @throws Exception
     */
    public int addMerchantAccount(Map<String, Object> map) throws Exception;

    /**
     * 查询合作商家账户信息
     * @param map
     * @return
     * @throws Exception
     */
    public Map<String, Object> getMerchantAccountById(Map<String, Object> map) throws Exception;

    /**
     * 编辑合作商户账户信息
     * @param map
     * @return
     * @throws Exception
     */
    public int editMerchantAccount(Map<String, Object> map) throws Exception;

    /**
     * 删除合作商户账户信息
     * @param map
     * @return
     * @throws Exception
     */
    public int delMerchantAccount(Map<String, Object> map) throws Exception;
}

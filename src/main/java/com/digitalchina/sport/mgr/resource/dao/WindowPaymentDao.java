package com.digitalchina.sport.mgr.resource.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author:wangw
 * @Description:窗口支付管理
 * @Date:Created on 2017/4/5.
 */
@Mapper
public interface WindowPaymentDao {
    /**
     * 获取主场馆窗口支付信息总数量
     * @param map
     * @return
     * @throws Exception
     */
    public int getWindowPaymentTotalCount(Map<String, Object> map) throws Exception;

    /**
     * 获取主场馆窗口支付信息列表
     * @param map
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> getWindowPaymentList(Map<String, Object> map) throws Exception;

    /**
     * 查询字典表payment里的所有支付方式
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> getAllPaymentList() throws Exception;

    /**
     * 添加窗口支付信息
     * @param map
     * @return
     * @throws Exception
     */
    public int addWindowPayment(Map<String, Object> map) throws Exception;

    /**
     * 查询窗口支付信息
     * @param map
     * @return
     * @throws Exception
     */
    public Map<String, Object> getWindowPaymentById(Map<String, Object> map) throws Exception;

    /**
     * 编辑窗口支付信息
     * @param map
     * @return
     * @throws Exception
     */
    public int editWindowPayment(Map<String, Object> map) throws Exception;

    /**
     * 删除窗口支付信息
     * @param map
     * @return
     * @throws Exception
     */
    public int delWindowPayment(Map<String, Object> map) throws Exception;
}

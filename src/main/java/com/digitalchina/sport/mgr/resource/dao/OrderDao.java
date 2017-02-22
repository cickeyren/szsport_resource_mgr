package com.digitalchina.sport.mgr.resource.dao;



import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderDao {

    /**
     * 根据条件查询所有订单
     * @param param
     * @return
     * @throws Exception
     */
    List<Map<String,Object>> getOrderListByMap(Map<String,Object> param) throws Exception;

    /**
     * 根据条件查询所有订单
     * @param param
     * @return
     * @throws Exception
     */
    int getCountByMap(Map<String,Object> param) throws Exception;

    /**
     * 根据orderId查询详情
     * @param param
     * @return
     * @throws Exception
     */
    Map<String,Object> getOrderDetailsByOrderId(Map<String,Object> param) throws Exception;

    /**
     * 订单下的所有子单
     * @param param
     * @return
     * @throws Exception
     */
    List<Map<String,Object>> getAllOrderContentByOrderId(Map<String,Object> param) throws Exception;

    /**
     * 子订单详情
     * @param param
     * @return
     * @throws Exception
     */
    Map<String,Object> getOrderContentById(Map<String,Object> param) throws Exception;

    /**
     * 年卡散客才有的使用记录表
     * @param param
     * @return
     * @throws Exception
     */
    List<Map<String,Object>> getUsedRecordsByOrderContentId(Map<String,Object> param) throws Exception;

    /**
     * 判断子单是否存在，以及子单类型
     * @param param
     * @return
     * @throws Exception
     */
    Map<String,Object> getOrderContentCountAndType(Map<String,Object> param) throws Exception;
}

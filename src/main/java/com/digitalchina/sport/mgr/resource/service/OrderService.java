package com.digitalchina.sport.mgr.resource.service;


import com.digitalchina.common.utils.StringUtil;
import com.digitalchina.sport.mgr.resource.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    /**
     * 根据条件查询所有订单
     * @param param
     * @return
     * @throws Exception
     */
    public List<Map<String,Object>> getOrderListByMap(Map<String,Object> param) throws Exception {
        return orderDao.getOrderListByMap(param);
    }

    /**
     * 根据条件查询所有订单
     * @param param
     * @return
     * @throws Exception
     */
    public int getCountByMap(Map<String,Object> param) throws Exception {
        return orderDao.getCountByMap(param);
    }

    /**
     * 订单详情
     * @param param
     * @return
     * @throws Exception
     */
    public Map<String,Object> getOrderDetailsByMap(Map<String,Object> param) throws Exception{
        return orderDao.getOrderDetailsByOrderId(param);
    }

    /**
     * 子订单列表
     * @param param
     * @return
     * @throws Exception
     */
    public List<Map<String,Object>> getOrderContentListByMap(Map<String,Object> param) throws Exception{
        List<Map<String,Object>> orderContentList = null;
        try {
            orderContentList = orderDao.getAllOrderContentByOrderId(param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderContentList;
    }

    /**
     * 子订单详情
     * @param param
     * @return
     * @throws Exception
     */
    public Map<String,Object> getOrderContentById(Map<String,Object> param) throws Exception{
        return orderDao.getOrderContentById(param);
    }

    /**
     * 子订单列表
     * @param param
     * @return
     * @throws Exception
     */
    public List<Map<String,Object>> getUsedRecordsByOrderContentId(Map<String,Object> param) throws Exception{
        try {
            Map<String,Object> map = orderDao.getOrderContentCountAndType(param);
            int count = Integer.parseInt(map.get("count").toString());
            String ticketType = (String) map.get("ticketType");
            if(count>0){
                if(ticketType.equals("0") || ticketType.equals("2")){
                    List<Map<String,Object>> usedRecordsList = orderDao.getUsedRecordsByOrderContentId(param);
                    return usedRecordsList;
                }else return null;
            }else return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Map<String, String>> getMainStadium(Map<String,Object> map) throws Exception{
        return orderDao.getMainStadium(map);
    };
}

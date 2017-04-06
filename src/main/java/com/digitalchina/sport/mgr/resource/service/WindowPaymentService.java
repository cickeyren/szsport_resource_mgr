package com.digitalchina.sport.mgr.resource.service;

import com.digitalchina.sport.mgr.resource.dao.WindowPaymentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author:wangw
 * @Description:窗口支付管理
 * @Date:Created on 2017/4/5.
 */
@Service
public class WindowPaymentService {
    @Autowired
    private WindowPaymentDao windowpaymentDao;

    /**
     * 获取主场馆窗口支付信息总数量
     * @param map
     * @return
     * @throws Exception
     */
    public int getWindowPaymentTotalCount(Map<String, Object> map) throws Exception{
        return windowpaymentDao.getWindowPaymentTotalCount(map);
    }

    /**
     * 获取主场馆窗口支付信息列表
     * @param map
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> getWindowPaymentList(Map<String, Object> map) throws Exception{
        return windowpaymentDao.getWindowPaymentList(map);
    }

    /**
     * 查询字典表payment里的所有支付方式
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> getAllPaymentList() throws Exception{
        return windowpaymentDao.getAllPaymentList();
    }

    /**
     * 添加窗口支付信息
     * @param map
     * @return
     * @throws Exception
     */
    public int addWindowPayment(Map<String, Object> map) throws Exception{
        return windowpaymentDao.addWindowPayment(map);
    }

    /**
     * 查询窗口支付信息
     * @param map
     * @return
     * @throws Exception
     */
    public Map<String, Object> getWindowPaymentById(Map<String, Object> map) throws Exception{
        return windowpaymentDao.getWindowPaymentById(map);
    }

    /**
     * 编辑窗口支付信息
     * @param map
     * @return
     * @throws Exception
     */
    public int editWindowPayment(Map<String, Object> map) throws Exception{
        return windowpaymentDao.editWindowPayment(map);
    }

    /**
     * 删除窗口支付信息
     * @param map
     * @return
     * @throws Exception
     */
    public int delWindowPayment(Map<String, Object> map) throws Exception{
        return windowpaymentDao.delWindowPayment(map);
    }
}

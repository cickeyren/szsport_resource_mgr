package com.digitalchina.sport.mgr.resource.service;

import com.digitalchina.sport.mgr.resource.dao.PaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * （一句话描述）
 * 作用：
 *
 * @author xujin
 * @date 2017/6/13 14:14
 * @see
 */
@Service
public class PaymentService {
    @Autowired
    private PaymentMapper paymentMapper;

    /**
     * 查询所有的支付方式
     * @return
     */
    public List<Map<String,String>> getAllPaymentList() {
        return paymentMapper.getAllPaymentList();
    }

    /**
     * 根据商户查询可绑定的支付方式
     * @return
     */
    public List<Map<String,String>> getAvailPaymentList(String institutionId, String merchantId) {
        return paymentMapper.getAvailPaymentList(institutionId, merchantId);
    }
}

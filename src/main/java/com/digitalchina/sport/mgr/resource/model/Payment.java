package com.digitalchina.sport.mgr.resource.model;

import java.io.Serializable;

/**
 * [STRATO MyBatis Generator]
 * Table: payment
@mbggenerated do_not_delete_during_merge 2017-06-13 15:52:10
 */
public class Payment implements Serializable {
    /**
     *   标识ID
     * Column: payment.id
    @mbggenerated 2017-06-13 15:52:10
     */
    private String id;

    /**
     *   支付方式
     * Column: payment.payment
    @mbggenerated 2017-06-13 15:52:10
     */
    private String payment;

    /**
     * Table: payment
    @mbggenerated 2017-06-13 15:52:10
     */
    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment == null ? null : payment.trim();
    }
}
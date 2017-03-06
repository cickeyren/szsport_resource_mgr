package com.digitalchina.sport.mgr.resource.service;

import com.digitalchina.common.utils.StringUtils;
import com.digitalchina.sport.mgr.resource.dao.MerchantPayAccountMapper;
import com.digitalchina.sport.mgr.resource.model.MerchantPayAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * （一句话描述）
 * 作用：
 *
 * @author 王功文
 * @date 2017/2/28 14:19
 * @see
 */
@Service
public class MerchantPayAccountService {
    @Autowired
    private MerchantPayAccountMapper merchantPayAccountMapper;

    /**
     * 查询合作商户对应的支付方式总条数
     * @param params
     * @return
     */
    public int findTotalCount(Map<String, Object> params) {
        return merchantPayAccountMapper.findTotalCount(params);
    }

    /**
     * 分页查询支付方式数据
     * @param params
     * @return
     */
    public List<Map<String,Object>> getmerchant_payList(Map<String, Object> params) {
        return merchantPayAccountMapper.getmerchant_payList(params);
    }

    /**
     * 添加数据
     * @param merchantPayAccount
     * @return
     */
    public int insert(MerchantPayAccount merchantPayAccount) {
        return merchantPayAccountMapper.insert(merchantPayAccount);
    }

    /**
     * 查询数据条数
     * @param
     * @return
     */
    public Integer selectByParams(Map<String,Object> map) {
        return merchantPayAccountMapper.selectByParams(map);
    }

    /**
     * 更具主键查询数据
     * @param params
     * @return
     */
    public MerchantPayAccount getmerchant_payByparams(MerchantPayAccount params) {
        return merchantPayAccountMapper.selectByPrimaryKey(params);
    }

    /**
     * 更新数据
     * @param merchantPayAccount
     * @return
     */
    public int update(MerchantPayAccount merchantPayAccount) {
        return merchantPayAccountMapper.updateByPrimaryKey(merchantPayAccount);
    }
}

package com.digitalchina.sport.mgr.resource.service;

import com.digitalchina.sport.mgr.resource.dao.MerchantUserMapper;
import com.digitalchina.sport.mgr.resource.model.MerchantUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * （一句话描述）
 * 作用：
 *
 * @author 王功文
 * @date 2017/3/2 16:15
 * @see
 */
@Service
public class MerchantUserService {
    @Autowired
    private MerchantUserMapper merchantUserMapper;

    /**
     * 查询账户信息总数
     * @param params
     * @return
     */
    public int findTotalCount(Map<String, Object> params) {
        return merchantUserMapper.findTotalCount(params);
    }

    /**
     * 分页查询账户信息
     * @param params
     * @return
     */
    public List<Map<String,Object>> getmerchantuser(Map<String, Object> params) {
        return  merchantUserMapper.getmerchantuser(params);
    }

    /**
     * 添加账户信息
     * @param merchantUser
     * @return
     */
    public int insert(MerchantUser merchantUser) {
        return merchantUserMapper.insert(merchantUser);
    }

    /**
     * 更新数据
     * @param merchantUser
     * @return
     */
    public int updateByID(MerchantUser merchantUser) {
        return merchantUserMapper.updateByPrimaryKey(merchantUser);
    }

    /**
     * 根据主键查询数据
     * @param merchantUser
     * @return
     */
    public MerchantUser selectByID(MerchantUser merchantUser) {
        return merchantUserMapper.selectByPrimaryKey(merchantUser);
    }
}

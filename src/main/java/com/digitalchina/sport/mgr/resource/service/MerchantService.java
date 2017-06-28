package com.digitalchina.sport.mgr.resource.service;

import com.digitalchina.sport.mgr.resource.dao.MerchantMapper;
import com.digitalchina.sport.mgr.resource.model.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * （一句话描述）
 * 作用：
 *
 * @author 王功文
 * @date 2017/2/27 14:14
 * @see
 */
@Service
public class MerchantService {
    @Autowired
    private  MerchantMapper merchantMapper;

    /**
     * 分页查询合作商家总条数
     * @param params
     * @return
     */
    public int findTotalCount(Map<String, Object> params) {
        return merchantMapper.findTotalCount(params);
    }

    /**
     * 分页查询主场馆对应的合作商户
     * @param params
     * @return
     */
    public List<Map<String,Object>> getmerchantList(Map<String, Object> params) {
        return  merchantMapper.getmerchantList(params);
    }
    /**
     * 查询所有合作商户
     * @return
     */
    public List<Map<String,String>> getMerchants() {
        return  merchantMapper.getMerchants(null);
    }

    /**
     * 添加数据
     * @param merchant
     * @return
     */
    public int insertMerchant(Merchant merchant) {
        return merchantMapper.insert(merchant);
    }

    /**
     * 根据主键id查询合作商户信息
     * @param merchant
     * @return
     */
    public Merchant selectMerchantById(Merchant merchant) {
        return merchantMapper.selectByPrimaryKey(merchant);
    }

    /**
     * 修改商户信息
     * @param merchant
     * @return
     */
    public int updatemerchant(Merchant merchant) {
        return merchantMapper.updateByPrimaryKey(merchant);
    }

    /**
     * 根据条件查询所有合作商户列表
     * @param map
     * @return
     */
    public List<Map<String,Object>> getMerchantListByParam(Map<String, Object> map){
        return merchantMapper.getMerchantListByParam(map);
    }

    /**
     * 查询所有商户列表
     * @return
     */
    public List<Map<String,String>> getAllMerchantList() {
        return merchantMapper.getAllMerchantList();
    }

    /**
     * 根据培训机构查询商户列表
     * @return
     */
    public List<Map<String,String>> getMerchantListByInstitutionId(String institution_id) {
        return merchantMapper.getMerchantListByInstitutionId(institution_id);
    }
}

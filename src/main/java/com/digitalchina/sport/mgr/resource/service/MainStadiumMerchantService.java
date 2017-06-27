package com.digitalchina.sport.mgr.resource.service;

import com.digitalchina.common.utils.HttpClientUtil;
import com.digitalchina.common.utils.UUIDUtil;
import com.digitalchina.config.PropertyConfig;
import com.digitalchina.sport.mgr.resource.dao.MainStadiumMerchantDao;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:wangw
 * @Description:主场馆合作商家管理
 * @Date:Created in 2017/6/2.
 */
@Service
public class MainStadiumMerchantService {
    public static final Logger logger = LoggerFactory.getLogger(MainStadiumMerchantService.class);

    @Autowired
    private PropertyConfig proConfig;
    @Autowired
    private MainStadiumMerchantDao mainStadiumMerchantDao;

    /**
     * 获取合作商户账户信息总数量
     * @param map
     * @return
     * @throws Exception
     */
    public int getMerchantTotalCount(Map<String, Object> map) throws Exception{
        return mainStadiumMerchantDao.getMerchantTotalCount(map);
    }

    /**
     * 获取合作商户账户信息列表
     * @param map
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> getMerchantList(Map<String, Object> map) throws Exception{
        List<Map<String, Object>> rtnList = mainStadiumMerchantDao.getMerchantList(map);
        return rtnList;
    }

    /**
     * 验证场馆下合作商户是否已添加
     * @param mainStadiumId
     * @param merchants
     * @return
     */
    public boolean verifyMerchants(String mainStadiumId, String merchants) throws Exception{
        boolean isExist = false;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("mainStadiumId", mainStadiumId);
        paramMap.put("type", "1");
        List<Map<String, Object>> merchantList = mainStadiumMerchantDao.getMerchantByParam(paramMap);
        String[] merchantArr = merchants.split(",");
        for (int i = 0; i < merchantArr.length; i++){
            for (int j = 0; j < merchantList.size(); j++){
                if(merchantArr[i].equals(merchantList.get(j).get("merchantId"))){
                    //一个场馆，一个合作商户只能绑定一次
                    isExist = true;
                    break;
                }
            }
            if(isExist){
                break;
            }
        }
        return isExist;
    }

    /**
     * 添加合作商家信息
     * @param mainStadiumId
     * @param merchants
     * @return
     * @throws Exception
     */
    public int addMerchant(String mainStadiumId, String merchants) throws Exception{
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        String[] merchantArr = merchants.split(",");
        for(int i = 0; i < merchantArr.length; i++){
            Map<String, Object> itemMap = new HashMap<String, Object>();
            String id = UUIDUtil.generateUUID();
            itemMap.put("id", id);
            itemMap.put("type", '1');
            itemMap.put("organId", mainStadiumId);
            itemMap.put("merchantId", merchantArr[i]);
            list.add(itemMap);
        }
        paramMap.put("list",list);
        return mainStadiumMerchantDao.addMerchants(paramMap);
    }

    /**
     * 删除合作商户账户信息
     * @param map
     * @return
     * @throws Exception
     */
    public int delMerchant(Map<String, Object> map) throws Exception{
        return mainStadiumMerchantDao.delMerchant(map);
    }
}

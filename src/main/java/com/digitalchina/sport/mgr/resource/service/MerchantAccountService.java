package com.digitalchina.sport.mgr.resource.service;

import com.digitalchina.common.data.Constants;
import com.digitalchina.common.utils.HttpClientUtil;
import com.digitalchina.common.utils.UUIDUtil;
import com.digitalchina.config.PropertyConfig;
import com.digitalchina.sport.mgr.resource.controller.merchantAccount.MerchantAccountController;
import com.digitalchina.sport.mgr.resource.dao.MerchantAccountDao;
import com.digitalchina.sport.mgr.resource.dao.SubStadiumMapper;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:wangw
 * @Description:合作商家账户管理
 * @Date:Created in 2017/4/18.
 */
@Service
public class MerchantAccountService {
    public static final Logger logger = LoggerFactory.getLogger(MerchantAccountService.class);

    @Autowired
    private PropertyConfig proConfig;
    @Autowired
    private MerchantAccountDao merchantAccountDao;
    @Autowired
    private SubStadiumMapper subStadiumDao;

    /**
     * 获取合作商户账户信息总数量
     * @param map
     * @return
     * @throws Exception
     */
    public int getMerchantAccountTotalCount(Map<String, Object> map) throws Exception{
        return merchantAccountDao.getMerchantAccountTotalCount(map);
    }

    /**
     * 获取合作商户账户信息列表
     * @param map
     * @return
     * @throws Exception
     */
    @Transactional
    public List<Map<String, Object>> getMerchantAccountList(Map<String, Object> map) throws Exception{
        List<Map<String, Object>> rtnList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> itemList = merchantAccountDao.getMerchantAccountList(map);
        for (int i = 0; i < itemList.size(); i++){
            Map<String, Object> itemMap = itemList.get(i);
            String[] subStadiumArr = itemMap.get("subStadiumId").toString().split(",");
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("idList", subStadiumArr);
            List<Map<String, Object>> subStadiumList = subStadiumDao.getSubStadiumByIds(paramMap);
            StringBuffer subStadium = new StringBuffer();
            for (int j = 0; j < subStadiumList.size(); j++){
                if(j == subStadiumList.size() - 1){
                    subStadium.append(subStadiumList.get(j).get("name"));
                } else {
                    subStadium.append(subStadiumList.get(j).get("name")).append("、");
                }
            }
            itemMap.put("subStadium", subStadium.toString());
            rtnList.add(itemMap);
        }
        return rtnList;
    }

    /**
     * 查询所有有效的账户信息
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> getAllValidUser() throws Exception{
        String win_UPMS_url = proConfig.WINDOW_UPMS_URL + "?apiKey=" + proConfig.WINDOW_API_KEY +"&ext1=" + proConfig.WINDOW_EXT1;
        List<Map<String, Object>> rtnList = null;
        String result = null;
        try {
            result = HttpClientUtil.doGet(win_UPMS_url, 30000, null, null);
            Gson gson = new Gson();
            Map<String,Object> gsonMap =  gson.fromJson(result,Map.class);
            if(null != gsonMap && gsonMap.containsKey("status")) {
                if("OK".equals((String)gsonMap.get("status"))) {
                    Map<String,Object> resultMap = (Map<String,Object>)gsonMap;
                    rtnList = (List<Map<String, Object>>) resultMap.get("result");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("=========调用" + win_UPMS_url + "接口时发生错误===========",e);
        }
        return rtnList != null ? rtnList:new ArrayList<Map<String,Object>>();
    }

    /**
     * 验证账户是否在其他合作商户下使用
     * @param mainstadiumId
     * @param merchantId
     * @param loginId
     * @return
     */
    public boolean verifyMerchantAccount(String mainstadiumId, String merchantId, String loginId) throws Exception{
        boolean isExist = false;
        String[] loginIdArr = loginId.split(",");
        for (int i = 0; i < loginIdArr.length; i++){
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("loginId", loginIdArr[i]);
            List<Map<String, Object>> merchantIds = merchantAccountDao.getMerchantByAccount(paramMap);
            for (int j = 0; j < merchantIds.size(); j++){
                if(merchantId.equals(merchantIds.get(j).get("merchantId"))){
                    if(mainstadiumId.equals(merchantIds.get(j).get("mainStadiumId"))){
                        //同一场馆下合作商户中账户只能添加一次
                        isExist = true;
                        break;
                    }
                } else {
                    //一个账户只能对应一个合作商户
                    isExist = true;
                    break;
                }
            }
        }
        return isExist;
    }

    /**
     * 添加合作商家账户信息
     * @param mainStadiumId
     * @param merchantId
     * @param loginId
     * @param subStadiumId
     * @return
     * @throws Exception
     */
    public int addMerchantAccount(String mainStadiumId, String merchantId, String loginId, String subStadiumId) throws Exception{
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        String[] loginArr = loginId.split(",");
        for(int i = 0; i < loginArr.length; i++){
            Map<String, Object> itemMap = new HashMap<String, Object>();
            String id = UUIDUtil.generateUUID();
            itemMap.put("id", id);
            itemMap.put("mainStadiumId", mainStadiumId);
            itemMap.put("merchantId", merchantId);
            itemMap.put("subStadiumId", subStadiumId);
            itemMap.put("loginId", loginArr[i]);
            Map<String, Object> accountMap = getUserInfoById(loginArr[i]);
            itemMap.put("account", accountMap.get("account"));
            itemMap.put("name", accountMap.get("name"));
            if(accountMap.containsKey("roleList")){
                itemMap.put("role", accountMap.get("roleList"));
            } else {
                itemMap.put("role", "");
            }
            list.add(itemMap);
        }
        paramMap.put("list",list);
        return merchantAccountDao.addMerchantAccount(paramMap);
    }

    /**
     * 根据loginId查询账户信息
     * @return
     * @throws Exception
     */
    public Map<String, Object> getUserInfoById(String loginId) throws Exception{
        String win_UPMS_url = proConfig.WINDOW_UPMS_URL + "?apiKey=" + proConfig.WINDOW_API_KEY +"&loginId=" + loginId;
        List<Map<String, Object>> rtnList = null;
        String result = null;
        Map<String, Object> rtnMap = new HashMap<String, Object>();
        try {
            result = HttpClientUtil.doGet(win_UPMS_url, 30000, null, null);
            Gson gson = new Gson();
            Map<String,Object> gsonMap =  gson.fromJson(result,Map.class);
            if(null != gsonMap && gsonMap.containsKey("status")) {
                if("OK".equals((String)gsonMap.get("status"))) {
                    Map<String,Object> resultMap = (Map<String,Object>)gsonMap;
                    rtnList = (List<Map<String, Object>>) resultMap.get("result");
                    if(rtnList != null){
                        rtnMap = rtnList.get(0);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("=========调用" + win_UPMS_url + "接口时发生错误===========",e);
        }
        return rtnMap;
    }

    /**
     * 查询合作商家账户信息
     * @param map
     * @return
     * @throws Exception
     */
    public Map<String, Object> getMerchantAccountById(Map<String, Object> map) throws Exception{
        return merchantAccountDao.getMerchantAccountById(map);
    }

    /**
     * 编辑合作商户账户信息
     * @param map
     * @return
     * @throws Exception
     */
    public int editMerchantAccount(Map<String, Object> map) throws Exception{
        return merchantAccountDao.editMerchantAccount(map);
    }

    /**
     * 删除合作商户账户信息
     * @param map
     * @return
     * @throws Exception
     */
    public int delMerchantAccount(Map<String, Object> map) throws Exception{
        return merchantAccountDao.delMerchantAccount(map);
    }
}

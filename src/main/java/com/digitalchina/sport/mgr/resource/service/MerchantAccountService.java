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
        String win_UPMS_url = proConfig.WINDOW_UPMS_URL + "?apiKey=" + proConfig.WINDOW_API_KEY;
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
     * 添加合作商家账户信息
     * @param map
     * @return
     * @throws Exception
     */
    public int addMerchantAccount(Map<String, Object> map) throws Exception{
        return merchantAccountDao.addMerchantAccount(map);
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

package com.digitalchina.sport.mgr.resource.service;

import com.alibaba.fastjson.JSONArray;
import com.digitalchina.common.utils.HttpClientUtil;
import com.digitalchina.common.utils.UUIDUtil;
import com.digitalchina.config.PropertyConfig;
import com.digitalchina.sport.mgr.resource.dao.RoleDao;
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
 * @Description:合作商家账户权限管理
 * @Date:Created in 2017/6/23.
 */
@Service
public class RoleService {
    public static final Logger logger = LoggerFactory.getLogger(RoleService.class);

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private SubStadiumMapper subStadiumDao;

    public final static long MAIN_STADIUM = 1;//机构类型是场馆
    public final static long CURRICULUM = 2;//机构类型是培训机构

    /**
     * 获取合作商户绑定的主场馆信息
     * @param map
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> getMainStadiumListByMerchant(Map<String, Object> map) throws Exception{
        List<Map<String, Object>> rtnList = new ArrayList<Map<String, Object>>();
        //获取合作商户有权限访问的场馆信息
        List<Map<String, Object>> mainStadiumList =  roleDao.getMainStadiumListByMerchant(map);
        for (int i = 0; i < mainStadiumList.size(); i++){
            Map<String, Object> mainStadiumMap = mainStadiumList.get(i);
            mainStadiumMap.put("pId", "0");
            rtnList.add(mainStadiumMap);
            String mainStadiumId = mainStadiumMap.get("id").toString();
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("parent_id", mainStadiumId);
            List<Map<String, Object>> subStadiumList = subStadiumDao.getAllSubStadiumByParentId(paramMap);
            for (int j = 0; j < subStadiumList.size(); j++){
                Map<String, Object> itemMap = new HashMap<String, Object>();
                itemMap.put("id", subStadiumList.get(j).get("id"));
                itemMap.put("name", subStadiumList.get(j).get("name"));
                itemMap.put("pId", mainStadiumId);
                rtnList.add(itemMap);
            }
        }
        //获取账户有权限访问的场馆信息
        map.put("type", MAIN_STADIUM);
        List<Map<String, Object>> roleList = roleDao.getRoleInfoByAccount(map);
        for(int i = 0; i < rtnList.size(); i++){
            Map<String, Object> itemMap = rtnList.get(i);
            String mainStadiumId = itemMap.get("pId").toString();
            String subStadiumId = itemMap.get("id").toString();
            for(int j = 0; j < roleList.size(); j++){
                if(mainStadiumId.equals(roleList.get(j).get("organId")) && subStadiumId.equals(roleList.get(j).get("subStadiumId"))){
                    itemMap.put("checked", true);
                }
            }
        }
        return rtnList;
    }

    /**
     * 获取合作商户账户权限信息列表
     * @param map
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> getRoleInfoByAccount(Map<String, Object> map) throws Exception{
        List<Map<String, Object>> rtnList = roleDao.getRoleInfoByAccount(map);
        return rtnList;
    }

    /**
     * 添加合作商家账户场馆权限信息
     * @param request
     * @return
     * @throws Exception
     */
    @Transactional
    public int addStadiumRoleInfo(HttpServletRequest request) throws Exception{
        String loginId = request.getParameter("loginId");
        String account = request.getParameter("account");
        String merchantId = request.getParameter("merchantId");
        //1、清空该账户配置的权限
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("account", account);
        roleDao.delRoleByAccount(paramMap);
        //2、重新保存该用户的权限配置
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Gson gson = new Gson();
        List<Map<String, Object>> stadiumList = gson.fromJson(request.getParameter("subStadiumList"), List.class);
        for(int i = 0; i < stadiumList.size(); i++){
            Map<String, Object> itemMap = new HashMap<String, Object>();
            String id = UUIDUtil.generateUUID();
            itemMap.put("id", id);
            itemMap.put("loginId", loginId);
            itemMap.put("account", account);
            itemMap.put("merchantId", merchantId);
            itemMap.put("type", MAIN_STADIUM);
            itemMap.put("organId", stadiumList.get(i).get("mainStadiumId"));
            itemMap.put("subStadiumId", stadiumList.get(i).get("subStadiumId"));
            list.add(itemMap);
        }
        paramMap.put("list",list);
        return roleDao.addRoleInfo(paramMap);
    }
}

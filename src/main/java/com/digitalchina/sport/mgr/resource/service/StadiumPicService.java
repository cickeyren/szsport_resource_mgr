package com.digitalchina.sport.mgr.resource.service;

import com.digitalchina.common.utils.UUIDUtil;
import com.digitalchina.sport.mgr.resource.dao.StadiumPicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:wangw
 * @Description:主场馆图片管理
 * @Date:Created on 2017/4/5.
 */
@Service
public class StadiumPicService {
    @Autowired
    private StadiumPicMapper stadiumPicDao;

    public String IS_FIRST = "1";//首图
    public String IS_NOT_FIRST = "0";//非首图

    /**
     * 添加主场馆图片信息
     * @param map
     * @return
     * @throws Exception
     */
    public int addMainStaiumPic(Map<String, Object> map){
        String id = UUIDUtil.generateUUID();
        map.put("id", id);
        map.put("isFirst", "0");//默认非首图
        int num;
        try {
            num = stadiumPicDao.addMainStaiumPic(map);
        } catch (Exception e){
            num = 0;
        }
        return num;
    }

    /**
     * 查询主场馆图片列表
     * @param map
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> getMainStadiumPicList(Map<String, Object> map) throws Exception{
        return stadiumPicDao.getMainStadiumPicList(map);
    }

    /**
     * 场馆图片设为首图
     * @param map
     * @return
     * @throws Exception
     */
    @Transactional
    public boolean setPicIsFirst(Map<String, Object> map) throws Exception{
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("stadiumId", map.get("stadiumId"));
        paramMap.put("isFirst", IS_FIRST);
        Map<String, Object> oldFirst = stadiumPicDao.getMainStadiumPic(paramMap);
        oldFirst.put("isFirst", IS_NOT_FIRST);
        boolean isSuccess = false;
        if(stadiumPicDao.updateMainStadiumPic(oldFirst) > 0){
            isSuccess = true;
        }
        if(isSuccess){
            map.put("isFirst", IS_FIRST);
            if(stadiumPicDao.updateMainStadiumPic(map) > 0){
                isSuccess = true;
            } else {
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    /**
     * 删除主场馆图片信息
     * @param map
     * @return
     * @throws Exception
     */
    public int delMainStadiumPic(Map<String, Object> map) throws Exception{
        return stadiumPicDao.delMainStadiumPic(map);
    }
}

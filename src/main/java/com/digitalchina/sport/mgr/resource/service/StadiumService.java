package com.digitalchina.sport.mgr.resource.service;

import com.digitalchina.sport.mgr.resource.dao.StadiumDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by xuxueyuan on 2017/2/15.
 */
@Service
public class StadiumService {

    @Autowired
    private StadiumDao stadiumDao;

    /**
     * 获取省级列表
     */
   public List<Map<String,Object>> getProvienceList(){
       return stadiumDao.findProvienceList();
   }
}

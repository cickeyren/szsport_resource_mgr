package com.digitalchina.sport.mgr.resource.service;

import com.digitalchina.config.PropertyConfig;
import com.digitalchina.sport.mgr.resource.dao.MainStadiumDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * （一句话描述）
 * 作用：
 *
 * @author 王功文
 * @date 2017/2/20 17:14
 * @see
 */
@Service
public class MainStadiumService {
    @Autowired
    private PropertyConfig proConfig;
    @Autowired
    private MainStadiumDao mainStadiumDao;
    private static Logger logger = Logger.getLogger(MainStadiumService.class);

    public List<Map<String,Object>> getAllStadiumList(Map<String,Object> param){
        return mainStadiumDao.getAllStadiumList(param);
    }


    public int findTotalCount(Map<String, Object> params) {
        return mainStadiumDao.findTotalCount(params);
    }
}

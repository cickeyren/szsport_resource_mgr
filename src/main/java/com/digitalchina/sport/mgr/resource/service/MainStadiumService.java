package com.digitalchina.sport.mgr.resource.service;

import com.digitalchina.config.PropertyConfig;
import com.digitalchina.sport.mgr.resource.dao.MainStadiumDao;
import com.digitalchina.sport.mgr.resource.model.MainStadiumModel;
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

    public List<Map<String, Object>> findStadiumModel() {
        return mainStadiumDao.findStadiumModel();
    }

    public int insertmainStadium(MainStadiumModel mainStadiumModel) {
        return mainStadiumDao.insertmainStadium(mainStadiumModel);
    }

    public MainStadiumModel selectmainStadiumId(Long id) {
        return mainStadiumDao.selectmainStadiumId(id);
    }

    public int updateMainStadium(MainStadiumModel mainStadiumModel) {
        return mainStadiumDao.updateMainStadium(mainStadiumModel);
    }

    public int deleteMainStadium(Long id) {
        return mainStadiumDao.deleteMainStadium(id);
    }
}

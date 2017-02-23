package com.digitalchina.sport.mgr.resource.service;

import com.digitalchina.config.PropertyConfig;
import com.digitalchina.sport.mgr.resource.dao.ClassifyMapper;
import com.digitalchina.sport.mgr.resource.dao.MainStadiumDao;
import com.digitalchina.sport.mgr.resource.dao.SubStadiumMapper;
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
    @Autowired
    private SubStadiumMapper subStadiumMapper;
    @Autowired
    private ClassifyMapper classifyMapper;
    private static Logger logger = Logger.getLogger(MainStadiumService.class);

    /**
     * 分页查询所有主场馆
     * @param param
     * @return
     */
    public List<Map<String, Object>> getAllStadiumList(Map<String, Object> param) {
        return mainStadiumDao.getAllStadiumList(param);
    }

    /**
     * 带条件查询总条数
     * @param params
     * @return
     */
    public int findTotalCount(Map<String, Object> params) {
        return mainStadiumDao.findTotalCount(params);
    }

    /**
     * 查询所有主场馆
     * @return
     */
    public List<Map<String, Object>> findStadiumModel() {
        return mainStadiumDao.findStadiumModel();
    }

    /**
     *添加主场馆数据
     * @param mainStadiumModel
     * @return
     */
    public int insertmainStadium(MainStadiumModel mainStadiumModel) {
        return mainStadiumDao.insertmainStadium(mainStadiumModel);
    }

    /**
     *根据ID查询主场馆数据
     * @param id
     * @return
     */
    public MainStadiumModel selectmainStadiumId(Long id) {
        return mainStadiumDao.selectmainStadiumId(id);
    }

    /**
     *更新主场馆数据
     * @param mainStadiumModel
     * @return
     */
    public int updateMainStadium(MainStadiumModel mainStadiumModel) {
        return mainStadiumDao.updateMainStadium(mainStadiumModel);
    }

    /**
     *根据ID删除数据
     * @param id
     * @return
     */
    public int deleteMainStadium(Long id) {
        return mainStadiumDao.deleteMainStadium(id);
    }
}

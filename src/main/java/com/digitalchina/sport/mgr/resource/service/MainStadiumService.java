package com.digitalchina.sport.mgr.resource.service;

import com.digitalchina.sport.mgr.resource.dao.AreaMapper;
import com.digitalchina.sport.mgr.resource.dao.CityMapper;
import com.digitalchina.sport.mgr.resource.dao.MainStadiumDao;
import com.digitalchina.sport.mgr.resource.dao.ProvinceMapper;
import com.digitalchina.sport.mgr.resource.model.MainStadiumModel;
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
    private MainStadiumDao mainStadiumDao;
    @Autowired
    private ProvinceMapper provinceMapper;
    @Autowired
    private CityMapper cityMapper;
    @Autowired
    private AreaMapper areaMapper;

    /**
     * 分页查询所有主场馆
     *
     * @param param
     *
     * @return
     */
    public List<Map<String, Object>> getAllStadiumList(Map<String, Object> param) {
        return mainStadiumDao.getAllStadiumList(param);
    }

    /**
     * 带条件查询总条数
     *
     * @param params
     *
     * @return
     */
    public int findTotalCount(Map<String, Object> params) {
        return mainStadiumDao.findTotalCount(params);
    }

    /**
     * 查询所有主场馆
     *
     * @return
     */
    public List<Map<String, Object>> findStadiumModel() {
        return mainStadiumDao.findStadiumModel();
    }

    /**
     * 添加主场馆数据
     *
     * @param mainStadiumModel
     *
     * @return
     */
    public int insertmainStadium(MainStadiumModel mainStadiumModel) {
        return mainStadiumDao.insertmainStadium(mainStadiumModel);
    }

    /**
     * 根据ID查询主场馆数据
     *
     * @param
     *
     * @return
     */
    public MainStadiumModel selectmainStadiumId(Map<String, Object> param) {
        return mainStadiumDao.selectmainStadiumId(param);
    }

    /**
     * 更新主场馆数据
     *
     * @param mainStadiumModel
     *
     * @return
     */
    public int updateMainStadium(MainStadiumModel mainStadiumModel) {
        return mainStadiumDao.updateMainStadium(mainStadiumModel);
    }

    /**
     * 根据ID删除数据
     *
     * @param param
     *
     * @return
     */
    public int deleteMainStadium(Map<String, Object> param) {
        return mainStadiumDao.deleteMainStadium(param);
    }

    /**
     * 根据id设为精选
     *
     * @param param
     *
     * @return
     */
    public int updataSelectFirst(Map<String, Object> param) {
        return mainStadiumDao.updataSelectFirst(param);
    }

    /**
     * 获取所有省份列表
     *
     * @return
     */
    public List<Map<String, Object>> getAllProvince() {
        return provinceMapper.getAllProvince();
    }

    /**
     * 获取所有市区
     * @return
     * @param params
     */
    public List<Map<String,Object>> getAllCity(Map<String, Object> params) {
        return cityMapper.getAllCity(params);
    }

    /**
     * 获取所有县区
     * @return
     * @param params
     */
    public List<Map<String,Object>> getAllArea(Map<String, Object> params) {
        return areaMapper.getAllArea(params);
    }
}

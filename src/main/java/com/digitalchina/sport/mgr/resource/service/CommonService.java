package com.digitalchina.sport.mgr.resource.service;

import com.digitalchina.sport.mgr.resource.dao.AreaMapper;
import com.digitalchina.sport.mgr.resource.dao.CityMapper;
import com.digitalchina.sport.mgr.resource.dao.ProvinceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by xujin on 2017/6/5.
 */
@Service
public class CommonService {

    @Autowired
    private ProvinceMapper provinceMapper;
    @Autowired
    private CityMapper cityMapper;
    @Autowired
    private AreaMapper areaMapper;

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

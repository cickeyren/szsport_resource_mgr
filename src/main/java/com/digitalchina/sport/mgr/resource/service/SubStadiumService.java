package com.digitalchina.sport.mgr.resource.service;

import com.digitalchina.config.PropertyConfig;
import com.digitalchina.sport.mgr.resource.dao.ClassifyMapper;
import com.digitalchina.sport.mgr.resource.dao.MainStadiumDao;
import com.digitalchina.sport.mgr.resource.dao.SubStadiumMapper;
import com.digitalchina.sport.mgr.resource.model.MainStadiumModel;
import com.digitalchina.sport.mgr.resource.model.SubStadium;
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
 * @date 2017/2/24 14:18
 * @see
 */
@Service
public class SubStadiumService {
    @Autowired
    private SubStadiumMapper subStadiumMapper;

    /**
     * 分页查询总条数
     * @param params
     * @return
     */
    public int findTotalCount(Map<String, Object> params) {
        return subStadiumMapper.findTotalCount(params);
    }

    /**
     * 分页查询(通过主场馆查询所有子场馆)
     * @param params
     * @return
     */
    public List<Map<String,Object>> getAllsubStadiumList(Map<String, Object> params) {
        return subStadiumMapper.getAllsubStadiumList(params);
    }

    /**
     * 查询所有子场馆信息
     * @return
     */
    public List<Map<String,Object>> findsubStadium() {
        return subStadiumMapper.findsubStadium();
    }

    /**
     * 新增子场馆信息
     * @param subStadium
     * @return
     */
    public int insertsubStadium(SubStadium subStadium) {
        return subStadiumMapper.insert(subStadium);
    }

    /**
     * 通过id查询所有子场馆信息
     * @param param
     * @return
     */
    public Map<String,Object> selectsubStadiumId(Map<String, Object> param) {
        return subStadiumMapper.selectsubStadiumId(param);

    }

    /**
     * 更新数据
     * @param subStadium
     * @return
     */
    public int updatesubStadium(SubStadium subStadium) {
        return subStadiumMapper.updateByPrimaryKey(subStadium);
    }

    /**
     * 删除数据
     * @param param
     * @return
     */
    public int deletesubStadium(Map<String, Object> param) {
        return subStadiumMapper.deleteByPrimaryKey(param);
    }
}

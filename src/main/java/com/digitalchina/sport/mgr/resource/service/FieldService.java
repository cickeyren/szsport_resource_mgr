package com.digitalchina.sport.mgr.resource.service;

import com.digitalchina.sport.mgr.resource.dao.FieldMapper;
import com.digitalchina.sport.mgr.resource.model.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 子场地 -- 场地模块service
 * <p>
 * create by wanggw 2017-02-25
 */
@Service
public class FieldService {

    @Autowired
    private FieldMapper fieldMapper;

    /**
     * 分页查询出所有的子场馆信息
     *
     * @param param
     * @return
     */
    public List<Map<String, Object>> getAllSubFiled(Map<String, Object> param) {
        return fieldMapper.getAllSubField(param);
    }

    /**
     * 根据子场馆ID查询子场地总数
     *
     * @param param
     * @return
     */
    public int getTotalCount(Map<String, Object> param) {
        return fieldMapper.getTotalCount(param);
    }

    /**
     * 新增子场馆场地方法
     *
     * @param field 子场馆场地对象
     */
    public int insertField(Field field) {
        return fieldMapper.insert(field);
    }

    /**
     * 获取ID最大值
     *
     * @return
     */
    public String getMaxId() {
        return fieldMapper.getMaxId();
    }

    /**
     * 根据ID取出实体
     *
     * @param field
     * @return
     */
    public Field getFieldById(Field field) {
        return fieldMapper.selectByPrimaryKey(field);
    }

    /**
     * 根据ID删除实体
     *
     * @param field
     * @return
     */
    public int deleteField(Field field) {

        return fieldMapper.deleteByPrimaryKey(field);

    }

    /**
     * 根据ID更新实体
     *
     * @param field
     * @return
     */
    public int updateField(Field field) {
        return fieldMapper.updateByPrimaryKey(field);
    }

}

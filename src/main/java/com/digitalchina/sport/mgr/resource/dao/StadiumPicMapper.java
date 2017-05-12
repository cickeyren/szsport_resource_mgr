package com.digitalchina.sport.mgr.resource.dao;

import com.digitalchina.sport.mgr.resource.model.StadiumPic;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@org.apache.ibatis.annotations.Mapper
public interface StadiumPicMapper extends Mapper<StadiumPic> {
    /**
     * 添加主场馆图片信息
     * @param map
     * @return
     * @throws Exception
     */
    public int addMainStaiumPic(Map<String, Object> map) throws Exception;

    /**
     * 查询主场馆图片列表
     * @param map
     * @return
     */
    public List<Map<String, Object>> getMainStadiumPicList(Map<String, Object> map);

    /**
     * 查询主场馆图片信息
     * @param map
     * @return
     * @throws Exception
     */
    public Map<String, Object> getMainStadiumPic(Map<String, Object> map) throws Exception;

    /**
     * 修改主场馆图片信息
     * @param map
     * @return
     * @throws Exception
     */
    public int updateMainStadiumPic(Map<String, Object> map) throws Exception;

    /**
     * 删除主场馆图片信息
     * @param map
     * @return
     * @throws Exception
     */
    public int delMainStadiumPic(Map<String, Object> map) throws Exception;
}
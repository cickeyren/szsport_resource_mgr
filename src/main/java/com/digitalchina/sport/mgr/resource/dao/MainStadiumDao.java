package com.digitalchina.sport.mgr.resource.dao;

import com.digitalchina.sport.mgr.resource.model.MainStadiumModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * （一句话描述）
 * 作用：
 *
 * @author 王功文
 * @date 2017/2/20 16:36
 * @see
 */
@Mapper
public interface MainStadiumDao {
    //分页查询所有主场馆
    List<Map<String, Object>> getAllStadiumList(Map<String, Object> param);

    //带条件查询总条数
    int findTotalCount(Map<String, Object> params);

    //查询所有主场馆
    List<Map<String, Object>> findStadiumModel();

    //根据状态查询所有主场馆
    List<Map<String, Object>> findAllMainStadiumByStuts();

    //添加主场馆数据
    int insertmainStadium(MainStadiumModel mainStadiumModel);

    //根据ID查询主场馆数据
    MainStadiumModel selectmainStadiumId(Map<String,Object> param);

    //更新主场馆数据
    int updateMainStadium(MainStadiumModel mainStadiumModel);

    //根据ID删除数据
    int deleteMainStadium(Map<String,Object> param);

    //根据id设为精选
    int updataSelectFirst(Map<String, Object> param);
}

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
    List<Map<String,Object>> getAllStadiumList(Map<String,Object> param);

    int findTotalCount(Map<String, Object> params);

    List<Map<String,Object>> findStadiumModel();

    List<Map<String,Object>> findAllMainStadiumByStuts();

    int insertmainStadium(MainStadiumModel mainStadiumModel);

    MainStadiumModel selectmainStadiumId(Long id);

    int updateMainStadium(MainStadiumModel mainStadiumModel);

    int deleteMainStadium(Long id);
}

package com.digitalchina.sport.mgr.resource.dao;



import com.digitalchina.sport.mgr.resource.model.DiscountConfigure;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DiscountDao {

    List<Map<String,Object>> getListByMap(Map<String, Object> param) throws Exception;

    int getCountByMap(Map<String,Object> param) throws Exception;

    List<Map<String, String>> getPayType() throws Exception;
    List<Map<String, String>> getMainStadium() throws Exception;
    List<Map<String, String>> getSubStadium(String mainStadium) throws Exception;
    List<Map<String, String>> getDiscountType() throws Exception;

    int insert(DiscountConfigure discount) throws Exception;

    int zuofei(String id) throws Exception;

    int update(DiscountConfigure discount) throws Exception;

    DiscountConfigure getDiscountById(String id) throws Exception;

    Map<String, Object> getSameCountByParams(Map<String, Object> param) throws Exception;

    int updateOverTimeStatus(String status) throws Exception;

    DiscountConfigure getDetailById(String id) throws Exception;

    Map<String, Object> getMainStadiumById(String id) throws Exception;

    Map<String, Object> getSubStadiumById(String id) throws Exception;
}

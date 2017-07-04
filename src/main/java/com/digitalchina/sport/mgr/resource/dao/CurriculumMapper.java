package com.digitalchina.sport.mgr.resource.dao;

import com.digitalchina.sport.mgr.resource.model.Curriculum;
import com.digitalchina.sport.mgr.resource.model.CurriculumNew;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@org.apache.ibatis.annotations.Mapper
public interface CurriculumMapper extends Mapper<Curriculum> {

     List<Curriculum> getCurriculum(Map<String,Object> args);
     int getCurriculumCount(Map<String,Object> args);
     //     List<Map<String,Object>> getCurriculum(Map<String,Object> args);
     List<Curriculum> getCurriculumByNameExHas(Map<String,Object> args);
     List<Curriculum> getCurriculumByIds(Map<String,Object> args);
     List<Curriculum> getCurriculumByIdsNot(Map<String,Object> args);
     List<Map<String,Object>> getCurriculumOrderHasPay(Map<String,Object> args);
     int getCurriculumOrderHasPayCount(Map<String,Object> args);
     Curriculum selectById(Integer id);

     List<Map<String,Object>> getAvailCurriculumListByInstitutionId(@Param("institutionId") String institutionId, @Param("curriculumId") Integer curriculumId);

     CurriculumNew selectNewByPrimaryKey(Integer id);

     int updateNewByPrimaryKeySelective(CurriculumNew record);
     /**
      * 根据条件查询所有订单
      * @param param
      * @return
      * @throws Exception
      */
     List<Map<String,Object>> getOrderListByMap(Map<String,Object> param) throws Exception;

     /**
      * 根据条件查询所有订单
      * @param param
      * @return
      * @throws Exception
      */
     int getCountByMap(Map<String,Object> param) throws Exception;
     List<Map<String, String>> getCurriculumTypes();
     /**
      * 根据orderId查询详情
      * @param param
      * @return
      * @throws Exception
      */
     Map<String,Object> getOrderDetailsByOrderId(Map<String,Object> param) throws Exception;
     Map<String, Object> getCurriculumRefund(Map<String, Object> args);
     int updateOrderByOrderTime(Map<String, Object> args);

     List<Map<String,Object>> getSlCurriculumList(Map<String, Object> param);

     List<Map<String,Object>> getSlCurriculumClassList(Map<String, Object> param);
}
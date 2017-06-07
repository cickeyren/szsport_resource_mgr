package com.digitalchina.sport.mgr.resource.dao;

import com.digitalchina.sport.mgr.resource.model.Curriculum;
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
}
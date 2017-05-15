package com.digitalchina.sport.mgr.resource.dao;

import com.digitalchina.sport.mgr.resource.model.CurriculumClass;
import com.digitalchina.sport.mgr.resource.model.CurriculumClassTimes;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@org.apache.ibatis.annotations.Mapper
public interface CurriculumClassMapper extends Mapper<CurriculumClass> {
    List<CurriculumClassTimes> getCurriculumClasses(String curriculumId);
    CurriculumClassTimes getCurriculumClassesByKey(String id);
    int addCurriculumClassesTimes(Map<String,Object> args);
    int updateCurriculumClassesTimes(Map<String,Object> args);
//    int deleteTimes(Map<String,Object> args);
    int delTimess(String id);
    List<Map<String,Object>> getClassTimes(String classId);
}
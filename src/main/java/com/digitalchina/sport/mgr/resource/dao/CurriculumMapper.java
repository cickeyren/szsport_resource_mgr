package com.digitalchina.sport.mgr.resource.dao;

import com.digitalchina.sport.mgr.resource.model.Curriculum;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface CurriculumMapper extends Mapper<Curriculum> {

     List<Curriculum> getCurriculum();
}
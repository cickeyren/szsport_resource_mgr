package com.digitalchina.sport.mgr.resource.service;

import com.digitalchina.sport.mgr.resource.dao.TrainInstitutionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by jack on 2017/6/5.
 */
@Service
public class TrainInstitutionService {

    @Autowired
    TrainInstitutionMapper trainInstitutionMapper;

    public List<Map<String,Object>> listTrainInstitution() throws Exception{
        return trainInstitutionMapper.listTrainInstitution();
    }
}

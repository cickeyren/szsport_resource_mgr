package com.digitalchina.sport.mgr.resource.service;

import com.digitalchina.sport.mgr.resource.dao.TrainingInstitutionDiscountValueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * （一句话描述）
 * 作用：
 *
 * @author xujin
 * @date 2017/6/13 14:14
 * @see
 */
@Service
public class TrainInstitutionDiscountValueService {
    @Autowired
    private TrainingInstitutionDiscountValueMapper discountValueMapper;

    /**
     * 查询所有的窗口折扣
     * @return
     */
    public List<Map<String,Object>> getAllDiscountValueList() {
        return discountValueMapper.getAllDiscountValueList();
    }

    /**
     * 查询所有的窗口折扣（标识已选中）
     * @return
     */
    public List<Map<String,Object>> getAllDiscountValueListWithChecked(Integer curriculum_id) {
        return discountValueMapper.getAllDiscountValueListWithChecked(curriculum_id);
    }
}

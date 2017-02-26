package com.digitalchina.sport.mgr.resource.service;

import com.digitalchina.sport.mgr.resource.dao.TimeFrameMapper;
import com.digitalchina.sport.mgr.resource.model.TimeFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * （一句话描述）
 * 作用：
 *
 * @author 王功文
 * @date 2017/2/24 15:14
 * @see
 */
@Service
public class TimeFrameService {
    @Autowired
    private TimeFrameMapper timeFrameMapper;

    /**
     * 获取总条数
     *
     * @param params
     * @return
     */
    public int findTotalCount(Map<String, Object> params) {
        return timeFrameMapper.findTotalCount(params);
    }

    /**
     * 带条件查询所有时段
     *
     * @param params
     * @return
     */
    public List<Map<String, Object>> getAllTimeFrameList(Map<String, Object> params) {
        return timeFrameMapper.getAllTimeFrame(params);
    }

    /**
     * 获取最大ID
     *
     * @return
     */
    public String getMaxId(){

        return timeFrameMapper.getMaxId();

    }


    /**
     * 查询所有
     *
     * @return
     */
    public List<TimeFrame> findtimeFrame() {
        return timeFrameMapper.selectAll();
    }

    /**
     * 添加数据
     *
     * @param timeFrame
     * @return
     */
    public int inserttimeFrame(TimeFrame timeFrame) {
        return timeFrameMapper.insert(timeFrame);
    }

    /**
     * 通过id查询
     *
     * @param timeFrame
     * @return
     */
    public TimeFrame selectTimeFrameById(TimeFrame timeFrame) {
        return timeFrameMapper.selectByPrimaryKey(timeFrame);
    }

    /**
     * 更新数据
     *
     * @param timeFrame
     * @return
     */
    public int updatetimeFrame(TimeFrame timeFrame) {
        return timeFrameMapper.updateByPrimaryKey(timeFrame);
    }

    /**
     * 删除数据
     *
     * @param param
     * @return
     */
    public int deletetimeFrame(Map<String, Object> param) {
        return timeFrameMapper.deleteByPrimaryKey(param);
    }
}

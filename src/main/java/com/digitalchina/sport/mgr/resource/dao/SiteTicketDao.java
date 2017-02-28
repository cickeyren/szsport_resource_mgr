package com.digitalchina.sport.mgr.resource.dao;

import com.digitalchina.sport.mgr.resource.model.SiteTicketBasicInfoModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author:wangw
 * @Description:场地票管理
 * @Date:Created in 2017/2/23.
 */
@Mapper
public interface SiteTicketDao {
    /**
     * 添加场地票基本信息
     * @param siteTicketBasicInfoModel
     * @return
     */
    public int addSiteTicketBasicInfo(SiteTicketBasicInfoModel siteTicketBasicInfoModel);
}

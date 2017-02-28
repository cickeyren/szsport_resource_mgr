package com.digitalchina.sport.mgr.resource.service;

import com.digitalchina.sport.mgr.resource.dao.SiteTicketDao;
import com.digitalchina.sport.mgr.resource.model.SiteTicketBasicInfoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:wangw
 * @Description:场地票管理
 * @Date:Created in 2017/2/23.
 */
@Service
public class SiteTicketService {
    @Autowired
    private SiteTicketDao siteTicketDao;

    /**
     * 添加场地票基本信息
     * @param siteTicketBasicInfoModel
     * @return
     */
    public int addSiteTicketBasicInfo(SiteTicketBasicInfoModel siteTicketBasicInfoModel){
        return siteTicketDao.addSiteTicketBasicInfo(siteTicketBasicInfoModel);
    }
}

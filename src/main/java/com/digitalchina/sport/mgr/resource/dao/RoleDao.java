package com.digitalchina.sport.mgr.resource.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author:wangw
 * @Description:合作商家账户权限管理
 * @Date:Created in 2017/6/23.
 */
@Mapper
public interface RoleDao {
    /**
     * 获取合作商户绑定的主场馆信息
     * @param map
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>>  getMainStadiumListByMerchant(Map<String, Object> map) throws Exception;

    /**
     * 获取合作商户账户权限信息列表
     * @param map
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> getRoleInfoByAccount(Map<String, Object> map) throws Exception;

    /**
     * 添加合作商家账号权限信息
     * @param map
     * @return
     * @throws Exception
     */
    public int addRoleInfo(Map<String, Object> map) throws Exception;

    /**
     * 删除合作商户账户权限信息
     * @param map
     * @return
     * @throws Exception
     */
    public int delRoleByAccount(Map<String, Object> map) throws Exception;

    public List<Map<String, Object>>  getInstitutionListByAccount(@Param("login_id") String login_id) throws Exception;

    void delInstitutionRoleByAccount(@Param("login_id") String login_id);
}

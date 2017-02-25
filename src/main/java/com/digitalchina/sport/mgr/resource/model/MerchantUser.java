package com.digitalchina.sport.mgr.resource.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "merchant_user")
public class MerchantUser {
    /**
     * 账户编号
     */
    @Id
    private String id;

    /**
     * 商户id
     */
    @Column(name = "merchant_id")
    private String merchantId;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 用户手机号
     */
    @Column(name = "user_tel")
    private String userTel;

    /**
     * 用户初始密码
     */
    @Column(name = "user_pwd")
    private String userPwd;

    /**
     * 用户描述
     */
    private String descride;

    /**
     * 状态：1正常，0停用
     */
    private String status;

    /**
     * 管理权限：0，全部
     */
    @Column(name = "admin_role")
    private String adminRole;

    /**
     * 新建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取账户编号
     *
     * @return id - 账户编号
     */
    public String getId() {
        return id;
    }

    /**
     * 设置账户编号
     *
     * @param id 账户编号
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取商户id
     *
     * @return merchant_id - 商户id
     */
    public String getMerchantId() {
        return merchantId;
    }

    /**
     * 设置商户id
     *
     * @param merchantId 商户id
     */
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId == null ? null : merchantId.trim();
    }

    /**
     * 获取用户名
     *
     * @return user_name - 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名
     *
     * @param userName 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 获取用户手机号
     *
     * @return user_tel - 用户手机号
     */
    public String getUserTel() {
        return userTel;
    }

    /**
     * 设置用户手机号
     *
     * @param userTel 用户手机号
     */
    public void setUserTel(String userTel) {
        this.userTel = userTel == null ? null : userTel.trim();
    }

    /**
     * 获取用户初始密码
     *
     * @return user_pwd - 用户初始密码
     */
    public String getUserPwd() {
        return userPwd;
    }

    /**
     * 设置用户初始密码
     *
     * @param userPwd 用户初始密码
     */
    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd == null ? null : userPwd.trim();
    }

    /**
     * 获取用户描述
     *
     * @return descride - 用户描述
     */
    public String getDescride() {
        return descride;
    }

    /**
     * 设置用户描述
     *
     * @param descride 用户描述
     */
    public void setDescride(String descride) {
        this.descride = descride == null ? null : descride.trim();
    }

    /**
     * 获取状态：1正常，0停用
     *
     * @return status - 状态：1正常，0停用
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态：1正常，0停用
     *
     * @param status 状态：1正常，0停用
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 获取管理权限：0，全部
     *
     * @return admin_role - 管理权限：0，全部
     */
    public String getAdminRole() {
        return adminRole;
    }

    /**
     * 设置管理权限：0，全部
     *
     * @param adminRole 管理权限：0，全部
     */
    public void setAdminRole(String adminRole) {
        this.adminRole = adminRole == null ? null : adminRole.trim();
    }

    /**
     * 获取新建时间
     *
     * @return create_time - 新建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置新建时间
     *
     * @param createTime 新建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
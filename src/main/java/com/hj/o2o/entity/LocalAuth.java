package com.hj.o2o.entity;

import java.util.Date;

/**
 * @Author: HUJING
 * @Date: 2019/3/4 20:57
 * @Version 1.0
 * @Description:
 */
public class LocalAuth {
    //主键ID
    private Long localAuthId;
    //账号
    private String username;
    //密码
    private String password;
    //创建时间
    private Date createTime;
    //最近一次的更新时间
    private Date lastEditTime;
    //个人信息，关系为一一对应
    private PersonInfo personInfo;

    public Long getLocalAuthId() {
        return localAuthId;
    }

    public void setLocalAuthId(Long localAuthId) {
        this.localAuthId = localAuthId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public PersonInfo getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(PersonInfo personInfo) {
        this.personInfo = personInfo;
    }
}

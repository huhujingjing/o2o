package com.hj.o2o.entity;

import java.util.Date;

/**
 * @Description:
 * @Author: HUJING
 * @Date: 2019/3/26 21:14
 */
//顾客已领取的奖品映射
public class UserAwardMap {
    //主键id
    private Long userAwardId;
//    private Long userId;
//    private Long awardId;
//    private Long shopId;
//    private String userName;
//    private String awardName;
//    private Date expireTime;
    //创建时间
    private Date createTime;
//    private Integer usedStatus;
    //领取奖品所消耗的积分
    private Integer point;
    //顾客信息实体类
    private PersonInfo user;
    //奖品信息实体类
    private Award award;
    //店铺信息实体类
    private Shop shop;
    //操作员信息实体类
    private PersonInfo operator;

    public Long getUserAwardId() {
        return userAwardId;
    }

    public void setUserAwardId(Long userAwardId) {
        this.userAwardId = userAwardId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public PersonInfo getUser() {
        return user;
    }

    public void setUser(PersonInfo user) {
        this.user = user;
    }

    public Award getAward() {
        return award;
    }

    public void setAward(Award award) {
        this.award = award;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public PersonInfo getOperator() {
        return operator;
    }

    public void setOperator(PersonInfo operator) {
        this.operator = operator;
    }
}

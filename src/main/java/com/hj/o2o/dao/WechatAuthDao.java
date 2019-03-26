package com.hj.o2o.dao;

import com.hj.o2o.entity.WechatAuth;

/**
 * @Description:
 * @Author: HUJING
 * @Date: 2019/3/25 14:48
 */
public interface WechatAuthDao {
    /**
     *
     * @param openId
     * @return
     */
    WechatAuth queryWechatInfoByOpenId(String openId);

    /**
     *
     * @param wechatAuth
     * @return
     */
    int insertWechatAuth(WechatAuth wechatAuth);

    /**
     *
     * @param wechatAuthId
     * @return
     */
//    int deleteWechatAuth(Long wechatAuthId);
}

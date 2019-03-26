package com.hj.o2o.service;

import com.hj.o2o.dto.WechatAuthExecution;
import com.hj.o2o.entity.WechatAuth;
import com.hj.o2o.exceptions.WechatAuthOperationException;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @Description:
 * @Author: HUJING
 * @Date: 2019/3/24 23:22
 */

public interface WechatAuthService {
    /**
     * 通过openId查找平台对应的微信号
     *
     * @param openId
     * @return
     */
    WechatAuth getWechatAuthByOpenId(String openId);

    /**
     * 注册本平台的微信账号
     *
     * @param wechatAuth
     * @return
     * @throws RuntimeException
     */
    WechatAuthExecution register(WechatAuth wechatAuth) throws WechatAuthOperationException;
}

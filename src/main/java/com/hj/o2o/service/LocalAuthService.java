package com.hj.o2o.service;

import com.hj.o2o.dto.LocalAuthExecution;
import com.hj.o2o.entity.LocalAuth;
import com.hj.o2o.exceptions.LocalAuthOperationException;

/**
 * @Description:
 * @Author: HUJING
 * @Date: 2019/3/25 21:47
 */
public interface LocalAuthService {
    /**
     * 通过账号和密码获取平台账号信息
     *
     * @param username
     * @param password
     * @return
     */
    LocalAuth getLocalAuthByUserNameAndPwd(String username, String password);

    /**
     * 通过userId获取平台账号信息
     *
     * @param userId
     * @return
     */
    LocalAuth getLocalAuthByUserId(long userId);

    /**
     * 绑定微信，生成平台专属的账号
     *
     * @param localAuth
     * @return
     * @throws RuntimeException
     */
//    LocalAuthExecution register(LocalAuth localAuth) throws LocalAuthOperationException;

    /**
     *
     * @param localAuth
     * @return
     * @throws RuntimeException
     */
    LocalAuthExecution bindLocalAuth(LocalAuth localAuth)
            throws LocalAuthOperationException;

    /**
     * 修改平台账号的登录密码
     * @param userId
     * @param username
     * @param password
     * @param newPassword
     * @return
     */
    LocalAuthExecution modifyLocalAuth(Long userId, String username,
                                       String password, String newPassword)
            throws LocalAuthOperationException;
}

package com.hj.o2o.service.impl;

import com.hj.o2o.dao.LocalAuthDao;
import com.hj.o2o.dao.PersonInfoDao;
import com.hj.o2o.dto.LocalAuthExecution;
import com.hj.o2o.entity.LocalAuth;
import com.hj.o2o.entity.PersonInfo;
import com.hj.o2o.enums.LocalAuthStateEnum;
import com.hj.o2o.exceptions.LocalAuthOperationException;
import com.hj.o2o.service.LocalAuthService;
import com.hj.o2o.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Description:
 * @Author: HUJING
 * @Date: 2019/3/25 21:55
 */
@Service
public class LocalAuthServiceImpl implements LocalAuthService {

    @Autowired
    private LocalAuthDao localAuthDao;
    @Autowired
    private PersonInfoDao personInfoDao;

    @Override
    public LocalAuth getLocalAuthByUserNameAndPwd(String userName, String password) {
        return localAuthDao.queryLocalByUserNameAndPwd(userName, MD5.getMd5(password));
    }

    @Override
    public LocalAuth getLocalAuthByUserId(long userId) {
        return localAuthDao.queryLocalByUserId(userId);
    }

//    @Override
//    @Transactional
//    public LocalAuthExecution register(LocalAuth localAuth) throws RuntimeException {
//        if (localAuth == null || localAuth.getPassword() == null
//                || localAuth.getUsername() == null) {
//            return new LocalAuthExecution(LocalAuthStateEnum.NULL_AUTH_INFO);
//        }
//        try {
//            localAuth.setCreateTime(new Date());
//            localAuth.setLastEditTime(new Date());
//            localAuth.setPassword(MD5.getMd5(localAuth.getPassword()));
//            if (localAuth.getPersonInfo() != null
//                    && localAuth.getPersonInfo().getUserId() == null) {
//                if (profileImg != null) {
//                    localAuth.getPersonInfo().setCreateTime(new Date());
//                    localAuth.getPersonInfo().setLastEditTime(new Date());
//                    localAuth.getPersonInfo().setEnableStatus(1);
//                    try {
//                        addProfileImg(localAuth, profileImg);
//                    } catch (Exception e) {
//                        throw new RuntimeException("addUserProfileImg error: "
//                                + e.getMessage());
//                    }
//                }
//                try {
//                    PersonInfo personInfo = localAuth.getPersonInfo();
//                    int effectedNum = personInfoDao
//                            .insertPersonInfo(personInfo);
//                    //                    localAuth.setUserId(personInfo.getUserId());
//                    if (effectedNum <= 0) {
//                        throw new RuntimeException("添加用户信息失败");
//                    }
//                } catch (Exception e) {
//                    throw new RuntimeException("insertPersonInfo error: "
//                            + e.getMessage());
//                }
//            }
//            int effectedNum = localAuthDao.insertLocalAuth(localAuth);
//            if (effectedNum <= 0) {
//                throw new RuntimeException("帐号创建失败");
//            } else {
//                return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS,
//                        localAuth);
//            }
//        } catch (Exception e) {
//            throw new RuntimeException("insertLocalAuth error: "
//                    + e.getMessage());
//        }
//    }

    @Override
    @Transactional
    public LocalAuthExecution bindLocalAuth(LocalAuth localAuth) throws LocalAuthOperationException {
        //空值判断，传入的localAuth账号密码，用户信息特别是userId不能为空，否则直接返回错误
        if (localAuth == null || localAuth.getPassword() == null || localAuth.getUsername() == null
                || localAuth.getPersonInfo() == null || localAuth.getPersonInfo().getUserId() == null) {
            return new LocalAuthExecution(LocalAuthStateEnum.NULL_AUTH_INFO);
        }
        //查询此用户是否已绑定过平台账号
        LocalAuth tempAuth = localAuthDao.queryLocalByUserId(localAuth.getPersonInfo().getUserId());
        if (tempAuth != null) {
            return new LocalAuthExecution(LocalAuthStateEnum.ONLY_ONE_ACCOUNT);
        }
        try {
            //如果之前没有绑定过平台账号，则创建一个平台账号与该用户绑定
            localAuth.setCreateTime(new Date());
            localAuth.setLastEditTime(new Date());
            //对密码进行MD5加密
            localAuth.setPassword(MD5.getMd5(localAuth.getPassword()));
            int effectedNum = localAuthDao.insertLocalAuth(localAuth);
            //判断创建是否成功
            if (effectedNum <= 0) {
                throw new RuntimeException("帐号绑定失败");
            } else {
                return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS, localAuth);
            }
        } catch (Exception e) {
            throw new LocalAuthOperationException("insertLocalAuth error: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public LocalAuthExecution modifyLocalAuth(Long userId, String userName,
                                              String password, String newPassword) {
        //非空判断，判断传入的用户Id，账号，新旧密码是否为空，新旧密码是否相同，若不满足条件则返回错误信息
        if (userId != null && userName != null && password != null
                && newPassword != null && !password.equals(newPassword)) {
            try {
                //更新密码，并对新密码进行MD5加密
                int effectedNum = localAuthDao.updateLocalAuth(userId, userName, MD5.getMd5(password),
                        MD5.getMd5(newPassword), new Date());
                //判断更新是否成功
                if (effectedNum <= 0) {
                    throw new LocalAuthOperationException("更新密码失败");
                }
                return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS);
            } catch (Exception e) {
                throw new LocalAuthOperationException("更新密码失败:" + e.toString());
            }
        } else {
            return new LocalAuthExecution(LocalAuthStateEnum.NULL_AUTH_INFO);
        }
    }

    //    private void addProfileImg(LocalAuth localAuth,
    //                               CommonsMultipartFile profileImg) {
    //        String dest = FileUtil.getPersonInfoImagePath();
    //        String profileImgAddr = ImageUtil.generateThumbnail(profileImg, dest);
    //        localAuth.getPersonInfo().setProfileImg(profileImgAddr);
    //    }

}

package com.hj.o2o.service;

import com.hj.o2o.BaseTest;
import com.hj.o2o.dto.LocalAuthExecution;
import com.hj.o2o.entity.LocalAuth;
import com.hj.o2o.entity.PersonInfo;
import com.hj.o2o.enums.WechatAuthStateEnum;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

/**
 * @Description:
 * @Author: HUJING
 * @Date: 2019/3/25 22:12
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LocalAuthServiceTest extends BaseTest {
    @Autowired
    private LocalAuthService localAuthService;

    @Test
    public void testABindLocalAuth(){
        //新增一条平台账号
        LocalAuth localAuth = new LocalAuth();
        PersonInfo personInfo = new PersonInfo();
        String username = "testusername";
        String password = "testpassword";
        //给平台账号设置上用户信息
        //给用户设置上用户Id，表明是某个用户创建的账号
        personInfo.setUserId(1L);
        //给平台账号设置用户信息，标明是哪个用户绑定
        localAuth.setPersonInfo(personInfo);
        //设置账号
        localAuth.setUsername(username);
        //设置密码
        localAuth.setPassword(password);
        //绑定账号
        LocalAuthExecution lae = localAuthService.bindLocalAuth(localAuth);
        assertEquals(WechatAuthStateEnum.SUCCESS.getState(),lae.getState());
        //通过userId找到新增的localAuth
        localAuth = localAuthService.getLocalAuthByUserId(personInfo.getUserId());
        //打印用户名和账号密码看着跟预期是否相等
        System.out.println("用户昵称:" + localAuth.getPersonInfo().getName());
        System.out.println("平台账号密码:" + localAuth.getPassword());
    }

    @Test
    public void testBModifyLocalAuth(){
        //设置账号信息
        long userId = 1;
        String username = "testusername";
        String password = "testpassword";
        String newpassword = "testnewpassword";
        //修改该账号对应的密码
        LocalAuthExecution lae = localAuthService.modifyLocalAuth(userId,username,password,newpassword);
        assertEquals(WechatAuthStateEnum.SUCCESS.getState(),lae.getState());
        //通过账号密码找到修改后的localAuth
        LocalAuth localAuth = localAuthService.getLocalAuthByUserNameAndPwd(username,newpassword);
        //打印用户名字看看跟预期是否相等
        System.out.println(localAuth.getPersonInfo().getName());

    }

    @Test
    public void testCQueryLocalByUserId(){
        //设置账号信息
        long userId = 1;
        LocalAuth localAuth = localAuthService.getLocalAuthByUserId(userId);
        System.out.println(localAuth.getUsername());

    }
}

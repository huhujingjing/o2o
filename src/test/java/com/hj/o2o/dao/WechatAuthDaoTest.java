package com.hj.o2o.dao;

import com.hj.o2o.BaseTest;
import com.hj.o2o.entity.PersonInfo;
import com.hj.o2o.entity.WechatAuth;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * @Description:
 * @Author: HUJING
 * @Date: 2019/3/25 15:14
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WechatAuthDaoTest extends BaseTest {
    @Autowired
    private WechatAuthDao wechatAuthDao;

    @Test
    public void testAInsertWechatAuth() throws Exception {
        WechatAuth wechatAuth = new WechatAuth();
        PersonInfo personInfo = new PersonInfo();
        personInfo.setUserId(1L);
        wechatAuth.setPersonInfo(personInfo);
        wechatAuth.setOpenId("fnanonvSADSAB");
        wechatAuth.setCreateTime(new Date());
        int effectedNum = wechatAuthDao.insertWechatAuth(wechatAuth);
        assertEquals(1, effectedNum);
    }

    @Test
    public void testBQueryWechatAuthByOpenId() throws Exception {
        WechatAuth wechatAuth = wechatAuthDao
                .queryWechatInfoByOpenId("fnanonvSADSAB");
        assertEquals("测试", wechatAuth.getPersonInfo().getName());
    }

//    @Test
//    public void testDeleteWechatAuth() throws Exception {
//        WechatAuth wechatAuth = wechatAuthDao
//                .queryWechatInfoByOpenId("dafahizhfdhaih");
//        int effectedNum = wechatAuthDao.deleteWechatAuth(wechatAuth
//                .getWechatAuthId());
//        assertEquals(1, effectedNum);
//    }
}

package com.hj.o2o.dao;

import com.hj.o2o.BaseTest;
import com.hj.o2o.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @Author: HUJING
 * @Date: 2019/3/4 23:06
 * @Version 1.0
 * @Description:
 */
public class AreaDaoTest extends BaseTest {
    @Autowired
    private AreaDao areaDao;

    @Test
    public void testQueryArea(){
        List<Area> areaList = areaDao.queryArea();
        assertEquals(2,areaList.size());
    }
}

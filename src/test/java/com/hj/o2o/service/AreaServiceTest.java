package com.hj.o2o.service;

import com.hj.o2o.BaseTest;
import com.hj.o2o.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @Author: HUJING
 * @Date: 2019/3/4 23:30
 * @Version 1.0
 * @Description:
 */
public class AreaServiceTest extends BaseTest {
    @Autowired
    private AreaService areaService;

    @Test
    public void testGetAreaList(){
        List<Area> areaList = areaService.getAreaList();
        assertEquals("北苑",areaList.get(0).getAreaName());
    }
}

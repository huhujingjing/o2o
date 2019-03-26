package com.hj.o2o.service;

import com.hj.o2o.entity.Area;

import java.util.List;

/**
 * @Author: HUJING
 * @Date: 2019/3/4 23:25
 * @Version 1.0
 * @Description:
 */
public interface AreaService {
    public static final String AREALISTKEY = "arealist";
    List<Area> getAreaList();
}

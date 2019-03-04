package com.hj.o2o.dao;

import com.hj.o2o.entity.Area;

import java.util.List;

/**
 * @Author: HUJING
 * @Date: 2019/3/4 22:58
 * @Version 1.0
 * @Description:
 */
public interface AreaDao {
    /**
     * 列出区域列表
     * @return areaList
     */
    List<Area> queryArea();
}

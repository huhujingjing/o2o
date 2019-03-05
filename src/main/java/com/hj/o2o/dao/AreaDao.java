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
     * 列出地域列表
     *
     * @param
     * @return
     */
    List<Area> queryArea();

    /**
     *
     * @param area
     * @return
     */
    int insertArea(Area area);

    /**
     *
     * @param area
     * @return
     */
    int updateArea(Area area);

    /**
     *
     * @param areaId
     * @return
     */
    int deleteArea(long areaId);

    /**
     *
     * @param areaIdList
     * @return
     */
    int batchDeleteArea(List<Long> areaIdList);
}

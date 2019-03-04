package com.hj.o2o.service.impl;

import com.hj.o2o.dao.AreaDao;
import com.hj.o2o.entity.Area;
import com.hj.o2o.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: HUJING
 * @Date: 2019/3/4 23:27
 * @Version 1.0
 * @Description:
 */
@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaDao areaDao;
    @Override
    public List<Area> getAreaList() {
        return areaDao.queryArea();
    }
}

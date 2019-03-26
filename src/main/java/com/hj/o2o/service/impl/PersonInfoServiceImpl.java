package com.hj.o2o.service.impl;

import com.hj.o2o.dao.PersonInfoDao;
import com.hj.o2o.entity.PersonInfo;
import com.hj.o2o.service.PersonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: HUJING
 * @Date: 2019/3/25 15:45
 */
@Service
public class PersonInfoServiceImpl implements PersonInfoService {

    @Autowired
    private PersonInfoDao personInfoDao;

    @Override
    public PersonInfo getPersonInfoById(Long userId) {
        return personInfoDao.queryPersonInfoById(userId);
    }
}

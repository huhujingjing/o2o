package com.hj.o2o.service;

import com.hj.o2o.dto.PersonInfoExecution;
import com.hj.o2o.entity.PersonInfo;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: HUJING
 * @Date: 2019/3/24 23:20
 */
@Service
public interface PersonInfoService {
    /**
     *
     * @param userId
     * @return
     */
    PersonInfo getPersonInfoById(Long userId);

//    /**
//     *
//     * @param personInfoCondition
//     * @param pageIndex
//     * @param pageSize
//     * @return
//     */
//    PersonInfoExecution getPersonInfoList(PersonInfo personInfoCondition,
//                                          int pageIndex, int pageSize);
//
//    /**
//     *
//     * @param personInfo
//     * @return
//     */
//    PersonInfoExecution addPersonInfo(PersonInfo personInfo);
//
//    /**
//     *
//     * @param personInfo
//     * @return
//     */
//    PersonInfoExecution modifyPersonInfo(PersonInfo personInfo);
}

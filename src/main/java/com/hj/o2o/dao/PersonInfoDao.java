package com.hj.o2o.dao;

import com.hj.o2o.entity.PersonInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Author: HUJING
 * @Date: 2019/3/25 14:46
 */
public interface PersonInfoDao {
    /**
     *
     * @param personInfoCondition
     * @param rowIndex
     * @param pageSize
     * @return
     */
    List<PersonInfo> queryPersonInfoList(
            @Param("personInfoCondition") PersonInfo personInfoCondition,
            @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

    /**
     *
     * @param personInfoCondition
     * @return
     */
    int queryPersonInfoCount(
            @Param("personInfoCondition") PersonInfo personInfoCondition);

    /**
     *
     * @param userId
     * @return
     */
    PersonInfo queryPersonInfoById(long userId);

    /**
     *
     * @param personInfo
     * @return
     */
    int insertPersonInfo(PersonInfo personInfo);

    /**
     *
     * @param personInfo
     * @return
     */
    int updatePersonInfo(PersonInfo personInfo);

    /**
     *
     * @param userId
     * @return
     */
    int deletePersonInfo(long userId);
}

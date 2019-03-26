package com.hj.o2o.service;

import com.hj.o2o.dto.HeadLineExecution;
import com.hj.o2o.entity.HeadLine;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @Description:
 * @Author: HUJING
 * @Date: 2019/3/20 20:46
 */
public interface HeadLineService {
    public static final String HLLISTKEY = "headlinelist";
    /**
     * 根据传入的条件返回指定的头条列表
     *
     * @param headLineCondition
     * @return
     * @throws IOException
     */
    List<HeadLine> getHeadLineList(HeadLine headLineCondition)
            throws IOException;

//    /**
//     *
//     * @param headLine
//     * @param thumbnail
//     * @return
//     */
//    HeadLineExecution addHeadLine(HeadLine headLine,
//                                  CommonsMultipartFile thumbnail);
//
//    /**
//     *
//     * @param headLine
//     * @param thumbnail
//     * @param thumbnail
//     * @return
//     */
//    HeadLineExecution modifyHeadLine(HeadLine headLine,
//                                     CommonsMultipartFile thumbnail);
//
//    /**
//     *
//     * @param headLineId
//     * @return
//     */
//    HeadLineExecution removeHeadLine(long headLineId);
//
//    /**
//     *
//     * @param headLineIdList
//     * @return
//     */
//    HeadLineExecution removeHeadLineList(List<Long> headLineIdList);
}

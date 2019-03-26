package com.hj.o2o.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hj.o2o.cache.JedisUtil;
import com.hj.o2o.dao.HeadLineDao;
import com.hj.o2o.entity.HeadLine;
import com.hj.o2o.exceptions.HeadLineOperationException;
import com.hj.o2o.service.HeadLineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: HUJING
 * @Date: 2019/3/20 20:50
 */
@Service
public class HeadLineServiceImpl implements HeadLineService {
    @Autowired
    private JedisUtil.Strings jedisStrings;
    @Autowired
    private JedisUtil.Keys jedisKeys;
    @Autowired
    private HeadLineDao headLineDao;

    private static Logger logger = LoggerFactory.getLogger(HeadLineServiceImpl.class);

    @Override
    public List<HeadLine> getHeadLineList(HeadLine headLineCondition) {
        //定义接受对象
        List<HeadLine> headLineList = null;
        //定义jackson数据转换操作类
        ObjectMapper mapper = new ObjectMapper();
        //定义redis的key前缀
        String key = HLLISTKEY;
        //拼接出redis的key
        if (headLineCondition != null && headLineCondition.getEnableStatus() != null) {
            key = key + "_" + headLineCondition.getEnableStatus();
        }
        //判断key是否存在
        if (!jedisKeys.exists(key)) {
            //若不存在，则从数据库里面取出相应数据
            headLineList = headLineDao.queryHeadLine(headLineCondition);
            //将相关的实体类集合转换成string，存入redis里面对应的key中
            String jsonString ;
            try {
                jsonString = mapper.writeValueAsString(headLineList);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
                throw new HeadLineOperationException(e.getMessage());
            }
            jedisStrings.set(key, jsonString);
        } else {
            //若存在，则直接从redis里面取出相应数据
            String jsonString = jedisStrings.get(key);
            //指定要将string转换成的集合类型
            JavaType javaType = mapper.getTypeFactory()
                    .constructParametricType(ArrayList.class, HeadLine.class);
            try {
                //将相关key对应的value里的string转换成对应的实体类集合
                headLineList = mapper.readValue(jsonString, javaType);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return headLineList;
    }

    //    @Override
    //    @Transactional
    //    public HeadLineExecution addHeadLine(HeadLine headLine,
    //                                         CommonsMultipartFile thumbnail) {
    //        if (headLine != null) {
    //            headLine.setCreateTime(new Date());
    //            headLine.setLastEditTime(new Date());
    //            if (thumbnail != null) {
    //                addThumbnail(headLine, thumbnail);
    //            }
    //            try {
    //                int effectedNum = headLineDao.insertHeadLine(headLine);
    //                if (effectedNum > 0) {
    //                    String prefix = HLLISTKEY;
    //                    Set<String> keySet = jedisKeys.keys(prefix + "*");
    //                    for (String key : keySet) {
    //                        jedisKeys.del(key);
    //                    }
    //                    return new HeadLineExecution(HeadLineStateEnum.SUCCESS,
    //                            headLine);
    //                } else {
    //                    return new HeadLineExecution(HeadLineStateEnum.INNER_ERROR);
    //                }
    //            } catch (Exception e) {
    //                throw new RuntimeException("添加区域信息失败:" + e.toString());
    //            }
    //        } else {
    //            return new HeadLineExecution(HeadLineStateEnum.EMPTY);
    //        }
    //    }
    //
    //    @Override
    //    @Transactional
    //    public HeadLineExecution modifyHeadLine(HeadLine headLine,
    //                                            CommonsMultipartFile thumbnail) {
    //        if (headLine.getLineId() != null && headLine.getLineId() > 0) {
    //            headLine.setLastEditTime(new Date());
    //            if (thumbnail != null) {
    //                HeadLine tempHeadLine = headLineDao.queryHeadLineById(headLine
    //                        .getLineId());
    //                if (tempHeadLine.getLineImg() != null) {
    //                    FileUtil.deleteFile(tempHeadLine.getLineImg());
    //                }
    //                addThumbnail(headLine, thumbnail);
    //            }
    //            try {
    //                int effectedNum = headLineDao.updateHeadLine(headLine);
    //                if (effectedNum > 0) {
    //                    String prefix = HLLISTKEY;
    //                    Set<String> keySet = jedisKeys.keys(prefix + "*");
    //                    for (String key : keySet) {
    //                        jedisKeys.del(key);
    //                    }
    //                    return new HeadLineExecution(HeadLineStateEnum.SUCCESS,
    //                            headLine);
    //                } else {
    //                    return new HeadLineExecution(HeadLineStateEnum.INNER_ERROR);
    //                }
    //            } catch (Exception e) {
    //                throw new RuntimeException("更新头条信息失败:" + e.toString());
    //            }
    //        } else {
    //            return new HeadLineExecution(HeadLineStateEnum.EMPTY);
    //        }
    //    }
    //
    //    @Override
    //    @Transactional
    //    public HeadLineExecution removeHeadLine(long headLineId) {
    //        if (headLineId > 0) {
    //            try {
    //                HeadLine tempHeadLine = headLineDao
    //                        .queryHeadLineById(headLineId);
    //                if (tempHeadLine.getLineImg() != null) {
    //                    FileUtil.deleteFile(tempHeadLine.getLineImg());
    //                }
    //                int effectedNum = headLineDao.deleteHeadLine(headLineId);
    //                if (effectedNum > 0) {
    //                    String prefix = HLLISTKEY;
    //                    Set<String> keySet = jedisKeys.keys(prefix + "*");
    //                    for (String key : keySet) {
    //                        jedisKeys.del(key);
    //                    }
    //                    return new HeadLineExecution(HeadLineStateEnum.SUCCESS);
    //                } else {
    //                    return new HeadLineExecution(HeadLineStateEnum.INNER_ERROR);
    //                }
    //            } catch (Exception e) {
    //                throw new RuntimeException("删除头条信息失败:" + e.toString());
    //            }
    //        } else {
    //            return new HeadLineExecution(HeadLineStateEnum.EMPTY);
    //        }
    //    }
    //
    //    @Override
    //    @Transactional
    //    public HeadLineExecution removeHeadLineList(List<Long> headLineIdList) {
    //        if (headLineIdList != null && headLineIdList.size() > 0) {
    //            try {
    //                List<HeadLine> headLineList = headLineDao
    //                        .queryHeadLineByIds(headLineIdList);
    //                for (HeadLine headLine : headLineList) {
    //                    if (headLine.getLineImg() != null) {
    //                        FileUtil.deleteFile(headLine.getLineImg());
    //                    }
    //                }
    //                int effectedNum = headLineDao
    //                        .batchDeleteHeadLine(headLineIdList);
    //                if (effectedNum > 0) {
    //                    String prefix = HLLISTKEY;
    //                    Set<String> keySet = jedisKeys.keys(prefix + "*");
    //                    for (String key : keySet) {
    //                        jedisKeys.del(key);
    //                    }
    //                    return new HeadLineExecution(HeadLineStateEnum.SUCCESS);
    //                } else {
    //                    return new HeadLineExecution(HeadLineStateEnum.INNER_ERROR);
    //                }
    //            } catch (Exception e) {
    //                throw new RuntimeException("删除头条信息失败:" + e.toString());
    //            }
    //        } else {
    //            return new HeadLineExecution(HeadLineStateEnum.EMPTY);
    //        }
    //    }

    //    private void addThumbnail(HeadLine headLine, CommonsMultipartFile thumbnail) {
    //        String dest = FileUtil.getHeadLineImagePath();
    //        String thumbnailAddr = ImageUtil.generateNormalImg(thumbnail, dest);
    //        headLine.setLineImg(thumbnailAddr);
    //    }

}

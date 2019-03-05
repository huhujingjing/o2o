package com.hj.o2o.service;

import com.hj.o2o.dto.ShopExecution;
import com.hj.o2o.entity.Shop;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;

/**
 * @Author: HUJING
 * @Date: 2019/3/5 23:17
 * @Version 1.0
 * @Description:
 */
public interface ShopService {
    ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);

    /**
     * 查询该用户下面的店铺信息
     *
     * @param employeeId
     * @return List<Shop>
     * @throws Exception
     */
    ShopExecution getByEmployeeId(long employeeId) throws RuntimeException;

    /**
     * 查询指定店铺信息
     *
     * @param shopId
     * @return Shop shop
     */
    Shop getByShopId(long shopId);

    /**
     * 创建商铺
     *
     * @param shop
     * @return ShopExecution shopExecution
     * @throws Exception
     */
    ShopExecution addShop(Shop shop, File shopImg) throws RuntimeException;

    /**
     * 更新店铺信息（从店家角度）
     *
     * @param shopImg
     * @return
     * @throws RuntimeException
     */
    ShopExecution modifyShop(Shop shop, CommonsMultipartFile shopImg) throws RuntimeException;
}

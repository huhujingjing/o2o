package com.hj.o2o.service;

import com.hj.o2o.dto.ShopExecution;
import com.hj.o2o.entity.Shop;
import com.hj.o2o.exceptions.ShopOperationException;

import java.io.InputStream;

/**
 * @Author: HUJING
 * @Date: 2019/3/5 23:17
 * @Version 1.0
 * @Description:
 */
public interface ShopService {

    /**
     * 通过店铺Id获取店铺信息
     *
     * @param shopId
     * @return Shop shop
     */
    Shop getByShopId(long shopId);

    /**
     * 更新店铺信息（从店家角度）,包括对图片的处理
     * @param shop
     * @param shopImgInputStream
     * @param fileName
     * @return
     * @throws ShopOperationException
     */
    ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream,String fileName) throws ShopOperationException;

    /**
     * 创建商铺
     *
     * @param shop
     * @return ShopExecution shopExecution
     * @throws Exception
     */
    ShopExecution addShop(Shop shop, InputStream shopImgInputStream,String fileName) throws ShopOperationException;
}

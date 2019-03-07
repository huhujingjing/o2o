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

    /**
     * 创建商铺
     *
     * @param shop
     * @return ShopExecution shopExecution
     * @throws Exception
     */
    ShopExecution addShop(Shop shop, CommonsMultipartFile shopImg);
}

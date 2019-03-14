package com.hj.o2o.service;

import com.hj.o2o.entity.ShopCategory;

import java.util.List;

/**
 * @Description:
 * @Author: HUJING
 * @Date: 2019/3/13 17:02
 */
public interface ShopCategoryService {
    List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);
}

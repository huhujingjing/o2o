package com.hj.o2o.service.impl;

import com.hj.o2o.dao.ShopCategoryDao;
import com.hj.o2o.entity.ShopCategory;
import com.hj.o2o.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: HUJING
 * @Date: 2019/3/13 17:04
 */
@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {
    @Autowired
    private ShopCategoryDao shopCategoryDao;
    @Override
    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {
        return shopCategoryDao.queryShopCategory(shopCategoryCondition);
    }
}

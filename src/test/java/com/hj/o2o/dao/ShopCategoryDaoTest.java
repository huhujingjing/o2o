package com.hj.o2o.dao;

import com.hj.o2o.BaseTest;
import com.hj.o2o.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @Description:
 * @Author: HUJING
 * @Date: 2019/3/13 16:51
 */
public class ShopCategoryDaoTest extends BaseTest {
    @Autowired
    private ShopCategoryDao shopCategoryDao;
    @Test
    public void testQueryShopCategory(){
        List<ShopCategory> shopCategoryList = shopCategoryDao.queryShopCategory(null);
        System.out.println(shopCategoryList.size());
//        assertEquals(1,shopCategoryList.size());
//        ShopCategory testCategory = new ShopCategory();
//        ShopCategory parentCategory = new ShopCategory();
//        parentCategory.setShopCategoryId(1L);
//        testCategory.setParent(parentCategory);
//        shopCategoryList = shopCategoryDao.queryShopCategory(testCategory);
//        assertEquals(1,shopCategoryList.size());
//        System.out.println(shopCategoryList.get(0).getShopCategoryName());
    }
}

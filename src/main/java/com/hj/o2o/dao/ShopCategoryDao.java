package com.hj.o2o.dao;

import com.hj.o2o.entity.ShopCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: HUJING
 * @Date: 2019/3/6 21:34
 * @Version 1.0
 * @Description:
 */
public interface ShopCategoryDao {
    /**
     *
     * @param shopCategoryCondition
     * @return
     */
    List<ShopCategory> queryShopCategory(
            @Param("shopCategoryCondition") ShopCategory shopCategoryCondition);

    /**
     *
     * @param shopCategoryId
     * @return
     */
    ShopCategory queryShopCategoryById(long shopCategoryId);

    /**
     *
     * @param shopCategoryIdList
     * @return
     */
    List<ShopCategory> queryShopCategoryByIds(List<Long> shopCategoryIdList);

    /**
     *
     * @param shopCategory
     * @return
     */
    int insertShopCategory(ShopCategory shopCategory);

    /**
     *
     * @param shopCategory
     * @return
     */
    int updateShopCategory(ShopCategory shopCategory);

    /**
     *
     * @param shopCategoryId
     * @return
     */
    int deleteShopCategory(long shopCategoryId);

    /**
     *
     * @param shopCategoryIdList
     * @return
     */
    int batchDeleteShopCategory(List<Long> shopCategoryIdList);
}

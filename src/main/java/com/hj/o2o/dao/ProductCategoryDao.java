package com.hj.o2o.dao;

import com.hj.o2o.entity.ProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Author: HUJING
 * @Date: 2019/3/18 17:03
 */
public interface ProductCategoryDao {

    /**
     * 通过shop id查询店铺商品类别
     * @param shopId
     * @return List<ProductCategory>
     */
    List<ProductCategory> queryProductCategoryList(long shopId);

    /**
     * 批量新增商品类别
     * @param productCategoryList
     * @return
     */
    int batchInsertProductCategory(List<ProductCategory> productCategoryList);

    /**
     * 删除商品类别（初版，即只支持删除尚且没有发布商品的商品类别）
     * @param productCategoryId
     * @param shopId
     * @return
     */
    int deleteProductCategory(
            @Param("productCategoryId") long productCategoryId,
            @Param("shopId") long shopId);
}

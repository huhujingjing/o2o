package com.hj.o2o.service;

import com.hj.o2o.dto.ProductCategoryExecution;
import com.hj.o2o.entity.ProductCategory;
import com.hj.o2o.exceptions.ProductCategoryOperationException;

import java.util.List;

/**
 * @Description:
 * @Author: HUJING
 * @Date: 2019/3/19 14:38
 */
public interface ProductCategoryService {
    /**
     * 查询指定某个店铺下的所有商品类别信息
     * @param shopId
     * @return
     */
    List<ProductCategory> getProductCategoryList(long shopId);

    ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList)
            throws ProductCategoryOperationException;

    /**
     * 将此类别下的商品的类别id置为空，再删除掉该商品类别
     * @param productCategoryId
     * @param shopId
     * @return
     * @throws ProductCategoryOperationException
     */
    ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId)
            throws ProductCategoryOperationException;
}

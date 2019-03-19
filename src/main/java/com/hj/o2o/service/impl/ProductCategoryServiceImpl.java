package com.hj.o2o.service.impl;

import com.hj.o2o.dao.ProductCategoryDao;
import com.hj.o2o.dto.ProductCategoryExecution;
import com.hj.o2o.entity.ProductCategory;
import com.hj.o2o.enums.ProductCategoryStateEnum;
import com.hj.o2o.exceptions.ProductCategoryOperationException;
import com.hj.o2o.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:
 * @Author: HUJING
 * @Date: 2019/3/19 14:42
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Override
    public List<ProductCategory> getProductCategoryList(long shopId) {
        return productCategoryDao.queryProductCategoryList(shopId);
    }

    @Override
    @Transactional
    public ProductCategoryExecution batchAddProductCategory(
            List<ProductCategory> productCategoryList) throws RuntimeException {
        if (productCategoryList != null && productCategoryList.size() > 0) {
            try {
                int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
                if (effectedNum <= 0) {
                    throw new ProductCategoryOperationException("店铺类别创建失败");
                } else {
                    return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
                }

            } catch (Exception e) {
                throw new ProductCategoryOperationException("batchAddProductCategory error: "
                        + e.getMessage());
            }
        } else {
            return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
        }

    }

    @Override
    @Transactional
    public ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId)
            throws RuntimeException {
        //TODO将此商品类别下的商品的类别Id置为空
        //        try {
        //            int effectedNum = productCategoryDao
        //                    .updateProductCategoryToNull(productCategoryId);
        //            if (effectedNum < 0) {
        //                throw new RuntimeException("商品类别更新失败");
        //            }
        //        } catch (Exception e) {
        //            throw new RuntimeException("deleteProductCategory error: "
        //                    + e.getMessage());
        //        }
        try {
            int effectedNum = productCategoryDao.deleteProductCategory(productCategoryId, shopId);
            if (effectedNum <= 0) {
                throw new ProductCategoryOperationException("店铺类别删除失败");
            } else {
                return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
            }
        } catch (Exception e) {
            throw new ProductCategoryOperationException("deleteProductCategory error: "
                    + e.getMessage());
        }
    }
}

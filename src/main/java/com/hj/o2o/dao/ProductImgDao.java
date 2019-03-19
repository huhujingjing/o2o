package com.hj.o2o.dao;

import com.hj.o2o.entity.ProductImg;

import java.util.List;

/**
 * @Description:
 * @Author: HUJING
 * @Date: 2019/3/19 21:22
 */
public interface ProductImgDao {
    List<ProductImg> queryProductImgList(long productId);

    /**
     * 批量添加商品详情图片
     * @param productImgList
     * @return
     */
    int batchInsertProductImg(List<ProductImg> productImgList);

    int deleteProductImgByProductId(long productId);
}

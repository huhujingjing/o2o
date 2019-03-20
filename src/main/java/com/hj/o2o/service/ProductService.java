package com.hj.o2o.service;

import com.hj.o2o.dto.ImageHolder;
import com.hj.o2o.dto.ProductExecution;
import com.hj.o2o.entity.Product;
import com.hj.o2o.exceptions.ProductOperationException;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * @Description:
 * @Author: HUJING
 * @Date: 2019/3/19 22:03
 */
public interface ProductService {
    /**
     * 查询商品列表并分页，可输入的条件有：商品名（模糊），商品状态，商铺Id，商品类别
     * @param productCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize);

    /**
     * 通过商品信息查询唯一的商品信息
     * @param productId
     * @return
     */
    Product getProductById(long productId);

    /**
     * 添加商品信息以及图片处理
     * @param product
     * @param thumbnail
     * @param productImgHolderList
     * @return
     * @throws ProductOperationException
     */
    ProductExecution addProduct(Product product, ImageHolder thumbnail,
                                List<ImageHolder> productImgHolderList)
            throws ProductOperationException;

    /**
     * 修改商品信息以及图片处理
     * @param product
     * @param thumbnail
     * @param productImgHolderList
     * @return
     * @throws RuntimeException
     */
    ProductExecution modifyProduct(Product product, ImageHolder thumbnail,
                                   List<ImageHolder> productImgHolderList) throws ProductOperationException;
}

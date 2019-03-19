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
//    ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize);

//    Product getProductById(long productId);

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
//
//    ProductExecution modifyProduct(Product product, CommonsMultipartFile thumbnail,
//                                   List<CommonsMultipartFile> productImgs) throws RuntimeException;
}

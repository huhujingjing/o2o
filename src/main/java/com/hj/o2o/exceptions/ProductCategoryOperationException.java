package com.hj.o2o.exceptions;

/**
 * @Author: HUJING
 * @Date: 2019/3/19 15:46
 * @Version 1.0
 * @Description:
 */
public class ProductCategoryOperationException extends RuntimeException{


    private static final long serialVersionUID = 8721701866015026977L;

    public ProductCategoryOperationException(String msg){
        super(msg);
    }
}

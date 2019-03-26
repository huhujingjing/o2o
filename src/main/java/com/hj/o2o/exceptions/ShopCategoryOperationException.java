package com.hj.o2o.exceptions;

/**
 * @Author: HUJING
 * @Date: 2019/3/25 19:52
 * @Version 1.0
 * @Description:
 */
public class ShopCategoryOperationException extends RuntimeException{


    private static final long serialVersionUID = -4157516297904987295L;

    public ShopCategoryOperationException(String msg){
        super(msg);
    }
}

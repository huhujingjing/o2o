package com.hj.o2o.exceptions;

/**
 * @Author: HUJING
 * @Date: 2019/3/7 23:19
 * @Version 1.0
 * @Description:
 */
public class ShopOperationException extends RuntimeException{
    private static final long serialVersionUID = 2361446884822298905L;

    public ShopOperationException(String msg){
        super(msg);
    }
}

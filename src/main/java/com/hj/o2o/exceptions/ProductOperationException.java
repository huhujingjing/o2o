package com.hj.o2o.exceptions;

/**
 * @Author: HUJING
 * @Date: 2019/3/19 22:46
 * @Version 1.0
 * @Description:
 */
public class ProductOperationException extends RuntimeException{


    private static final long serialVersionUID = -973613740132997207L;

    public ProductOperationException(String msg){
        super(msg);
    }
}

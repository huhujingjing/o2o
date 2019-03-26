package com.hj.o2o.exceptions;

/**
 * @Author: HUJING
 * @Date: 2019/3/25 17:34
 * @Version 1.0
 * @Description:
 */
public class LocalAuthOperationException extends RuntimeException{


    private static final long serialVersionUID = 2193396507421440288L;

    public LocalAuthOperationException(String msg){
        super(msg);
    }
}

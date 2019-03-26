package com.hj.o2o.exceptions;

/**
 * @Author: HUJING
 * @Date: 2019/3/25 15:27
 * @Version 1.0
 * @Description:
 */
public class WechatAuthOperationException extends RuntimeException{


    private static final long serialVersionUID = -3080901661619783857L;

    public WechatAuthOperationException(String msg){
        super(msg);
    }
}

package com.hj.o2o.util;

/**
 * @Description:
 * @Author: HUJING
 * @Date: 2019/3/18 11:05
 */
public class PageCalculator {
    public static int calculateRowIndex(int pageIndex, int pageSize) {
        return (pageIndex > 0) ? (pageIndex - 1) * pageSize : 0;
    }
}

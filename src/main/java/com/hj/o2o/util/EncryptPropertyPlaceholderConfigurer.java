package com.hj.o2o.util;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * @Author: HUJING
 * @Date: 2019/3/25 16:22
 * @Version 1.0
 * @Description:
 */
public class EncryptPropertyPlaceholderConfigurer extends
        PropertyPlaceholderConfigurer {
    //需要加密的字段数组
    private String[] encryptPropNames = { "jdbc.username", "jdbc.password" };

    /**
     * 对关键的属性进行转换
     * @param propertyName
     * @param propertyValue
     * @return
     */
    @Override
    protected String convertProperty(String propertyName, String propertyValue) {
        if (isEncryptProp(propertyName)) {
            //对已加密的字段进行解密工作
            String decryptValue = DESUtils.getDecryptString(propertyValue);
            return decryptValue;
        } else {
            return propertyValue;
        }
    }

    /**
     * 该属性是否已加密
     * @param propertyName
     * @return
     */
    private boolean isEncryptProp(String propertyName) {
        for (String encryptpropertyName : encryptPropNames) {
            if (encryptpropertyName.equals(propertyName)) {
                return true;
            }
        }
        return false;
    }
}


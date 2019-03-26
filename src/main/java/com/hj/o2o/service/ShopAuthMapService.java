package com.hj.o2o.service;

import com.hj.o2o.dto.ShopAuthMapExecution;
import com.hj.o2o.entity.ShopAuthMap;

/**
 * @Description:
 * @Author: HUJING
 * @Date: 2019/3/24 23:42
 */
public interface ShopAuthMapService {
    /**
     *
     * @param shopId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    ShopAuthMapExecution listShopAuthMapByShopId(Long shopId,
                                                 Integer pageIndex, Integer pageSize);

    /**
     *
     * @param shopAuthMap
     * @return
     * @throws RuntimeException
     */
    ShopAuthMapExecution addShopAuthMap(ShopAuthMap shopAuthMap)
            throws RuntimeException;

    /**
     * 更新授权信息，包括职位等
     *
     * @param shopAuthMap
     * @return
     * @throws RuntimeException
     */
    ShopAuthMapExecution modifyShopAuthMap(ShopAuthMap shopAuthMap) throws RuntimeException;

    /**
     *
     * @param shopAuthMapId
     * @return
     * @throws RuntimeException
     */
    ShopAuthMapExecution removeShopAuthMap(Long shopAuthMapId)
            throws RuntimeException;

    /**
     *
     * @param shopAuthId
     * @return
     */
    ShopAuthMap getShopAuthMapById(Long shopAuthId);
}

package com.hj.o2o.dao;

import com.hj.o2o.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: HUJING
 * @Date: 2019/3/5 20:29
 * @Version 1.0
 * @Description:
 */
public interface ShopDao {
    /**
     * 分页查询店铺,可输入的条件有：店铺名（模糊），店铺状态，店铺Id,店铺类别,区域ID,owner
     *
     * @param shopCondition
     * @param rowIndex  从第几行开始取数据
     * @param pageSize  返回的条数
     * @return
     */
    List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition,
                             @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

    /**
     * 返回queryShopList总数
     *
     * @param shopCondition
     * @return
     */
    int queryShopCount(@Param("shopCondition") Shop shopCondition);

    /**
     * 通过employee id 查询店铺
     *
     * @param employeeId
     * @return List<shop>
     */
    List<Shop> queryByEmployeeId(long employeeId);

    /**
     * 通过owner id 查询店铺
     *
     * @param shopId
     * @return shop
     */
    Shop queryByShopId(long shopId);

    /**
     * 新增店铺
     *
     * @param shop
     * @return effectedNum
     */
    int insertShop(Shop shop);

    /**
     * 更新店铺信息
     *
     * @param shop
     * @return effectedNum
     */
    int updateShop(Shop shop);

    /**
     * 删除店铺（初版，即只支持删除尚且没有发布商品的店铺）
     *
     * @param shopName
     * @return effectedNum
     */
    int deleteShopByName(String shopName);
}

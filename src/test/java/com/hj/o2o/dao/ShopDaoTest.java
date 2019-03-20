package com.hj.o2o.dao;

import com.hj.o2o.BaseTest;
import com.hj.o2o.entity.Area;
import com.hj.o2o.entity.PersonInfo;
import com.hj.o2o.entity.Shop;
import com.hj.o2o.entity.ShopCategory;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @Author: HUJING
 * @Date: 2019/3/5 20:41
 * @Version 1.0
 * @Description:
 */
public class ShopDaoTest extends BaseTest {
    @Autowired
    private ShopDao shopDao;

    @Test
    public void testQueryShopListAndCount() {
        Shop shopCondition = new Shop();
//        PersonInfo owner = new PersonInfo();
//        owner.setUserId(1L);
//        shopCondition.setOwner(owner);
        ShopCategory childCategory = new ShopCategory();
        ShopCategory parentCategory = new ShopCategory();
        parentCategory.setShopCategoryId(12L);
        childCategory.setParent(parentCategory);
        shopCondition.setShopCategory(childCategory);
        List<Shop> shopList = shopDao.queryShopList(shopCondition, 0, 6);
        int count = shopDao.queryShopCount(shopCondition);
        System.out.println("店铺列表的大小：" + shopList.size());
        System.out.println("店铺总数：" + count);
//        ShopCategory sc = new ShopCategory();
//        sc.setShopCategoryId(3L);
//        shopCondition.setShopCategory(sc);
//        shopList = shopDao.queryShopList(shopCondition, 0, 2);
//        count = shopDao.queryShopCount(shopCondition);
//        System.out.println("店铺列表的大小：" + shopList.size());
//        System.out.println("店铺总数：" + count);
    }

    @Test
    @Ignore
    public void testAInsertShop() throws Exception {
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(1L);
        shopCategory.setShopCategoryId(1L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试的店铺5");
        shop.setShopDesc("test5");
        shop.setShopAddr("test5");
        shop.setPhone("test5");
        shop.setShopImg("test5");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvice("审核中");
        int effectedNum = shopDao.insertShop(shop);
        assertEquals(1, effectedNum);
    }

    @Test
    @Ignore
    public void testQueryByShopId() {
        long shopId = 1;
        Shop shop = shopDao.queryByShopId(shopId);
        System.out.println("areaId:" + shop.getArea().getAreaId());
        System.out.println("areaName:" + shop.getArea().getAreaName());
    }

    //
    //    @Test
    //    public void testBQueryByEmployeeId() throws Exception {
    //        long employeeId = 1;
    //        List<Shop> shopList = shopDao.queryByEmployeeId(employeeId);
    //        for (Shop shop : shopList) {
    //            System.out.println(shop);
    //        }
    //    }
    //
    //    @Test
    //    public void testBQueryShopList() throws Exception {
    //        Shop shop = new Shop();
    //        List<Shop> shopList = shopDao.queryShopList(shop, 0, 2);
    //        assertEquals(2, shopList.size());
    //        int count = shopDao.queryShopCount(shop);
    //        assertEquals(3, count);
    //        shop.setShopName("花");
    //        shopList = shopDao.queryShopList(shop, 0, 3);
    //        assertEquals(2, shopList.size());
    //        count = shopDao.queryShopCount(shop);
    //        assertEquals(2, count);
    //        shop.setShopId(1L);
    //        shopList = shopDao.queryShopList(shop, 0, 3);
    //        assertEquals(1, shopList.size());
    //        count = shopDao.queryShopCount(shop);
    //        assertEquals(1, count);
    //
    //    }
    //
    //    @Test
    //    public void testCQueryByShopId() throws Exception {
    //        long shopId = 1;
    //        Shop shop = shopDao.queryByShopId(shopId);
    //        System.out.println(shop);
    //    }
    //
    @Test
    @Ignore
    public void testUpdateShop() {
        Shop shop = new Shop();
        shop.setShopId(2L);
        shop.setShopDesc("测试描述");
        shop.setShopAddr("测试地址");
        shop.setLastEditTime(new Date());
        int effectedNum = shopDao.updateShop(shop);
        assertEquals(1, effectedNum);
    }
    //
    //    @Test
    //    public void testEDeleteShopByName() throws Exception {
    //        String shopName = "mytest1";
    //        int effectedNum = shopDao.deleteShopByName(shopName);
    //        assertEquals(1, effectedNum);
    //

}

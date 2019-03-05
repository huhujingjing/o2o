package com.hj.o2o.dao;

import com.hj.o2o.BaseTest;
import com.hj.o2o.entity.Area;
import com.hj.o2o.entity.PersonInfo;
import com.hj.o2o.entity.Shop;
import com.hj.o2o.entity.ShopCategory;
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
    public void testAInsertShop() throws Exception {
        Shop shop = new Shop();
        shop.setOwnerId(1L);
        Area area = new Area();
        area.setAreaId(1);
        ShopCategory sc = new ShopCategory();
        sc.setShopCategoryId(1L);
        shop.setShopName("mytest1");
        shop.setShopDesc("mytest1");
        shop.setShopAddr("testaddr1");
        shop.setPhone("15372026611");
        shop.setShopImg("test1");
        shop.setLongitude(1D);
        shop.setLatitude(1D);
        shop.setCreateTime(new Date());
        shop.setLastEditTime(new Date());
        shop.setEnableStatus(0);
        shop.setAdvice("审核中");
        shop.setArea(area);
        shop.setShopCategory(sc);
        int effectedNum = shopDao.insertShop(shop);
        assertEquals(1, effectedNum);
    }

    @Test
    public void testBQueryByEmployeeId() throws Exception {
        long employeeId = 1;
        List<Shop> shopList = shopDao.queryByEmployeeId(employeeId);
        for (Shop shop : shopList) {
            System.out.println(shop);
        }
    }

    @Test
    public void testBQueryShopList() throws Exception {
        Shop shop = new Shop();
        List<Shop> shopList = shopDao.queryShopList(shop, 0, 2);
        assertEquals(2, shopList.size());
        int count = shopDao.queryShopCount(shop);
        assertEquals(3, count);
        shop.setShopName("花");
        shopList = shopDao.queryShopList(shop, 0, 3);
        assertEquals(2, shopList.size());
        count = shopDao.queryShopCount(shop);
        assertEquals(2, count);
        shop.setShopId(1L);
        shopList = shopDao.queryShopList(shop, 0, 3);
        assertEquals(1, shopList.size());
        count = shopDao.queryShopCount(shop);
        assertEquals(1, count);

    }

    @Test
    public void testCQueryByShopId() throws Exception {
        long shopId = 1;
        Shop shop = shopDao.queryByShopId(shopId);
        System.out.println(shop);
    }

    @Test
    public void testDUpdateShop() {
        long shopId = 1;
        Shop shop = shopDao.queryByShopId(shopId);
        Area area = new Area();
        area.setAreaId(1);
        shop.setArea(area);
        ShopCategory shopCategory = new ShopCategory();
        shopCategory.setShopCategoryId(1L);
        shop.setShopCategory(shopCategory);
        shop.setShopName("四季花");
        int effectedNum = shopDao.updateShop(shop);
        assertEquals(1, effectedNum);
    }

    @Test
    public void testEDeleteShopByName() throws Exception {
        String shopName = "mytest1";
        int effectedNum = shopDao.deleteShopByName(shopName);
        assertEquals(1, effectedNum);

    }
}

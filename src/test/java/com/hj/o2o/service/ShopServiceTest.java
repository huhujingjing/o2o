package com.hj.o2o.service;

import com.hj.o2o.BaseTest;
import com.hj.o2o.dto.ShopExecution;
import com.hj.o2o.entity.Area;
import com.hj.o2o.entity.PersonInfo;
import com.hj.o2o.entity.Shop;
import com.hj.o2o.entity.ShopCategory;
import com.hj.o2o.enums.ShopStateEnum;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @Author: HUJING
 * @Date: 2019/3/5 23:45
 * @Version 1.0
 * @Description:
 */
public class ShopServiceTest extends BaseTest {
    @Autowired
    private ShopService shopService;

	@Test
	public void testAddShop() throws Exception {
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(1L);
        shopCategory.setShopCategoryId(1L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试的店铺");
        shop.setShopDesc("test");
        shop.setShopAddr("test");
        shop.setPhone("test");
        shop.setShopImg("test");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中");
        File shopImg = new File("D:\\学习文档\\类\\hj.jpg");
        ShopExecution se = shopService.addShop(shop,shopImg);
        assertEquals(ShopStateEnum.CHECK.getState(), se.getState());
	}



}

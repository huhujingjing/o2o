package com.hj.o2o.service;

import com.hj.o2o.BaseTest;
import com.hj.o2o.dto.ImageHolder;
import com.hj.o2o.dto.ShopExecution;
import com.hj.o2o.entity.Area;
import com.hj.o2o.entity.PersonInfo;
import com.hj.o2o.entity.Shop;
import com.hj.o2o.entity.ShopCategory;
import com.hj.o2o.enums.ShopStateEnum;
import com.hj.o2o.exceptions.ShopOperationException;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
    public void testQueryShopListAndCount() {
        Shop shopCondition = new Shop();
        ShopCategory sc = new ShopCategory();
        sc.setShopCategoryId(3L);
        shopCondition.setShopCategory(sc);
        ShopExecution se = shopService.getShopList(shopCondition, 2, 2);
        System.out.println("店铺列表数为：" + se.getShopList().size());
        System.out.println("店铺总数：" + se.getCount());
    }

    @Ignore
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
        shop.setShopName("测试的店铺3");
        shop.setShopDesc("test3");
        shop.setShopAddr("test3");
        shop.setPhone("test3");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中");
        File shopImg = new File("D:\\学习文档\\类\\hj.jpg");
        InputStream is = new FileInputStream(shopImg);
        ImageHolder imageHolder = new ImageHolder(shopImg.getName(),is);
        ShopExecution se = shopService.addShop(shop,imageHolder);
        assertEquals(ShopStateEnum.CHECK.getState(), se.getState());
    }

    @Ignore
    @Test
    public void testModifyShop() throws ShopOperationException, FileNotFoundException {
        Shop shop = new Shop();
        shop.setShopId(1L);
        shop.setShopName("修改后的店铺名称");
        File shopImg = new File("D:\\D盘文件\\图片\\PROJECT2017_wp-8_1920x1080.jpg");
        InputStream is = new FileInputStream(shopImg);
        ImageHolder imageHolder = new ImageHolder("PROJECT2017_wp-8_1920x1080.jpg",is);
        ShopExecution shopExecution = shopService.modifyShop(shop, imageHolder);
        System.out.println("新的图片地址为："+shopExecution.getShop().getShopImg());
    }

//    public FileItem createFileItem(String filePath) {
//        FileItemFactory factory = new DiskFileItemFactory(16, null);
//        String textFieldName = "textField.jpg";
//        int num = filePath.lastIndexOf(".");
//        String extFile = filePath.substring(num);
//        FileItem item = factory.createItem(textFieldName, "text/plain", true, "MyFileName.jpg");
//        File newfile = new File(filePath);
//        int bytesRead = 0;
//        byte[] buffer = new byte[8192];
//        try {
//            FileInputStream fis = new FileInputStream(newfile);
//            OutputStream os = item.getOutputStream();
//            while ((bytesRead = fis.read(buffer, 0, 8192)) != -1) {
//                os.write(buffer, 0, bytesRead);
//            }
//            os.close();
//            fis.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return item;
//    }


}

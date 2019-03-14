package com.hj.o2o.service;

import com.hj.o2o.BaseTest;
import com.hj.o2o.dto.ShopExecution;
import com.hj.o2o.entity.Area;
import com.hj.o2o.entity.PersonInfo;
import com.hj.o2o.entity.Shop;
import com.hj.o2o.entity.ShopCategory;
import com.hj.o2o.enums.ShopStateEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;

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
        shop.setShopName("测试的店铺3");
        shop.setShopDesc("test3");
        shop.setShopAddr("test3");
        shop.setPhone("test3");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中");
        File shopImg = new File("D:\\学习文档\\类\\hj.jpg");
        InputStream is = new FileInputStream(shopImg);
        ShopExecution se = shopService.addShop(shop,is,shopImg.getName());
        assertEquals(ShopStateEnum.CHECK.getState(), se.getState());
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

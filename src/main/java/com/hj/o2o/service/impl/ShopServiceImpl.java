package com.hj.o2o.service.impl;

import com.hj.o2o.dao.ShopAuthMapDao;
import com.hj.o2o.dao.ShopCategoryDao;
import com.hj.o2o.dao.ShopDao;
import com.hj.o2o.dto.ImageHolder;
import com.hj.o2o.dto.ShopExecution;
import com.hj.o2o.entity.Shop;
import com.hj.o2o.enums.ShopStateEnum;
import com.hj.o2o.exceptions.ShopOperationException;
import com.hj.o2o.service.ShopService;
import com.hj.o2o.util.ImageUtil;
import com.hj.o2o.util.PageCalculator;
import com.hj.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @Author: HUJING
 * @Date: 2019/3/5 23:24
 * @Version 1.0
 * @Description:
 */
@Service
@Transactional
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopDao shopDao;
    @Autowired
    private ShopAuthMapDao shopAuthMapDao;
    @Autowired
    private ShopCategoryDao shopCategoryDao;

    @Override
    public Shop getByShopId(long shopId) {
        return shopDao.queryByShopId(shopId);
    }

    @Override
    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex,pageSize);
        List<Shop> shopList = shopDao.queryShopList(shopCondition,rowIndex,pageSize);
        int count = shopDao.queryShopCount(shopCondition);
        ShopExecution se = new ShopExecution();
        if (shopList != null){
            se.setShopList(shopList);
            se.setCount(count);
        } else {
            se.setState(ShopStateEnum.INNER_ERROR.getState());
        }
        return se;
    }

    @Override
    public ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException {
        if (shop == null || shop.getShopId() == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        } else {
            //1.判断是否需要处理图片
            try {
                if (thumbnail.getImage() != null && thumbnail.getImageName() != null && !"".equals(thumbnail.getImageName())) {
                    Shop tempShop = shopDao.queryByShopId(shop.getShopId());
                    if (tempShop.getShopImg() != null) {
                        ImageUtil.deleteFileOrPath(tempShop.getShopImg());
                    }
                    addShopImg(shop, thumbnail);
                }
                //2.更新店铺信息
                shop.setLastEditTime(new Date());
                int effectedNum = shopDao.updateShop(shop);
                if (effectedNum <= 0) {
                    return new ShopExecution(ShopStateEnum.INNER_ERROR);
                } else {
                    shop = shopDao.queryByShopId(shop.getShopId());
                    return new ShopExecution(ShopStateEnum.SUCCESS, shop);
                }
            } catch (Exception e) {
                throw new ShopOperationException("modifyShop error:" + e.getMessage());
            }
        }
    }

    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, ImageHolder thumbnail) {
        //空值判断
        if (shop == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        try {
            //给店铺信息赋初始值
            shop.setEnableStatus(0);
            shop.setAdvice("审核中");
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            //添加店铺信息
            int effectedNum = shopDao.insertShop(shop);
            if (effectedNum <= 0) {
                throw new RuntimeException("店铺创建失败");
            } else {
                if (thumbnail.getImage() != null) {
                    //存储图片
                    try {
                        addShopImg(shop,thumbnail);
                    } catch (Exception e) {
                        throw new RuntimeException("addShop error" + e.getMessage());
                    }
                    //更新店铺的图片地址
                    effectedNum = shopDao.updateShop(shop);
                    if (effectedNum <= 0) {
                        throw new RuntimeException("更新图片地址失败");
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("addShop error" + e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK, shop);
    }

    private void addShopImg(Shop shop, ImageHolder thumbnail) {
        //获取shop图片目录的相对值路径
        String dest = PathUtil.getShopImagePath(shop.getShopId());
        String shopImgAddr = ImageUtil.generateThumbnail(thumbnail, dest);
        shop.setShopImg(shopImgAddr);
    }
}

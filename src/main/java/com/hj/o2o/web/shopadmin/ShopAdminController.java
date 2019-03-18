package com.hj.o2o.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: HUJING
 * @Date: 2019/3/6 21:37
 * @Version 1.0
 * @Description:
 */
@Controller
@RequestMapping(value = "/shopadmin",method = RequestMethod.GET)
public class ShopAdminController {
    @RequestMapping(value = "/shopoperation")
    public String shopOperation(){
        return "shop/shopoperation";
    }

    @RequestMapping(value = "/shoplist")
    public String shopList(){
        return "shop/shoplist";
    }

    @RequestMapping(value = "/shopmanagement")
    public String shopManagement(){
        return "shop/shopmanagement";
    }
}

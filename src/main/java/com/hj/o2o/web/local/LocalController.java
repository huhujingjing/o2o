package com.hj.o2o.web.local;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description:
 * @Author: HUJING
 * @Date: 2019/3/25 23:34
 */
@Controller
@RequestMapping("/local")
public class LocalController {
    /**
     * 绑定账号路由
     * @return
     */
    @RequestMapping(value = "/accountbind",method = RequestMethod.GET)
    private String accountBind(){
        return "local/accountbind";
    }

    /**
     * 修改密码路由
     * @return
     */
    @RequestMapping(value = "/changepsw",method = RequestMethod.GET)
    private String changepsw(){
        return "local/changepsw";
    }

    /**
     * 登录页路由
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    private String login(){
        return "local/login";
    }
}

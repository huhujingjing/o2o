package com.hj.o2o.web.wechat;

import com.hj.o2o.dto.ShopExecution;
import com.hj.o2o.dto.UserAccessToken;
import com.hj.o2o.dto.WechatAuthExecution;
import com.hj.o2o.dto.WechatUser;
import com.hj.o2o.entity.PersonInfo;
import com.hj.o2o.entity.WechatAuth;
import com.hj.o2o.enums.WechatAuthStateEnum;
import com.hj.o2o.service.PersonInfoService;
import com.hj.o2o.service.ShopAuthMapService;
import com.hj.o2o.service.ShopService;
import com.hj.o2o.service.WechatAuthService;
import com.hj.o2o.util.weixin.WechatUserUtil;
import com.hj.o2o.util.weixin.WechatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description:从微信菜单点击后调用的接口，可以在url里增加参数（role_type）来表明是从商家还是从玩家按钮进来的，依次区分登陆后跳转不同的页面 玩家会跳转到index.html页面
 * 商家如果没有注册，会跳转到注册页面，否则跳转到任务管理页面
 * 如果是商家的授权用户登陆，会跳到授权店铺的任务管理页面
 * https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxa887b3d1b0c1a1cc&redirect_uri=http://47.102.216.95/o2o/wechatlogin/logincheck&role_type=1&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect
 * @Author: HUJING
 * @Date: 2019/3/24 22:41
 */
@Controller
@RequestMapping("wechatlogin")
public class WechatLoginController {

    private static Logger log = LoggerFactory
            .getLogger(WechatLoginController.class);

    @Autowired
    private PersonInfoService personInfoService;
    @Autowired
    private WechatAuthService wechatAuthService;

    //    @Resource
    //    private ShopService shopService;

    //    @Resource
    //    private ShopAuthMapService shopAuthMapService;

    private static final String FRONTEND = "1";
    private static final String SHOPEND = "2";

    @RequestMapping(value = "/logincheck", method = { RequestMethod.GET })
    public String doGet(HttpServletRequest request, HttpServletResponse response) {
        log.debug("weixin login get...");
        //获取微信公众号传输过来的code，通过code可获取access_token,进而获取用户信息
        String code = request.getParameter("code");
        //这个state可以用来传我们自定义的信息，方便程序调用，这里也可以不用
        String roleType = request.getParameter("state");
        log.debug("weixin login code:" + code);
        WechatUser user = null;
        String openId = null;
        WechatAuth auth = null;
        if (null != code) {
            UserAccessToken token;
            try {
                //通过code获取access_token
                token = WechatUtil.getUserAccessToken(code);
                log.debug("weixin login token:" + token.toString());
                //通过token获取accessToken
                String accessToken = token.getAccessToken();
                //通过token获取openId
                openId = token.getOpenId();
                //通过access_token和openId获取用户昵称等信息
                user = WechatUserUtil.getUserInfo(accessToken, openId);
                log.debug("weixin login user:" + user.toString());
                request.getSession().setAttribute("openId", openId);
                auth = wechatAuthService.getWechatAuthByOpenId(openId);
                //                auth = WechatAuthService.getWechatAuthByOpenId(openId);
            } catch (IOException e) {
                log.error("error in getUserAccessToken or getUserInfo or findByOpenId: "
                        + e.toString());
                e.printStackTrace();
            }
        }
        log.debug("weixin login success.");
        log.debug("login role_type:" + roleType);
        if (auth == null) {
            PersonInfo personInfo = WechatUtil.getPersonInfoFromRequest(user);
            auth = new WechatAuth();
            auth.setOpenId(openId);
            if (FRONTEND.equals(roleType)) {
                personInfo.setUserType(1);
            } else {
                personInfo.setUserType(2);
            }
            auth.setPersonInfo(personInfo);
            WechatAuthExecution we = wechatAuthService.register(auth);
            if (we.getState() != WechatAuthStateEnum.SUCCESS.getState()) {
                return null;
            } else {
                personInfo = personInfoService.getPersonInfoById(auth.getPersonInfo().getUserId());
                request.getSession().setAttribute("user", personInfo);
            }
        }
        //若用户点击的是前端展示系统按钮则进入前端展示系统
        if (FRONTEND.equals(roleType)) {
            return "frontend/index";
        } else {
            return "shopadmin/shoplist";
        }
    }
}

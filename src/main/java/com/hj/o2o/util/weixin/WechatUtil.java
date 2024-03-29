package com.hj.o2o.util.weixin;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hj.o2o.dto.UserAccessToken;
import com.hj.o2o.dto.WechatUser;
import com.hj.o2o.entity.PersonInfo;
import com.hj.o2o.util.weixin.message.pojo.AccessToken;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Description:
 * @Author: HUJING
 * @Date: 2019/3/24 23:02
 */
public class WechatUtil {
    private static Logger log = LoggerFactory.getLogger(WechatUtil.class);

    public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod)) {
                httpUrlConn.connect();
            }

            // 当有数据需要提交时
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            log.debug("http buffer:" + buffer.toString());
            jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (ConnectException ce) {
            log.error("Weixin server connection timed out.");
        } catch (JSONException e) {
            String result = buffer.toString();
            int begin = result.indexOf("(");
            int end = result.indexOf(")");
            String content = result.substring(begin + 1, end);
            return JSONObject.fromObject(content);

        } catch (Exception e) {
            log.error("https request error:{}", e);
        }
        return jsonObject;
    }

    /**
     * 发起https请求并获取结果
     *
     * @param requestUrl    请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr     提交的数据
     * @return JSONObject(通过JSONObject.get ( key)的方式获取json对象的属性值)
     */
    public static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = { new MyX509TrustManager() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod)) {
                httpUrlConn.connect();
            }

            // 当有数据需要提交时
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            log.debug("https buffer:" + buffer.toString());
//            jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (ConnectException ce) {
            log.error("Weixin server connection timed out.");
        } catch (Exception e) {
            log.error("https request error:{}", e);
        }
        return buffer.toString();
    }

    // 获取access_token的接口地址（GET） 限200（次/天）
    public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    /**
     * 获取access_token String appid, String appsecret,  appid 凭证 appsecret 密钥
     *
     * @param
     * @param
     * @return
     */
    public static UserAccessToken getUserAccessToken(String code) {
        //        AccessToken accessToken = null;
        //测试号信息里的appId
        String appid = "wxa887b3d1b0c1a1cc";
        log.debug("appId:" + appid);
        //测试号信息里的appsecret
        String appsecret = "8bb867ac04251d7ebbaa30c5087de858";
        log.debug("appsecret:" + appsecret);
        //        String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret) +
        //                "&code=" + code + "&grant_type=authorization_code";
        String requestUrl = "//open.weixin.qq.com/connnect/oauth2/authorize?appid=wxa887b3d1b0c1a1cc&sectet=" + appsecret +
                "&code=" + code + "&grant_type=authorization_code";
        //向相应URL发送请求获取token json字符串
        //        JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);
        String tokenStr = httpsRequest(requestUrl, "GET", null);
        UserAccessToken token = new UserAccessToken();
        ObjectMapper objectMapper = new ObjectMapper();
        // 如果请求成功
        try {
            //将json字符串转换成相应对象
            token = objectMapper.readValue(tokenStr,UserAccessToken.class);
        } catch (JsonParseException e){
            log.error("获取用户accessToken失败:" + e.getMessage());
            e.printStackTrace();
        } catch (JsonMappingException e){
            log.error("获取用户accessToken失败:" + e.getMessage());
            e.printStackTrace();
        } catch (IOException e){
            log.error("获取用户accessToken失败:" + e.getMessage());
            e.printStackTrace();
        }
        if (token == null){
            return  null;
        }

        //        if (null != jsonObject) {
        //            try {
        //                accessToken = new AccessToken();
        //                accessToken.setToken(jsonObject.getString("access_token"));
        //                accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
        //            } catch (Exception e) {
        //                accessToken = null;
        //                // 获取token失败
        //                log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
        //            }
        //        }
        return token;
    }

    /**
     * 将WechatUser里的信息转换成PersonIndfo的信息并返回PersonInfo实体类
     * @param user
     * @return
     */
    public static PersonInfo getPersonInfoFromRequest(WechatUser user){
        PersonInfo personInfo = new PersonInfo();
        personInfo.setName(user.getNickName());
        personInfo.setGender(user.getSex() + "");
        personInfo.setProfileImg(user.getHeadimgurl());
        personInfo.setEnableStatus(1);
        return personInfo;
    }

    // 菜单创建（POST） 限100（次/天）
    public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    /**
     * 创建菜单
     *
     * @param menu 菜单实例
     * @param accessToken 有效的access_token
     * @return 0表示成功，其他值表示失败
     */
    //    public static int createMenu(Menu menu, String accessToken) {
    //        int result = 0;
    //        // 拼装创建菜单的url
    //        String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
    //        // 将菜单对象转换成json字符串
    //        String jsonMenu = JSONObject.fromObject(menu).toString();
    //        // 调用接口创建菜单
    //        JSONObject jsonObject = httpsRequest(url, "POST", jsonMenu);
    //
    //        if (null != jsonObject) {
    //            if (0 != jsonObject.getInt("errcode")) {
    //                result = jsonObject.getInt("errcode");
    //                log.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
    //            }
    //        }
    //
    //        return result;
    //    }
}

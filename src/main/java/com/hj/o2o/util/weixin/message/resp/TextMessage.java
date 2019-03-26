package com.hj.o2o.util.weixin.message.resp;

import com.hj.o2o.util.baidu.Point;

/**
 * @Description:
 * @Author: HUJING
 * @Date: 2019/3/24 18:52
 */
public class TextMessage {
    // 回复的消息内容
    private String Content;
    //坐标点
    private Point point;
    //openId
    private String openId;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}

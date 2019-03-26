package com.hj.o2o.util.weixin.message.resp;

import java.util.List;

/**
 * @Description:
 * @Author: HUJING
 * @Date: 2019/3/25 0:05
 */
public class NewsMessage extends BaseMessage{
    // 图文消息个数，限制为10条以内
    private int ArticleCount;
    // 多条图文消息信息，默认第一个item为大图
    private List<Article> Articles;

    public int getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(int articleCount) {
        ArticleCount = articleCount;
    }

    public List<Article> getArticles() {
        return Articles;
    }

    public void setArticles(List<Article> articles) {
        Articles = articles;
    }
}

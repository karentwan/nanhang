package cn.karent.nanhang.model;

/**
 * Created by wan on 2016/12/24.
 * 新闻的model
 */
public class News {

    /**
     * 图片地址
     */
    private String picUrl;
    /**
     * 标题
     */
    private String title;
    /**
     * 时间
     */
    private String time;
    /**
     * 阅读次数
     */
    private String count;

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}

package cn.karent.nanhang.UI;

import android.app.Activity;
import android.util.Log;
import android.widget.ListView;
import cn.karent.nanhang.R;
import cn.karent.nanhang.adapter.NewsAdapter;
import cn.karent.nanhang.model.News;

/**
 * Created by wan on 2016/12/24.
 * 将新闻列表的常用操作封装在这里
 */
public class NewsUI {

    private Activity mActivity;

    private ListView mListView;

    private NewsAdapter mNewsAdapter;

    public NewsUI(Activity activity) {
        mActivity = activity;
        mListView = (ListView)activity.findViewById(R.id.news_list);
        mNewsAdapter = new NewsAdapter(activity, R.layout.news_item_layout);
Log.d("mNewsAdapter", mNewsAdapter.toString());
        mListView.setAdapter(mNewsAdapter);
        fillContent();
    }

    /**
     * 给ListView填充新闻
     */
    public void fillContent() {
        News  n = new News();
        n.setCount("阅读次数：322");
        n.setTitle("12月26日华东理工大学黎野平教授来校讲学");
        n.setTime("2016/12/23/ 15:21:03");
        mNewsAdapter.add(n);
        mNewsAdapter.add(n);
        mNewsAdapter.add(n);
        mNewsAdapter.add(n);
        mNewsAdapter.add(n);
        mNewsAdapter.add(n);
        mNewsAdapter.add(n);
        mNewsAdapter.add(n);
        mNewsAdapter.add(n);
        mNewsAdapter.add(n);
        mNewsAdapter.add(n);
        mNewsAdapter.add(n);
        mNewsAdapter.add(n);
        mNewsAdapter.add(n);
        mNewsAdapter.add(n);
        mNewsAdapter.add(n);
    }





}

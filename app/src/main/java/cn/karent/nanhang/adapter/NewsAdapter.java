package cn.karent.nanhang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.karent.nanhang.model.News;
import cn.karent.nanhang.R;

/**
 * Created by wan on 2016/12/24.
 * 新闻列表的Adapter
 */
public class NewsAdapter extends ArrayAdapter<News> {
    /**
     * 保存的是layout的id,例如R.layout.news_item_layout
     */
    private int mResourceId;

    private Context mContext;

    public NewsAdapter(Context context, int resourceId) {
        super(context, resourceId);
        mResourceId = resourceId;
        mContext = context;
    }

    /**
     * 加载每一项的布局
     * @param position     列表的位置
     * @param convertView 移除屏幕外回收的convertView
     * @param parent       这里是ListView
     * @return             ListView里面的列表项
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        News n = getItem(position);
        Hold h;
        if( convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(mResourceId, null);
            h = new Hold();
            h.pic = (ImageView) convertView.findViewById(R.id.news_item_pic);
            h.title = (TextView)convertView.findViewById(R.id.news_item_title);
            h.time = (TextView)convertView.findViewById(R.id.news_item_time);
            h.count = (TextView)convertView.findViewById(R.id.news_item_count);
            convertView.setTag(h);
        } else {
            h = (Hold)convertView.getTag();
        }
        h.getCount().setText(n.getCount());
        h.getTime().setText(n.getTime());
        h.getTitle().setText(n.getTitle());
        return convertView;
    }

    class Hold {

        private ImageView pic;

        private TextView title;

        private TextView time;

        private TextView count;

        public ImageView getPic() {
            return pic;
        }

        public void setPic(ImageView pic) {
            this.pic = pic;
        }

        public TextView getTitle() {
            return title;
        }

        public void setTitle(TextView title) {
            this.title = title;
        }

        public TextView getTime() {
            return time;
        }

        public void setTime(TextView time) {
            this.time = time;
        }

        public TextView getCount() {
            return count;
        }

        public void setCount(TextView count) {
            this.count = count;
        }
    }

}

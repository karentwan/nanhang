package cn.karent.nanhang.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import cn.karent.nanhang.R;

/**
 * Created by wan on 2016/12/27.
 */
public class WeekAdapter extends ArrayAdapter<String> {

    private Context mContext;

    private int mResourceId;
    /**
     * 显示成绩的Activity中显示当前年份的TextView
     */
    private TextView mTextView;

    public WeekAdapter(Context context, int resourceId) {
        super(context, resourceId);
        mResourceId = resourceId;
        mContext = context;
    }

    public void setWeekTextView(TextView textView) {
        mTextView = textView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if( convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.check_week_layout, null);
        }
        TextView t = (TextView)convertView.findViewById(R.id.check_week_week);
        String s = getItem(position);
        //如果当前要加载的周数等于TextView里面的周数，那么改变背景色
        if( s.equals(mTextView.getText())) {
            t.setBackground(new ColorDrawable(0xffffffff));
            t.setTextColor(Color.rgb(0, 0, 0));
        } else {
            t.setBackground(null);
            t.setTextColor(Color.rgb(255, 255, 255));
        }
        t.setText(s);
        return convertView;
    }
}

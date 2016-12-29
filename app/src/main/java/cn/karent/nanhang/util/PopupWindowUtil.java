package cn.karent.nanhang.util;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import cn.karent.nanhang.R;

/**
 * Created by wan on 2016/12/28.
 * 创建一个PopupWindow的
 */
public class PopupWindowUtil {


    /**
     * 创建PopupWindow
     * @param activity 要创建PopupWindow的Activity
     * @param width    popupWindow的宽度
     * @param l        listView的监听器
     * @return         一个创建好了的PopupWindow
     */
    public static PopupWindow createPopupWindow(Activity activity, int width, BaseAdapter adapter, final ItemClickListener l) {
        PopupWindow checkWeekPopupWindow = new PopupWindow(activity);
        View contentView = LayoutInflater.from(activity).inflate(R.layout.checkweek_popup_layout, null);
        checkWeekPopupWindow.setContentView(contentView);
        checkWeekPopupWindow.setWidth(width);
        checkWeekPopupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        ListView list = (ListView) contentView.findViewById(R.id.checkweek_list);
        //设置Adapter
        list.setAdapter(adapter);
        checkWeekPopupWindow.setBackgroundDrawable(null);
        checkWeekPopupWindow.setFocusable(false);
        checkWeekPopupWindow.setOutsideTouchable(true);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                l.onItemClick(parent, view, position, id);
            }
        });
        return checkWeekPopupWindow;
    }

    /**
     * 里面的列表项的监听器
     */
    public static interface ItemClickListener {
        void onItemClick(AdapterView<?> parent, View view, int position, long id);
    }

}

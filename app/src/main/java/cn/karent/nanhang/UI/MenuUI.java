package cn.karent.nanhang.UI;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import cn.karent.nanhang.R;
/**
 * Created by wan on 2016/12/25.
 * 侧滑菜单里面的封装
 */
public class MenuUI {

    private Activity mActivity;
    /**
     * 登陆按钮
     */
    private Button loginBtn;

    public MenuUI(Activity activity) {
        mActivity = activity;
        loginBtn = (Button)activity.findViewById(R.id.menu_loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoginDialog.LoginDialogBuilder(mActivity).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("MenuUI", "对话框点击事件");
                    }
                }).create().show();
            }
        });
    }






}

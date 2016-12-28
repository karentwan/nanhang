package cn.karent.nanhang.UI;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import cn.karent.nanhang.R;
import cn.karent.nanhang.util.ScreenUtil;

/**
 * Created by wan on 2016/12/25.
 * 登陆对话框
 */
public class LoginDialog extends Dialog{

    public LoginDialog(Context context) {
        super(context);
    }

    public LoginDialog(Context context, int theme) {
        super(context, theme);
    }

    /**
     * 创建对话框的类
     */
    public static class LoginDialogBuilder {

        private Context mContext;
        /**
         * 登录事件的监听器
         */
        private View.OnClickListener mLoginListener;
        /**
         * 对话框的宽度
         */
        private int mWidth;

        public LoginDialogBuilder(Context context) {
            this.mContext = context;
            mWidth = ScreenUtil.dp2px(280);
        }

        public LoginDialogBuilder setOnClickListener(View.OnClickListener l) {
            this.mLoginListener = l;
            return this;
        }

        /**
         * 创建对话框
         * @return
         */
        public LoginDialog create() {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            //加载布局对话
            View v = inflater.inflate(R.layout.login_dialog_layout, null);
            final LoginDialog d = new LoginDialog(mContext, R.style.LoginDialogTheme);
            d.addContentView(v, new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            //设置对话框的大小
            Window dialogWindow = d.getWindow();
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            dialogWindow.setGravity(Gravity.CENTER);
            lp.width = mWidth;
            dialogWindow.setAttributes(lp);
            Button loginBtn = (Button)v.findViewById(R.id.dialog_loginBtn);
            loginBtn.setOnClickListener(mLoginListener);
            ImageView cancelBtn = (ImageView)v.findViewById(R.id.dialog_cancelBtn);
            cancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    d.dismiss();
                }
            });
            return d;
        }

    }


}

package cn.karent.nanhang.UI;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import cn.karent.nanhang.R;

/**
 * Created by wan on 2016/12/27.
 * 进度对话框
 */
public class ProgressDialog extends Dialog {

    private ProgressDialog(Context context) {
        super(context);
    }

    private ProgressDialog(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder {

        private Context mContext;

        public Builder(Context context) {
            mContext = context;
        }


        public ProgressDialog create() {
            ProgressDialog p = new ProgressDialog(mContext, R.style.LoginDialogTheme);
            View v = LayoutInflater.from(mContext).inflate(R.layout.load_dialog_layout, null);
            p.addContentView(v, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            Window window  = p.getWindow();
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = 700;
            lp.height = 700;
            window.setAttributes(lp);
            return p;
        }
    }


}

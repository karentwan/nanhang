package cn.karent.nanhang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import cn.karent.nanhang.UI.BottomControlUI;
import cn.karent.nanhang.UI.MenuUI;
import cn.karent.nanhang.UI.NewsUI;
import cn.karent.nanhang.UI.SlideLayout;

public class MainActivity extends AppCompatActivity {
    /**
     * 封装跟新闻有关的UI
     */
    private NewsUI mNewsUI;
    /**
     * 封装的跟侧滑菜单有关的UI
     */
    private MenuUI mMenuUI;
    /**
     * 底部控制栏的封装
     */
    private BottomControlUI mBottomCntrolUI;

    private ImageView headUser;

    private SlideLayout slide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        headUser = (ImageView)findViewById(R.id.head_user);
        slide = (SlideLayout)findViewById(R.id.slide);
        //设置titile
        mNewsUI = new NewsUI(this);
        mMenuUI = new MenuUI(this);
        mBottomCntrolUI = new BottomControlUI(this);
        headUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
Log.d("MainActivity", "侧滑菜单!");
                slide.switchMenu();
            }
        });
    }



}

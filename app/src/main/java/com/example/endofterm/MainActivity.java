package com.example.endofterm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.endofterm.Adapter.TabAdapter;
import com.example.endofterm.Fragment.FindFragment;
import com.example.endofterm.Fragment.HomeFragment;
import com.example.endofterm.Fragment.MyFragment;
import com.example.endofterm.Fragment.ShoppingFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.tab)
    TabLayout tab;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> strings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initTab();
    }

    private void initTab() {
        fragments = new ArrayList<>();                      //创建fragment的集合
        fragments.add(new HomeFragment());
        fragments.add(new FindFragment());
        fragments.add(new ShoppingFragment());
        fragments.add(new MyFragment());

        strings = new ArrayList<>();                        //创建字符串的集合
        strings.add("首页");
        strings.add("发现");
        strings.add("商城");
        strings.add("我的");

        //创建适配器
        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager(), 0, fragments, strings);
        vp.setAdapter(tabAdapter);
        tab.setupWithViewPager(vp);

        for (int i = 0; i < strings.size(); i++) {
            setTab(i);
        }
    }

    private void setTab(int position) {
        //创建自定义布局
        View view = LayoutInflater.from(this).inflate(R.layout.layout_tab, null, false);

        TextView tv_tab = view.findViewById(R.id.tv_tab);
        ImageView iv_tab = view.findViewById(R.id.iv_tab);

        tv_tab.setText(strings.get(position));      //给tab设置文字

        switch (position) {
            case 0:
                iv_tab.setImageResource(R.drawable.select1);            //给tab设置图片选择器
                break;
            case 1:
                iv_tab.setImageResource(R.drawable.select2);
                break;
            case 2:
                iv_tab.setImageResource(R.drawable.select3);
                break;
            case 3:
                iv_tab.setImageResource(R.drawable.select4);
                break;
        }

        TabLayout.Tab tabAt = tab.getTabAt(position);                   //获取tab布局
        tabAt.setCustomView(view);                                      //把自定义的布局加进去
    }

    private void initView() {
        toolbar.setTitle("");//给toolbar设置标题
        setSupportActionBar(toolbar);//绑定toolbar
    }
}

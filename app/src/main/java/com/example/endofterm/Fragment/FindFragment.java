package com.example.endofterm.Fragment;


import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.endofterm.Adapter.FindAdapter;
import com.example.endofterm.Adapter.HotAdapter;
import com.example.endofterm.Adapter.HotTabAdapter;
import com.example.endofterm.Base.BaseFragment;
import com.example.endofterm.Bean.JavaBean;
import com.example.endofterm.Fragment.find.ChartFragment;
import com.example.endofterm.Fragment.find.EncyclopediasFragment;
import com.example.endofterm.Fragment.find.HotspotFragment;
import com.example.endofterm.Fragment.find.MakeFragment;
import com.example.endofterm.Presenter.MainPresenter;
import com.example.endofterm.R;
import com.example.endofterm.View.MainView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
//继承BaseFragment
public class FindFragment extends BaseFragment<MainPresenter> implements MainView {

    @BindView(R.id.rlv_find)
    RecyclerView rlvFind;
    private ArrayList<JavaBean.DataBean.ActiondataBean> actionBeans;
    private FindAdapter findAdapter;
    private HotAdapter hotAdapter;
    private ArrayList<Fragment> fragments_hot;
    private ArrayList<String> strings;
    private HotTabAdapter tabAdapter;


    @Override
    protected void initView() {
        fragments_hot = new ArrayList<>();                      //创建fragment的集合
        fragments_hot.add(new HotspotFragment());
        fragments_hot.add(new MakeFragment());
        fragments_hot.add(new ChartFragment());
        fragments_hot.add(new EncyclopediasFragment());

        strings = new ArrayList<>();                        //创建字符串的集合
        strings.add("热点");
        strings.add("妆造");
        strings.add("图鉴");
        strings.add("百科");

        //创建适配器
        tabAdapter = new HotTabAdapter(getChildFragmentManager(), 0, fragments_hot, strings);

        actionBeans = new ArrayList<>();
        rlvFind.setLayoutManager(new LinearLayoutManager(getActivity()));
        rlvFind.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        findAdapter = new FindAdapter(getActivity(), actionBeans, tabAdapter);
        rlvFind.setAdapter(findAdapter);

        //给父Recycler设置监听
        rlvFind.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                if (e != null) {
                // 找到被点击位置的item的rootView
                    View itemView = rv.findChildViewUnder(e.getX(), e.getY());
                    if (itemView != null) {
                    // 通过rootView找到对应的ViewHolder
                        Object object = rv.getChildViewHolder(itemView);
                        //判断是否在儿子recycleView布局中
                        if(object instanceof FindAdapter.ViewHolder1 || object instanceof FindAdapter.ViewHolder2){
                            ((ViewGroup) itemView).requestDisallowInterceptTouchEvent(true);
                        }else {
                            ((ViewGroup) itemView).requestDisallowInterceptTouchEvent(false);
                        }

                       /* if (holder.getViewHolderType() == R.layout.view_pinyin_card) {

                            ((ViewGroup) itemView).requestDisallowInterceptTouchEvent(true);

                        }*/

                    }

                }

                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }


    @Override
    protected void initData() {
        mPresenter.getData();
    }

    @Override
    protected void initPresenter() {
        mPresenter = new MainPresenter();
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_find;
    }

    @Override
    protected void initListener() {


    }

    @Override
    public void setData(JavaBean javaBean) {
        actionBeans.addAll(javaBean.getData().getActiondata());
        findAdapter.notifyDataSetChanged();
    }

    @Override
    public void showToast(String string) {
        Toast.makeText(getActivity(), string, Toast.LENGTH_SHORT).show();
    }
}

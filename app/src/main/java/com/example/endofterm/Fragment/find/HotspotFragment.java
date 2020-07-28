package com.example.endofterm.Fragment.find;


import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.endofterm.Adapter.HotspotAdapter;
import com.example.endofterm.Base.BaseFragment;
import com.example.endofterm.Bean.JavaBean;
import com.example.endofterm.Presenter.MainPresenter;
import com.example.endofterm.R;
import com.example.endofterm.View.MainView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotspotFragment extends BaseFragment<MainPresenter> implements MainView {


    @BindView(R.id.rlv)
    RecyclerView rlv;
    private ArrayList<JavaBean.DataBean.ListBean> listBeans;
    private HotspotAdapter hotspotAdapter;


    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        mPresenter.getData();
    }

    @Override
    protected void initView() {
        listBeans = new ArrayList<>();
        rlv.setHasFixedSize(true);
        rlv.setNestedScrollingEnabled(false);
        rlv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rlv.addItemDecoration(new DividerItemDecoration(getActivity(),LinearLayoutManager.VERTICAL));
        hotspotAdapter = new HotspotAdapter(getActivity(),listBeans);
        rlv.setAdapter(hotspotAdapter);
    }


    @Override
    protected void initPresenter() {
        mPresenter = new MainPresenter();
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_hotspot;
    }

    @Override
    public void setData(JavaBean javaBean) {
        listBeans.addAll(javaBean.getData().getList());
        hotspotAdapter.notifyDataSetChanged();
    }

    @Override
    public void showToast(String string) {
        Toast.makeText(getActivity(),string, Toast.LENGTH_SHORT).show();
    }
}

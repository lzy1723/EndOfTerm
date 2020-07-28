package com.example.endofterm.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.endofterm.Bean.JavaBean;
import com.example.endofterm.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class FindAdapter extends RecyclerView.Adapter {

    private Context context;
    private int ITEM_TYPE_ONE = 1;
    private int ITEM_TYPE_TWO = 2;
    private HotAdapter hotAdapter;
    private ArrayList<JavaBean.DataBean.ActiondataBean> actionBeans;
    private HotTabAdapter tabAdapter;
    private int ItemCount = 2;


    public FindAdapter(Context context, ArrayList<JavaBean.DataBean.ActiondataBean> actionBeans, HotTabAdapter tabAdapter) {
        this.context = context;
        this.actionBeans = actionBeans;
        this.tabAdapter = tabAdapter;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ITEM_TYPE_ONE;
        } else {
            return ITEM_TYPE_TWO;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_ONE) {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_find_hot, parent, false);
            return new ViewHolder1(view);
        } else {
            View view1 = LayoutInflater.from(context).inflate(R.layout.layout_find_hot1, parent, false);
            return new ViewHolder2(view1);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if (itemViewType == ITEM_TYPE_ONE){
            ViewHolder1 holder1 = (ViewHolder1) holder;
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            holder1.rlv_hot.setLayoutManager(layoutManager);
            hotAdapter = new HotAdapter(context, actionBeans);
            holder1.rlv_hot.setAdapter(hotAdapter);
        }else {
            ViewHolder2 holder2 = (ViewHolder2) holder;
            holder2.find_vp.setAdapter(tabAdapter);
            holder2.find_tab.setupWithViewPager(holder2.find_vp);
        }
    }

    @Override
    public int getItemCount() {
        return ItemCount;
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {
        public View rootView;
        public RecyclerView rlv_hot;

        public ViewHolder1(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.rlv_hot = (RecyclerView) rootView.findViewById(R.id.rlv_hot);
        }

    }

    public class ViewHolder2 extends RecyclerView.ViewHolder {
        public View rootView;
        public TabLayout find_tab;
        public ViewPager find_vp;

        public ViewHolder2(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.find_tab = (TabLayout) rootView.findViewById(R.id.find_tab);
            this.find_vp = (ViewPager) rootView.findViewById(R.id.find_vp);
        }

    }
}

package com.example.endofterm.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.endofterm.Bean.JavaBean;
import com.example.endofterm.R;

import java.util.ArrayList;

public class HotspotAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<JavaBean.DataBean.ListBean> listBeans;
    private int ITEM_TYPE_ONE = 1;
    private int ITEM_TYPE_TWO = 2;
    private int ITEM_TYPE_THREE = 3;

    public HotspotAdapter(Context context, ArrayList<JavaBean.DataBean.ListBean> listBeans) {
        this.context = context;
        this.listBeans = listBeans;
    }

    @Override
    public int getItemViewType(int position) {
        if (listBeans.get(position).getFilePathList().size() > 1) {
            return ITEM_TYPE_ONE;
        } else if (listBeans.get(position).getFilePathList().size() == 1) {
            return ITEM_TYPE_TWO;
        } else {
            return ITEM_TYPE_THREE;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_ONE) {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_itme1, parent, false);
            return new Holder(view);
        } else if (viewType == ITEM_TYPE_TWO) {
            View view1 = LayoutInflater.from(context).inflate(R.layout.layout_item2, parent, false);
            return new Holder1(view1);
        } else {
            View view2 = LayoutInflater.from(context).inflate(R.layout.layout_item3, parent, false);
            return new Holder2(view2);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if (itemViewType == ITEM_TYPE_ONE){
            Holder holder1 = (Holder) holder;
            holder1.tv_title.setText(listBeans.get(position).getTitle());
            Glide.with(context).load(listBeans.get(position).getFilePathList().get(0).getFilePath()).into(holder1.iv_img1);
            Glide.with(context).load(listBeans.get(position).getFilePathList().get(1).getFilePath()).into(holder1.iv_img2);
            Glide.with(context).load(listBeans.get(position).getFilePathList().get(2).getFilePath()).into(holder1.iv_img3);
        }else if (itemViewType == ITEM_TYPE_TWO){
            Holder1 holder2 = (Holder1) holder;
            holder2.tv_title_item2.setText(listBeans.get(position).getTitle());
            Glide.with(context).load(listBeans.get(position).getFilePathList().get(0).getFilePath()).into(holder2.iv_img_item2);
        }else {
            Holder2 holder3 = (Holder2) holder;
            holder3.tv_title_item3.setText(listBeans.get(position).getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }


    class Holder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView tv_title;
        public ImageView iv_img1;
        public ImageView iv_img2;
        public ImageView iv_img3;

        public Holder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
            this.iv_img1 = (ImageView) rootView.findViewById(R.id.iv_img1);
            this.iv_img2 = (ImageView) rootView.findViewById(R.id.iv_img2);
            this.iv_img3 = (ImageView) rootView.findViewById(R.id.iv_img3);
        }

    }


    class Holder1 extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView tv_title_item2;
        public ImageView iv_img_item2;

        public Holder1(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.tv_title_item2 = (TextView) rootView.findViewById(R.id.tv_title_item2);
            this.iv_img_item2 = (ImageView) rootView.findViewById(R.id.iv_img_item2);
        }

    }


    class Holder2 extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView tv_title_item3;

        public Holder2(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.tv_title_item3 = (TextView) rootView.findViewById(R.id.tv_title_item3);
        }

    }
}

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

public class HotAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<JavaBean.DataBean.ActiondataBean> actionBeans;

    public HotAdapter(Context context, ArrayList<JavaBean.DataBean.ActiondataBean> actionBeans) {
        this.context = context;
        this.actionBeans = actionBeans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_hot, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        holder1.tv_hot_title.setText(actionBeans.get(position).getTitle());
        holder1.tv_hot_location.setText(actionBeans.get(position).getLocation());
        holder1.tv_hot_startTime.setText(actionBeans.get(position).getStartTime());
        Glide.with(context).load(actionBeans.get(position).getCover()).into(holder1.iv_hot_cover);
    }

    @Override
    public int getItemCount() {
        return actionBeans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView iv_hot_cover;
        public TextView tv_hot_title;
        public TextView tv_hot_location;
        public TextView tv_hot_startTime;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.iv_hot_cover = (ImageView) rootView.findViewById(R.id.iv_hot_cover);
            this.tv_hot_title = (TextView) rootView.findViewById(R.id.tv_hot_title);
            this.tv_hot_location = (TextView) rootView.findViewById(R.id.tv_hot_location);
            this.tv_hot_startTime = (TextView) rootView.findViewById(R.id.tv_hot_startTime);
        }

    }
}

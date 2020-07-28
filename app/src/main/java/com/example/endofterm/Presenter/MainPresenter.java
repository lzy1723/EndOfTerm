package com.example.endofterm.Presenter;


import com.example.endofterm.Base.BasePresenter;
import com.example.endofterm.Bean.JavaBean;
import com.example.endofterm.Bean.MainCallBack;
import com.example.endofterm.Model.MainModel;
import com.example.endofterm.View.MainView;

public class MainPresenter extends BasePresenter<MainView> implements MainCallBack {

    private MainModel mMainModel;

    @Override
    protected void initModel() {
        mMainModel = new MainModel();
        addModel(mMainModel);
    }

    @Override
    public void onSuccess(JavaBean javaBean) {
        mView.setData(javaBean);
    }

    @Override
    public void onFail(String string) {
        mView.showToast(string);
    }

    public void getData() {
        mMainModel.getData(this);
    }
}

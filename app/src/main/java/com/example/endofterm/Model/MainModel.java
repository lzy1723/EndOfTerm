package com.example.endofterm.Model;

import android.util.Log;

import com.example.endofterm.Base.BaseModel;
import com.example.endofterm.Bean.JavaBean;
import com.example.endofterm.Bean.MainCallBack;
import com.example.endofterm.Net.ApiService;
import com.example.endofterm.Presenter.MainPresenter;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainModel extends BaseModel {
    public void getData(MainCallBack mainCallBack) {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Observable<JavaBean> javaBean = build.create(ApiService.class).getJavaBean();

        javaBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JavaBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JavaBean javaBean) {
                        mainCallBack.onSuccess(javaBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mainCallBack.onFail(e.getMessage());

                        Log.i("TAG",e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

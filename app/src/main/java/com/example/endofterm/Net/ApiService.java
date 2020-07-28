package com.example.endofterm.Net;

import com.example.endofterm.Bean.JavaBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {

    String BASE_URL = "http://cdwan.cn:7000/";

    @GET("exam/data.json")
    Observable<JavaBean> getJavaBean();

}

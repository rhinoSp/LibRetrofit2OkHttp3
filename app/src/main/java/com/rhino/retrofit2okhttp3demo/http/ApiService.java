package com.rhino.retrofit2okhttp3demo.http;


import com.rhino.retrofit2okhttp3demo.http.result.HomeModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;


/**
 * @author LuoLin
 * @since Create on 2019/2/19.
 */
public interface ApiService {

    String BASE_URL = "http://tanzi.cpicollect.com";

    @GET("big_shot_article_category")
    Observable<BaseResult<List<HomeModel>>> getHomeModel();


}
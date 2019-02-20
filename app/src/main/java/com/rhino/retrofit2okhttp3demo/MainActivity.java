package com.rhino.retrofit2okhttp3demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.rhino.retrofit2okhttp3demo.http.BaseResult;
import com.rhino.retrofit2okhttp3demo.http.HttpUtils;
import com.rhino.retrofit2okhttp3demo.http.result.HomeModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author LuoLin
 * @since Create on 2019/2/19.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onViewClick(View v) {
        int id = v.getId();
        if (id == R.id.button) {
            getHomeModel();
        }
    }

    private void getHomeModel() {
        Disposable disposable = HttpUtils.getApiService().getHomeModel()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        showToast("加载中...");
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.e("DDDD", "请求完成！");
                        showToast("请求完成！");
                    }
                })
                .subscribe(new Consumer<BaseResult<List<HomeModel>>>() {
                               @Override
                               public void accept(BaseResult<List<HomeModel>> result) throws Exception {
                                   Log.d("DDDD", result != null ? result.toString() : null);
                                   showToast("请求成功" + (result != null ? result.toString() : null));
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.e("DDDD", throwable.toString());
                                showToast("请求失败！" + throwable.toString());
                            }
                        });
    }

    private void showToast(String msg) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

}

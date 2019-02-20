package com.rhino.retrofit2okhttp3demo.http.result;


/**
 * @author LuoLin
 * @since Create on 2019/2/19.
 */
public final class HomeModel {

    public int id;
    public String name;

    public HomeModel() {
    }

    public HomeModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HomeModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}

package com.xajd.takeout.presenter.api;

import com.xajd.takeout.model.net.bean.RetrofitInfo;
import com.xajd.takeout.utils.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/*
 *  @项目名：  TakeOut 
 *  @包名：    com.xajd.takeout.presenter.api
 *  @文件名:   RetrofitInfoAPI
 *  @创建者:   Administrator
 *  @创建时间:  2017/6/10 20:59
 *  @描述：    TODO
 */
public interface RetrofitInfoAPI {
	@GET(Constants.LOGIN)
	Call<RetrofitInfo> login(
			@Query("username")
			String username,
			@Query("password")
			String password);

	@GET(Constants.HOME)
	Call<RetrofitInfo> home();

	@GET(Constants.GOOGS)
	Call<RetrofitInfo> goods(@Query("sellerId") Long sellerId);
}

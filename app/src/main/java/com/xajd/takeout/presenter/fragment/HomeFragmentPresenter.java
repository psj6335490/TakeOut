package com.xajd.takeout.presenter.fragment;

import com.google.gson.Gson;
import com.xajd.takeout.model.net.bean.HomeInfo;
import com.xajd.takeout.model.net.bean.RetrofitInfo;
import com.xajd.takeout.presenter.BasePresenter;
import com.xajd.takeout.ui.fragment.HomeFragment;

import retrofit2.Call;

/*
 *  @项目名：  主页业务
 *  @包名：    com.xajd.takeout.presenter.fragment
 *  @文件名:   HomeFragmentPresenter
 *  @创建者:   Administrator
 *  @创建时间:  2017/6/13 22:19
 *  @描述：    TODO
 */
public class HomeFragmentPresenter extends BasePresenter {
	private static final String TAG = "HomeFragmentPresenter";

	private HomeFragment mFragment;


	public HomeFragmentPresenter(HomeFragment fragment) {
		mFragment = fragment;
	}

	/**
	 * 拿到首页数据
	 */
	public void getData() {
		Call<RetrofitInfo> call = mApi.home();
		call.enqueue(new CallbackAdapter());
	}

	@Override
	protected void doFail(String msg) {

	}

	@Override
	protected void parseDate(String data) {
		Gson gson = new Gson();
		HomeInfo homeInfo = gson.fromJson(data, HomeInfo.class);
		mFragment.getAdapter().setData(homeInfo);
	}
	

}

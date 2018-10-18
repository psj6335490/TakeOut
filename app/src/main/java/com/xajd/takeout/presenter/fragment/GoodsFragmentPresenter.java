package com.xajd.takeout.presenter.fragment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xajd.takeout.model.net.bean.GoodItem;
import com.xajd.takeout.model.net.bean.RetrofitInfo;
import com.xajd.takeout.presenter.BasePresenter;
import com.xajd.takeout.ui.fragment.GoodsFragment;

import java.util.List;

import retrofit2.Call;

/*
 *  @项目名：  TakeOut 
 *  @包名：    com.xajd.takeout.presenter.fragment
 *  @文件名:   GoodsFragmentPresenter
 *  @创建者:   Administrator
 *  @创建时间:  2017/9/10 16:09
 *  @描述：    TODO
 */
public class GoodsFragmentPresenter extends BasePresenter {
	private static final String TAG = "GoodsFragmentPresenter";

	private GoodsFragment mFragment;

	public GoodsFragmentPresenter(GoodsFragment fragment) {
		mFragment = fragment;
	}

	@Override
	protected void doFail(String msg) {

	}

	@Override
	protected void parseDate(String data) {
		Gson gson = new Gson();
		List<GoodItem> goods = gson.fromJson(data, new TypeToken<List<GoodItem>>() {
		}.getType());

		mFragment.setUi(goods);
	}

	public void getData(Long sellerId) {
		Call<RetrofitInfo> call = mApi.goods(sellerId);
		call.enqueue(new CallbackAdapter());
	}
}

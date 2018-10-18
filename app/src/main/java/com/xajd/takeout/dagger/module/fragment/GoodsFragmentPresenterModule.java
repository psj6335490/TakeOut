package com.xajd.takeout.dagger.module.fragment;

import com.xajd.takeout.presenter.fragment.GoodsFragmentPresenter;
import com.xajd.takeout.ui.fragment.GoodsFragment;

import dagger.Module;
import dagger.Provides;

/*
 *  @项目名：  TakeOut 
 *  @包名：    com.xajd.takeout.dagger.module.fragment
 *  @文件名:   GoodsFragmentPresenterModule
 *  @创建者:   Administrator
 *  @创建时间:  2017-09-13 16:38
 *  @描述：    TODO
 */
@Module
public class GoodsFragmentPresenterModule {
	private static final String TAG = "GoodsFragmentPresenterModule";

	private GoodsFragment mFragment;

	public GoodsFragmentPresenterModule(GoodsFragment fragment) {
		mFragment = fragment;
	}

	@Provides
	public GoodsFragmentPresenter providerGoodsFragmentPresenter(){
		return new GoodsFragmentPresenter(mFragment);
	}
}

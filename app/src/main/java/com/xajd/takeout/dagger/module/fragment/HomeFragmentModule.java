package com.xajd.takeout.dagger.module.fragment;

import com.xajd.takeout.presenter.fragment.HomeFragmentPresenter;
import com.xajd.takeout.ui.fragment.HomeFragment;

import dagger.Module;
import dagger.Provides;

/*
 *  @项目名：  TakeOut 
 *  @包名：    com.xajd.takeout.dagger.module.fragment
 *  @文件名:   HomeFragmentModule
 *  @创建者:   Administrator
 *  @创建时间:  2017/9/8 8:19
 *  @描述：    TODO
 */
@Module
public class HomeFragmentModule {
	private static final String TAG = "HomeFragmentModule";

	private HomeFragment mFragment;

	public HomeFragmentModule(HomeFragment fragment) {
		mFragment = fragment;
	}

	@Provides
	HomeFragmentPresenter  providerHomeFragmentPresenter(){
		return new HomeFragmentPresenter(mFragment);
	}
}

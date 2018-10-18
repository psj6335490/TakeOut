package com.xajd.takeout.dagger.component.fragment;

import com.xajd.takeout.dagger.module.fragment.HomeFragmentModule;
import com.xajd.takeout.ui.fragment.HomeFragment;

import dagger.Component;

/*
 *  @项目名：  TakeOut 
 *  @包名：    com.xajd.takeout.dagger.component.fragment
 *  @文件名:   HomeFragmentComponent
 *  @创建者:   Administrator
 *  @创建时间:  2017/9/8 8:24
 *  @描述：    TODO
 */
@Component(modules = HomeFragmentModule.class)
public interface HomeFragmentComponent {
	void in(HomeFragment fragment);
}

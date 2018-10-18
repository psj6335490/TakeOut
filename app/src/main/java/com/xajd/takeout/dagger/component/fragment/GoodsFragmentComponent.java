package com.xajd.takeout.dagger.component.fragment;

import com.xajd.takeout.dagger.module.fragment.GoodsFragmentPresenterModule;
import com.xajd.takeout.ui.fragment.GoodsFragment;

import dagger.Component;

/*
 *  @项目名：  TakeOut 
 *  @包名：    com.xajd.takeout.dagger.component.fragment
 *  @文件名:   GoodsFragmentComponent
 *  @创建者:   Administrator
 *  @创建时间:  2017-09-13 16:41
 *  @描述：    TODO
 */
@Component(modules = GoodsFragmentPresenterModule.class)
public interface GoodsFragmentComponent {

	void in(GoodsFragment fragment);
}

package com.xajd.takeout.model.net.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
 *  @项目名：  TakeOut 
 *  @包名：    com.xajd.takeout.model.net.bean
 *  @文件名:   GoodItem
 *  @创建者:   Administrator
 *  @创建时间:  2017/9/10 16:33
 *  @描述：    TODO
 */
public class GoodItem {

	public int id;
	public String info;
	public String name;
	public List<ListBean> list;
	private static final String TAG = "GoodItem";


	public static class ListBean {
		public boolean bargainPrice;
		public String form;
		public String icon;
		public int id;
		public int monthSaleNum;
		public String name;
		@SerializedName("new")
		public boolean newX;
		public double newPrice;
		public int oldPrice;
		public int headIndex;

		public int count;


	}
}

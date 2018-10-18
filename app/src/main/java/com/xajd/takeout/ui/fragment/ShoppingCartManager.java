package com.xajd.takeout.ui.fragment;

import com.xajd.takeout.model.net.bean.GoodItem;

import java.util.concurrent.CopyOnWriteArrayList;

/*
 *  @项目名：  TakeOut 
 *  @包名：    com.xajd.takeout.ui.fragment
 *  @文件名:   ShoppingCartManager
 *  @创建者:   Administrator
 *  @创建时间:  2017-09-13 21:22
 *  @描述：    TODO
 */
public class ShoppingCartManager {
	private static final String TAG = "ShoppingCartManager";

	private static ShoppingCartManager instance;
	public CopyOnWriteArrayList<GoodItem.ListBean> goodItems = new CopyOnWriteArrayList<>();
	public long sellerId=-1;

	private ShoppingCartManager() {
	}

	public static ShoppingCartManager getInstance() {
		if (instance == null) {
			instance = new ShoppingCartManager();
		}
		return instance;
	}



	public Integer addGoods(GoodItem.ListBean info) {
		int num = 0;
		// 判断容器中是否含有该商品
		// 如果有做++
		// 如果没有，添加一条记录

		boolean isContain = false;
		for (GoodItem.ListBean item : goodItems) {
			if (item.id == info.id) {
				item.count++;
				num = item.count;
				isContain = true;
				break;
			}
		}

		if (!isContain) {
			num = info.count = 1;
			goodItems.add(info);
		}
		return num;
	}


	public Integer minusGoods(GoodItem.ListBean info) {
		int num = 0;
		for (GoodItem.ListBean item : goodItems) {
			if (item.id == info.id && item.count > 0) {
				item.count--;
				num = item.count;
				break;
			}
		}
		return num;
	}

	/**
	 * 获取商品总数
	 *
	 * @return
	 */
	public Integer getTotalNum() {
		int totalNum = 0;
		for (GoodItem.ListBean item :
				goodItems) {
			totalNum += item.count;
		}
		return totalNum;
	}

	/**
	 * 获取商品总数
	 *
	 * @return
	 */
	public Integer getMoney() {
		int totalMoney = 0;
		for (GoodItem.ListBean item : goodItems) {
			totalMoney += (int) (item.newPrice*item.count * 100);
		}
		return totalMoney;
	}
	/**
	 * 情况所有选中的商品
	 */
	public void clear() {
		goodItems.clear();
	}

	public Integer getGoodsIdNum(int id) {
		for (GoodItem.ListBean item : goodItems) {
			if (item.id == id) {
				return item.count;
			}
		}
		return 0;
	}
}

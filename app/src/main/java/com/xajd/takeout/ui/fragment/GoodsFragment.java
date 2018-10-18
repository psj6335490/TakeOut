package com.xajd.takeout.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xajd.takeout.R;
import com.xajd.takeout.dagger.component.fragment.DaggerGoodsFragmentComponent;
import com.xajd.takeout.dagger.module.fragment.GoodsFragmentPresenterModule;
import com.xajd.takeout.model.net.bean.GoodHead;
import com.xajd.takeout.model.net.bean.GoodItem;
import com.xajd.takeout.presenter.fragment.GoodsFragmentPresenter;
import com.xajd.takeout.ui.activity.CartActivity;
import com.xajd.takeout.ui.adapter.MyAdapter;
import com.xajd.takeout.ui.adapter.MyGroupkAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;


/**
 * Created by lidongzhi on 2016/11/26.
 */

public class GoodsFragment extends BaseFragment implements AdapterView.OnItemClickListener, AbsListView.OnScrollListener {

	@InjectView(R.id.lv)
	ListView mLv;
	@InjectView(R.id.shl)
	StickyListHeadersListView mShl;
	@InjectView(R.id.iv_cart)
	ImageView mIvCart;
	@InjectView(R.id.fragment_goods_tv_count)
	TextView mFragmentGoodsTvCount;
	@InjectView(R.id.cart)
	RelativeLayout mCart;
	private MyGroupkAdapter mHeadAdapter;
	private MyAdapter mAdapter;

	List<GoodItem.ListBean> datas = new ArrayList<>();
	List<GoodHead> mGoodHeadDatas = new ArrayList<>();

	private boolean isFromScroll = false;//如果是滑动引起的才刷新,左边点击引起的滑动不会刷新(点击引起的滑动不会导致滑动状态的变化)

	@Inject
	GoodsFragmentPresenter mPresenter;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DaggerGoodsFragmentComponent.builder().goodsFragmentPresenterModule(new GoodsFragmentPresenterModule(this)).build().in(this);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_goods, null);
		ButterKnife.inject(this, view);
		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		Bundle arguments = getArguments();
		long sellerId = arguments.getLong("seller_id");
		mPresenter.getData(sellerId);
	}

	/**
	 * Desc:  展示数据
	 * Params:
	 * Author:  SaGe Peng
	 * Create Time:  2017/9/10 16:54
	 */
	public void setUi(List<GoodItem> goods) {
		datas.clear();
		mGoodHeadDatas.clear();
		for (int i = 0; i < goods.size(); i++) {
			GoodHead GoodHead = new GoodHead();
			GoodHead.info = goods.get(i).name;
			GoodHead.firstData = datas.size();
			mGoodHeadDatas.add(GoodHead);
			for (int j = 0; j < goods.get(i).list.size(); j++) {
				goods.get(i).list.get(j).headIndex = i;
				datas.add(goods.get(i).list.get(j));
			}
		}

		/////////////////////////////////////////
		mHeadAdapter = new MyGroupkAdapter(datas, mGoodHeadDatas);
		mShl.setAdapter(mHeadAdapter);

		mAdapter = new MyAdapter(mGoodHeadDatas);
		mLv.setAdapter(mAdapter);

		mLv.setOnItemClickListener(this);
		mShl.setOnScrollListener(this);

		if (ShoppingCartManager.getInstance().getTotalNum() > 0) {
			mFragmentGoodsTvCount.setVisibility(View.VISIBLE);
			mFragmentGoodsTvCount.setText(ShoppingCartManager.getInstance().getTotalNum().toString());
		}
	}


	@Override
	public void onDestroyView() {
		super.onDestroyView();
		ButterKnife.reset(this);
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
		isFromScroll = false;
		mAdapter.setSelectPosition(i);
		mShl.setSelection(mGoodHeadDatas.get(i).firstData);
	}

	@Override
	public void onScrollStateChanged(AbsListView absListView, int i) {
		isFromScroll = true;
	}

	@Override
	public void onScroll(AbsListView absListView, int i, int i1, int i2) {
		if (isFromScroll) {
			int headIndex = datas.get(i).headIndex;
			mAdapter.setSelectPosition(headIndex);
			int firstHeadVisiblePosition = mLv.getFirstVisiblePosition();
			int lastHeadVisiblePosition = mLv.getLastVisiblePosition();
			if (headIndex <= firstHeadVisiblePosition || headIndex >= lastHeadVisiblePosition) {
				mLv.setSelection(headIndex);
			}
		}

	}

	@OnClick(R.id.cart)
	public void onClick() {
		Intent intent = new Intent(this.getContext(), CartActivity.class);
		this.getContext().startActivity(intent);
	}
}

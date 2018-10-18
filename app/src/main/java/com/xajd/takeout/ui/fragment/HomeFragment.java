package com.xajd.takeout.ui.fragment;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xajd.takeout.R;
import com.xajd.takeout.dagger.component.fragment.DaggerHomeFragmentComponent;
import com.xajd.takeout.dagger.module.fragment.HomeFragmentModule;
import com.xajd.takeout.presenter.fragment.HomeFragmentPresenter;
import com.xajd.takeout.ui.adapter.HomeRecyclerViewAdapter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * Created by lidongzhi on 2016/11/26.
 */

public class HomeFragment extends BaseFragment {

	@InjectView(R.id.rv_home)
	RecyclerView mRvHome;
	@InjectView(R.id.home_tv_address)
	TextView mHomeTvAddress;
	@InjectView(R.id.ll_title_search)
	LinearLayout mLlTitleSearch;
	@InjectView(R.id.ll_title_container)
	LinearLayout mLlTitleContainer;


	@Inject
	HomeFragmentPresenter mHomeFragmentPresenter;
	private HomeRecyclerViewAdapter mAdapter;

	@Nullable



	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_home, null);
		ButterKnife.inject(this, view);
		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		mAdapter = new HomeRecyclerViewAdapter();
		mRvHome.setAdapter(mAdapter);
		mRvHome.setLayoutManager(new LinearLayoutManager(this.getActivity(),LinearLayoutManager.VERTICAL,false));

		mRvHome.addOnScrollListener(listener);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DaggerHomeFragmentComponent.builder().homeFragmentModule(new HomeFragmentModule(this)).build().in(this);
	}

	@Override
	public void onResume() {
		super.onResume();
		mHomeFragmentPresenter.getData();
		sum=0;//复原透明度
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		ButterKnife.reset(this);//释放资源
	}

	private int sum=0;
	private int color=0x553190E8;
	private float duration=150.0f;
	private ArgbEvaluator mEvaluator=new ArgbEvaluator();

	private RecyclerView.OnScrollListener listener=new RecyclerView.OnScrollListener() {

		@Override
		public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
			super.onScrolled(recyclerView, dx, dy);
			sum+=dy;
			if (sum>150){
				color=0xFF3190E8;
			}else if(sum<0){
				color=0x553190E8;
			}else {
				color = (int) mEvaluator.evaluate(sum / duration, 0x553190E8, 0xFF3190E8);
			}
			Log.d("HomeFragment", "color:" + color);
			mLlTitleContainer.setBackgroundColor(color);
		}
	};


	public HomeRecyclerViewAdapter getAdapter() {
		return  mAdapter;
	}
}

package com.xajd.takeout.ui.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.xajd.takeout.R;
import com.xajd.takeout.ui.fragment.HomeFragment;
import com.xajd.takeout.ui.fragment.MoreFragment;
import com.xajd.takeout.ui.fragment.OrderFragment;
import com.xajd.takeout.ui.fragment.UserFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	@InjectView(R.id.main_fragment_container)
	FrameLayout mMainFragmentContainer;


	@InjectView(R.id.main_bottome_switcher_container)
	LinearLayout mMainBottomeSwitcherContainer;


	private List<Fragment> fragments=new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.inject(this);

		init();
		setListener();
	}

	/**
	 * Desc:  初始化Fragment
	 * Params:
	 * Author:  SaGe Peng
	 * Create Time:  2017/6/11 19:05
	 *
	 */
	private void init() {
		fragments.add(new HomeFragment());
		fragments.add(new OrderFragment());
		fragments.add(new UserFragment());
		fragments.add(new MoreFragment());

		onClick(mMainBottomeSwitcherContainer.getChildAt(0));
	}


	/**
	 * Desc:  设置下面的点击
	 * Params:
	 * Author:  SaGe Peng
	 * Create Time:  2017/6/11 0:17
	 */
	private void setListener() {
		int childCount = mMainBottomeSwitcherContainer.getChildCount();

		for (int i = 0; i < childCount; i++) {
			FrameLayout childAt = (FrameLayout) mMainBottomeSwitcherContainer.getChildAt(i);
			childAt.setOnClickListener(this);
		}
	}


	@Override
	public void onClick(View view) {
		int index = mMainBottomeSwitcherContainer.indexOfChild(view);

		changeNavigator(index);
		changeFragment(index);
	}

	/**
	 * Desc:
	 * Params: 改变界面 内容
	 * Author:  SaGe Peng
	 * Create Time:  2017/6/11 19:04
	 *
	 */
	private void changeFragment(int index) {
		Fragment fragment = fragments.get(index);
		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.main_fragment_container,fragment)
				.commit();
	}

	private void changeNavigator(int index) {
		int childCount = mMainBottomeSwitcherContainer.getChildCount();
		for (int i = 0; i < childCount; i++) {
			if (index == i) {
				setChildEnable(mMainBottomeSwitcherContainer.getChildAt(i), false);
			} else {
				setChildEnable(mMainBottomeSwitcherContainer.getChildAt(i),true);
			}
		}
	}




	/**
	 * Desc:  改变底部导航
	 * Params:
	 * Author:  SaGe Peng
	 * Create Time:  2017/6/11 19:04
	 *
	 */
	private void setChildEnable(View item, boolean b) {
		item.setEnabled(b);
		if (item instanceof ViewGroup) {
			int childCount = ((ViewGroup) item).getChildCount();

			for (int i = 0; i < childCount; i++) {
				setChildEnable(((ViewGroup) item).getChildAt(i),b);
			}
		}
	}
}

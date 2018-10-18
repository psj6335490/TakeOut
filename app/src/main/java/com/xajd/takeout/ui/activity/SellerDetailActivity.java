package com.xajd.takeout.ui.activity;


import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.xajd.takeout.R;
import com.xajd.takeout.ui.fragment.CommentsFragment;
import com.xajd.takeout.ui.fragment.GoodsFragment;
import com.xajd.takeout.ui.fragment.SellerFragment;
import com.xajd.takeout.utils.UiUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SellerDetailActivity extends AppCompatActivity {

	@InjectView(R.id.toolbar)
	Toolbar mToolbar;
	@InjectView(R.id.tabs)
	android.support.design.widget.TabLayout mTabs;
	@InjectView(R.id.vp)
	ViewPager mVp;
	@InjectView(R.id.seller_detail_container)
	FrameLayout mSellerDetailContainer;

	private long mSeller_id;
	private PagerAdapter mAdapter;

	private String[] titles={"商品","评价","商家"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_seller_detail);
		ButterKnife.inject(this);

		Intent intent = getIntent();

		mSeller_id = intent.getLongExtra("seller_id", -1);
		String name = intent.getStringExtra("name");

		mToolbar.setTitle(name);
		setSupportActionBar(mToolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);


		mAdapter = new MyFragmentPageAdapter(getSupportFragmentManager());
		mVp.setAdapter(mAdapter);
		mTabs.setupWithViewPager(mVp);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId()==android.R.id.home){
			finish();
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);

		// 获取到状态栏的高度
		Rect outRect = new Rect();
		this.getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect);
		UiUtils.STATUE_BAR_HEIGHT = outRect.top;// 状态栏的高度

	}

	private class MyFragmentPageAdapter extends FragmentPagerAdapter {
		public MyFragmentPageAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			Fragment fragment=null;
			switch (position) {
				case 0:
					Bundle bundle = new Bundle();
					bundle.putLong("seller_id",mSeller_id);
					fragment = new GoodsFragment();
					fragment.setArguments(bundle);
					break;
				case 1:
					fragment = new CommentsFragment();
					break;
				case 2:
					fragment = new SellerFragment();
					break;
				default:
					break;
			}
			return fragment;
		}

		@Override
		public int getCount() {
			return titles.length;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return titles[position];
		}
	}
}

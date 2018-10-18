package com.xajd.takeout.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xajd.takeout.R;
import com.xajd.takeout.ui.RecycleViewDivider;
import com.xajd.takeout.ui.adapter.MyCartAdapter;
import com.xajd.takeout.ui.fragment.ShoppingCartManager;
import com.xajd.takeout.utils.NumberFormatUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class CartActivity extends AppCompatActivity {

	@InjectView(R.id.toolbar)
	Toolbar mToolbar;
	@InjectView(R.id.tv_total)
	TextView mTvTotal;
	@InjectView(R.id.tv_money)
	TextView mTvMoney;
	@InjectView(R.id.button)
	Button mButton;
	@InjectView(R.id.bottom)
	RelativeLayout mBottom;
	@InjectView(R.id.cart_rv)
	RecyclerView mCartRv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cart);
		ButterKnife.inject(this);

		mToolbar.setTitle("购物车");
		setSupportActionBar(mToolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		RecyclerView.Adapter adapter = new MyCartAdapter();
		mCartRv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
		mCartRv.setAdapter(adapter);

		// 设置分割线
		mCartRv.addItemDecoration(new RecycleViewDivider(this,LinearLayoutManager.HORIZONTAL,2,0XE3E0DC));

		mTvMoney.setText(NumberFormatUtils.formatDigits(ShoppingCartManager.getInstance().getMoney()/100.0));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			finish();
		}
		return super.onOptionsItemSelected(item);
	}

}

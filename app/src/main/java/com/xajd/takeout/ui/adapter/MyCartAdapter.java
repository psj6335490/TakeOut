package com.xajd.takeout.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xajd.takeout.MyApplication;
import com.xajd.takeout.R;
import com.xajd.takeout.model.net.bean.GoodItem;
import com.xajd.takeout.ui.fragment.ShoppingCartManager;
import com.xajd.takeout.utils.Constants;
import com.xajd.takeout.utils.NumberFormatUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MyCartAdapter extends RecyclerView.Adapter {
		@Override
		public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			ViewHolder holder = new ViewHolder(View.inflate(MyApplication.getContext(), R.layout.item_cart, null));
			return holder;
		}

		@Override
		public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
			((ViewHolder) holder).setData(ShoppingCartManager.getInstance().goodItems.get(position));
		}

		@Override
		public int getItemCount() {
			return ShoppingCartManager.getInstance().goodItems.size();
		}


		class ViewHolder extends RecyclerView.ViewHolder {
			@InjectView(R.id.item_iv)
			ImageView mItemIv;
			@InjectView(R.id.item_tv_name)
			TextView mItemTvName;
			@InjectView(R.id.item_tv_price)
			TextView mItemTvPrice;
			@InjectView(R.id.item_tv_num)
			TextView mItemTvNum;

			private GoodItem.ListBean data;


			public ViewHolder(View view) {
				super(view);
				ButterKnife.inject(this, view);
			}

			public void setData(GoodItem.ListBean data) {
				this.data = data;
				Picasso.with(MyApplication.getContext()).load(data.icon.replace("http://172.16.0.116:8080", Constants.BASEURL)).into(mItemIv);
				mItemTvName.setText(data.name);
				mItemTvPrice.setText(NumberFormatUtils.formatDigits(data.newPrice));
				mItemTvNum.setText(data.count + "");
			}
		}
	}
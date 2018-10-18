package com.xajd.takeout.ui.adapter;

import android.graphics.Paint;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xajd.takeout.MyApplication;
import com.xajd.takeout.R;
import com.xajd.takeout.model.net.bean.GoodHead;
import com.xajd.takeout.model.net.bean.GoodItem;
import com.xajd.takeout.ui.fragment.ShoppingCartManager;
import com.xajd.takeout.utils.AnimationUtils;
import com.xajd.takeout.utils.AnimationUtils.AnimationListenerAdapter;
import com.xajd.takeout.utils.Constants;
import com.xajd.takeout.utils.NumberFormatUtils;
import com.xajd.takeout.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

public class MyGroupkAdapter extends BaseAdapter implements StickyListHeadersAdapter {

	List<GoodItem.ListBean> datas = new ArrayList<>();
	List<GoodHead> mGoodHeadDatas = new ArrayList<>();

	public MyGroupkAdapter(List<GoodItem.ListBean> datas, List<GoodHead> goodHeadDatas) {
		this.datas = datas;
		mGoodHeadDatas = goodHeadDatas;
	}

	@Override
	public View getHeaderView(int position, View convertView, ViewGroup parent) {
		TextView textView = new TextView(MyApplication.getContext());
		textView.setText(mGoodHeadDatas.get(datas.get(position).headIndex).info);
		textView.setBackgroundColor(MyApplication.getContext().getResources().getColor(R.color.colorItemBg));
		return textView;
	}

	@Override
	public long getHeaderId(int position) {
		return datas.get(position).headIndex;
	}

	////////////////////////////////////////
	@Override
	public int getCount() {
		if (datas != null && datas.size() > 0) {
			return datas.size();
		} else {
			return 0;
		}
	}

	@Override
	public Object getItem(int i) {
		return datas.get(i);
	}

	@Override
	public long getItemId(int i) {
		return i;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup viewGroup) {
		GoodItem.ListBean data = datas.get(position);
		ItemViewHolder holder;
		if (convertView == null) {
			convertView = View.inflate(MyApplication.getContext(), R.layout.item_goods, null);
			holder = new ItemViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ItemViewHolder) convertView.getTag();
		}

		holder.setData(data);
		return convertView;
	}


	class ItemViewHolder {
		View itemView;


		@InjectView(R.id.iv_icon)
		ImageView ivIcon;
		@InjectView(R.id.tv_name)
		TextView tvName;
		@InjectView(R.id.tv_zucheng)
		TextView tvZucheng;
		@InjectView(R.id.tv_yueshaoshounum)
		TextView tvYueshaoshounum;
		@InjectView(R.id.tv_newprice)
		TextView tvNewprice;
		@InjectView(R.id.tv_oldprice)
		TextView tvOldprice;
		@InjectView(R.id.ib_minus)
		ImageButton ibMinus;
		@InjectView(R.id.tv_money)
		TextView tvCount;
		@InjectView(R.id.ib_add)
		ImageButton ibAdd;
		private FrameLayout container;
		private TextView count;
		private GoodItem.ListBean data;



		@OnClick({R.id.ib_minus, R.id.ib_add})
		public void onClick(View view) {
			switch (view.getId()) {
				case R.id.ib_minus:
					minusHandle(view);
					break;
				case R.id.ib_add:
					addHandle(view);
					break;
			}
		}

		private void minusHandle(View view) {
			Integer num = ShoppingCartManager.getInstance().minusGoods(data);
			if (num == 0) {
				ibMinus.setEnabled(false);
				AnimationSet animation = AnimationUtils.getHideMinusAnimation();
				tvCount.startAnimation(animation);
				ibMinus.startAnimation(animation);

				animation.setAnimationListener(new AnimationListenerAdapter() {
					@Override
					public void onAnimationEnd(Animation animation) {
						tvCount.setVisibility(View.INVISIBLE);
						ibMinus.setVisibility(View.INVISIBLE);
						ibMinus.setEnabled(true);
					}
				});
			}
			tvCount.setText(num.toString());
			Integer totalNum=ShoppingCartManager.getInstance().getTotalNum();
			if (totalNum == 0) {
				count.setVisibility(View.INVISIBLE);
			}
			count.setText(totalNum.toString());
		}

		private void addHandle(View view) {
			Integer num = ShoppingCartManager.getInstance().addGoods(data);
			if (num == 1) {
				AnimationSet animation = AnimationUtils.getShowMinusAnimation();
				tvCount.startAnimation(animation);
				ibMinus.startAnimation(animation);

				tvCount.setVisibility(View.VISIBLE);
				ibMinus.setVisibility(View.VISIBLE);
			}
			tvCount.setText(num.toString());

			flyToShoppingCart(view);

			// 修改气泡提示
			if(count==null) {
				count = (TextView) container.findViewById(R.id.fragment_goods_tv_count);
			}

			if(num>0){
				count.setVisibility(View.VISIBLE);
			}

			Integer totalNum=ShoppingCartManager.getInstance().getTotalNum();
			count.setText(totalNum.toString());
		}

		/**
		 * Desc:  开启加入购物车的动画
		 * Params:
		 * Author:  SaGe Peng
		 * Create Time:  2017-09-13 22:05
		 */
		private void flyToShoppingCart(View view) {
			int[] location = new int[2];
			view.getLocationOnScreen(location);

			int[] targetLocation = new int[2];

			if(container==null) {
				container = (FrameLayout) UiUtils.getContainder(view, R.id.seller_detail_container);
			}
			// getLocationOnScreen获取控件所在屏幕中的位置，需要在y轴方向将状态栏的高度减掉
			location[1]-=UiUtils.STATUE_BAR_HEIGHT;
			targetLocation[1]-=UiUtils.STATUE_BAR_HEIGHT;

			container.findViewById(R.id.cart).getLocationOnScreen(targetLocation);

			final ImageButton ib = getImageButton(location,view);
			Animation addAnimation = AnimationUtils.getAddAnimation(targetLocation, location);
			ib.startAnimation(addAnimation);
			addAnimation.setAnimationListener(new AnimationUtils.AnimationListenerAdapter(){
				@Override
				public void onAnimationEnd(Animation animation) {
					container.removeView(ib);
				}
			});

		}

		private ImageButton getImageButton(int[] location, View view) {
			ImageButton v = new ImageButton(MyApplication.getContext());
			v.setX(location[0]);
			v.setY(location[1]);

			v.setBackgroundResource(R.mipmap.food_button_add);

			container.addView(v,view.getWidth(),view.getHeight());
			return v;
		}

		public ItemViewHolder(View itemView) {
			this.itemView = itemView;
			ButterKnife.inject(this, this.itemView);
		}


		public void setData(GoodItem.ListBean data) {
			this.data = data;

			//图片
			Picasso.with(MyApplication.getContext()).load(data.icon.replace("http://172.16.0.116:8080", Constants.BASEURL)).into(ivIcon);
			tvName.setText(data.name);
			if (TextUtils.isEmpty(data.form)) {
				tvZucheng.setVisibility(View.GONE);
			} else {
				tvZucheng.setVisibility(View.VISIBLE);
				tvZucheng.setText(data.form);
			}
			tvYueshaoshounum.setText("月销售" + data.monthSaleNum + "份");
			tvNewprice.setText(NumberFormatUtils.formatDigits(data.newPrice));
			if (data.oldPrice == 0) {
				tvOldprice.setVisibility(View.GONE);
			} else {
				tvOldprice.setVisibility(View.VISIBLE);
				tvOldprice.setText(NumberFormatUtils.formatDigits(data.oldPrice));
				//TextView出现中间的线
				tvOldprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
			}

			// 判断购物重是否有当前条目的商品，如果有需要获取到商品的数据量
			Integer num = ShoppingCartManager.getInstance().getGoodsIdNum(data.id);
			if(num>0){
				ibMinus.setVisibility(View.VISIBLE);
				tvCount.setVisibility(View.VISIBLE);
				tvCount.setText(num.toString());
			}else {
				ibMinus.setVisibility(View.INVISIBLE);
				tvCount.setVisibility(View.INVISIBLE);
			}
		}
	}
}
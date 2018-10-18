package com.xajd.takeout.ui.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.squareup.picasso.Picasso;
import com.xajd.takeout.MyApplication;
import com.xajd.takeout.R;
import com.xajd.takeout.model.net.bean.Category;
import com.xajd.takeout.model.net.bean.Head;
import com.xajd.takeout.model.net.bean.HomeInfo;
import com.xajd.takeout.model.net.bean.HomeItem;
import com.xajd.takeout.model.net.bean.Promotion;
import com.xajd.takeout.ui.activity.SellerDetailActivity;
import com.xajd.takeout.ui.fragment.ShoppingCartManager;
import com.xajd.takeout.utils.Constants;

/*
 *  @项目名：  TakeOut 
 *  @包名：    com.xajd.takeout.ui.adapter
 *  @文件名:   HomeRecyclerViewAdapter
 *  @创建者:   Administrator
 *  @创建时间:  2017/6/12 23:38
 *  @描述：    TODO
 */
public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
	private static final String TAG = "HomeRecyclerViewAdapter";

	public static final int TYPE_HEAD = 0;
	public static final int TYPE_SELLER = 1;
	public static final int TYPE_RECOMMEND = 2;


	private HomeInfo datas;


	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		RecyclerView.ViewHolder viewHolder = null;
		switch (viewType) {
			case TYPE_HEAD:
				viewHolder = new TitleHolder(View.inflate(MyApplication.getContext(), R.layout.item_title, null));
				break;
			case TYPE_SELLER:
				viewHolder = new SellerHolder(View.inflate(MyApplication.getContext(), R.layout.item_seller, null));
				break;
			case TYPE_RECOMMEND:
				viewHolder = new RecommendHolder(View.inflate(MyApplication.getContext(), R.layout.item_division, null));
				break;
			default:
				break;
		}
		return viewHolder;
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		int type = getItemViewType(position);
		switch (type) {
			case TYPE_HEAD:
				((TitleHolder) holder).setData(datas.head);
				break;
			case TYPE_SELLER:
				((SellerHolder) holder).setData(datas.body.get(position - 1));
				break;
			case TYPE_RECOMMEND:

				break;
			default:
				break;
		}
	}

	@Override
	public int getItemCount() {
		int count = 0;
		if (datas == null) {
			count = 0;
		} else {
			count = datas.body.size() + 1;
		}
		return count;
	}

	@Override
	public int getItemViewType(int position) {
		int currentType = -1;
		if (position == 0) {
			currentType = TYPE_HEAD;
		} else {
			currentType = datas.body.get(position - 1).type == 0 ? TYPE_SELLER : TYPE_RECOMMEND;
		}

		return currentType;
	}

	public void setData(HomeInfo homeInfo) {
		this.datas = homeInfo;
		notifyDataSetChanged();
	}

	/**
	 * Desc:  标题holder
	 * Params:
	 * Author:  SaGe Peng
	 * Create Time:  2017/9/8 10:55
	 */
	private class TitleHolder extends RecyclerView.ViewHolder {

		private SliderLayout mSliderLayout;
		private LinearLayout mCatetoryContainer;

		public TitleHolder(View view) {
			super(view);
			mSliderLayout = (SliderLayout) view.findViewById(R.id.slider);
			mCatetoryContainer = (LinearLayout) view.findViewById(R.id.catetory_container);

		}

		/**
		 * Desc:  数据绑定
		 * Params:
		 * Author:  SaGe Peng
		 * Create Time:  2017/9/8 11:29
		 */
		public void setData(Head head) {
			mSliderLayout.removeAllSliders();
			if (head != null && head.promotionList.size() > 0) {
				for (Promotion promotion : head.promotionList) {
					TextSliderView view = new TextSliderView(MyApplication.getContext());
					view.image(promotion.pic.replace("http://172.16.0.116:8080", Constants.BASEURL));//简单处理一下后台的数据
					view.description(promotion.info);
					mSliderLayout.addSlider(view);
				}
			}

			View item = null;
			mCatetoryContainer.removeAllViews();
			if (head != null && head.categorieList.size() > 0) {
				for (int i = 0; i < head.categorieList.size(); i++) {
					Category category = head.categorieList.get(i);
					if (i % 2 == 0) {
						item = View.inflate(MyApplication.getContext(), R.layout.item_home_head_category, null);
						Picasso.with(MyApplication.getContext())
								.load(category.pic.replace("http://172.16.0.116:8080", Constants.BASEURL))
								.into(((ImageView) item.findViewById(R.id.top_iv)));
						((TextView) item.findViewById(R.id.top_tv)).setText(category.name);
						mCatetoryContainer.addView(item);
					} else {
						Picasso.with(MyApplication.getContext())
								.load(category.pic.replace("http://172.16.0.116:8080", Constants.BASEURL))
								.into(((ImageView) item.findViewById(R.id.bottom_iv)));
						((TextView) item.findViewById(R.id.bottom_tv)).setText(category.name);
					}
				}


			}
		}
	}

	/**
	 * Desc:  商户holder
	 * Params:
	 * Author:  SaGe Peng
	 * Create Time:  2017/9/8 10:55
	 */
	private class SellerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

		TextView mTvTitle;
		HomeItem homeItem;
		private TextView mTvCount;

		public SellerHolder(View view) {
			super(view);
			mTvTitle = (TextView) view.findViewById(R.id.tv_title);
			mTvCount = (TextView) view.findViewById(R.id.tv_count);
		}

		public void setData(HomeItem homeItem) {
			this.homeItem = homeItem;
			String name = homeItem.seller.name;
			if (name.contains("黑马程序员")) {
				homeItem.seller.name = name.replace("黑马程序员", "西交");
			} else if (name.contains("传智播客")) {
				homeItem.seller.name = name.replace("传智播客", "苏州");
			}
			mTvTitle.setText(homeItem.seller.name);
			//mTvTitle.setText("西交(苏州)空气好");
			itemView.setOnClickListener(this);


			//处理商家的回显
			Integer totalNum = ShoppingCartManager.getInstance().getTotalNum();

			if (totalNum > 0 && homeItem.seller.id == ShoppingCartManager.getInstance().sellerId) {
				mTvCount.setVisibility(View.VISIBLE);
				mTvCount.setText(totalNum.toString());
			} else {
				mTvCount.setVisibility(View.GONE);
			}

		}

		@Override
		public void onClick(View view) {
			Intent intent = new Intent(MyApplication.getContext(), SellerDetailActivity.class);
			intent.putExtra("seller_id", homeItem.seller.id);
			intent.putExtra("name", homeItem.seller.name);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// 由于使用了Application的上下文，需要增加该配置信息（错误日志中会提示）

			if (ShoppingCartManager.getInstance().sellerId != homeItem.seller.id) {
				ShoppingCartManager.getInstance().sellerId = homeItem.seller.id;
				ShoppingCartManager.getInstance().clear();
			}

			MyApplication.getContext().startActivity(intent);
		}
	}


	/**
	 * Desc:  推荐(分割线)holder
	 * Params:
	 * Author:  SaGe Peng
	 * Create Time:  2017/9/8 10:55
	 */
	private class RecommendHolder extends RecyclerView.ViewHolder {
		public RecommendHolder(View view) {
			super(view);
		}
	}

}

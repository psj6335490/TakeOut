package com.xajd.takeout.ui.adapter;

import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.xajd.takeout.MyApplication;
import com.xajd.takeout.R;
import com.xajd.takeout.model.net.bean.GoodHead;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter {

	private int position;
	private List<GoodHead> mGoodHeadDatas = new ArrayList<>();

	public MyAdapter(List<GoodHead> goodHeadDatas) {
		mGoodHeadDatas = goodHeadDatas;
	}

	@Override
	public int getCount() {
		if (mGoodHeadDatas != null && mGoodHeadDatas.size() > 0) {
			return mGoodHeadDatas.size();
		} else {
			return 0;
		}
	}

	@Override
	public Object getItem(int i) {
		return mGoodHeadDatas.get(i);
	}

	@Override
	public long getItemId(int i) {
		return i;
	}

	@Override
	public View getView(int i, View view, ViewGroup viewGroup) {
		HeadViewHolder holder;
		if (view == null) {
			view = new TextView(MyApplication.getContext());
			holder = new HeadViewHolder(view);
			view.setTag(holder);
		} else {
			holder = (HeadViewHolder) view.getTag();
		}
		holder.setData(mGoodHeadDatas.get(i));

		if (i == position) {
			holder.itemView.setBackgroundColor(Color.WHITE);
		}

		return view;
	}

	public void setSelectPosition(int i) {
		if (i == position)
			return;
		System.out.println("刷新了");
		this.position = i;
		notifyDataSetChanged();
	}

	public class HeadViewHolder {
		private View itemView;
		private GoodHead data;

		public HeadViewHolder(View itemView) {
			this.itemView = itemView;
		}

		public void setData(GoodHead data) {
			this.data = data;
			((TextView) itemView).setText(data.info);
			((TextView) itemView).setBackgroundColor(MyApplication.getContext().getResources().getColor(R.color.colorItemBg));
			((TextView) itemView).setTextSize(12);

			int h = (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, itemView.getResources().getDisplayMetrics()) + 0.5f);

			((TextView) itemView).setLayoutParams(new ListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, h));
			((TextView) itemView).setGravity(Gravity.CENTER);
		}

	}
}
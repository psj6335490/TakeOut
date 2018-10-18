package com.xajd.takeout.presenter;

import com.xajd.takeout.model.dao.DBHelper;
import com.xajd.takeout.model.net.bean.RetrofitInfo;
import com.xajd.takeout.presenter.api.RetrofitInfoAPI;
import com.xajd.takeout.utils.Constants;
import com.xajd.takeout.utils.ErrorInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 *  @项目名：  公共操作 (数据库和网络)
 *  @包名：    com.xajd.takeout.presenter
 *  @文件名:   BasePresenter
 *  @创建者:   Administrator
 *  @创建时间:  2017/6/10 20:42
 *  @描述：    TODO
 */
public abstract class BasePresenter {
	private static final String TAG = "BasePresenter";
	protected RetrofitInfoAPI mApi;//网络
	protected DBHelper mHelper;//数据库


	public BasePresenter() {
	/*	//创建Retrofit的构造者,配置hostUrl,设置解析器
		Retrofit.Builder builder = new Retrofit.Builder();
		builder.baseUrl(Constants.BASEURL);
		builder.addConverterFactory(GsonConverterFactory.create());
		
		//创建Retrofit对象
		Retrofit retrofit = builder.build();*/
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(Constants.BASEURL)
				.addConverterFactory(GsonConverterFactory.create())
				.build();

		//创建接口接对象
		mApi = retrofit.create(RetrofitInfoAPI.class);

		mHelper = DBHelper.getInstance();

	}

	public class CallbackAdapter implements Callback<RetrofitInfo>{
		@Override
		public void onResponse(Call<RetrofitInfo> call, Response<RetrofitInfo> response) {
			if (response != null && response.isSuccessful()) {
				RetrofitInfo info = response.body();
				if (info.code.equals("0")) {
					// TODO: 2017/9/8  成功返回
					parseDate(info.data);
				} else {
					// TODO: 2017/9/8  失败
					String msg = ErrorInfo.INFO.get(info.code);
					doFail(msg);
				}
			} else {
				// TODO: 2017/9/8  失败
			}
		}


		@Override
		public void onFailure(Call<RetrofitInfo> call, Throwable t) {
		}
	}

	protected abstract void doFail(String msg);
	protected abstract void parseDate(String data) ;
}

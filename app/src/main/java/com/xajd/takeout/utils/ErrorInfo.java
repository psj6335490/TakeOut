package com.xajd.takeout.utils;

import java.util.HashMap;

/*
 *  @项目名：  TakeOut 
 *  @包名：    com.xajd.takeout.utils
 *  @文件名:   ErrorInfo
 *  @创建者:   Administrator
 *  @创建时间:  2017/9/8 9:33
 *  @描述：    TODO
 */
public class ErrorInfo {
	private static final String TAG = "ErrorInfo";

	public static HashMap<String, String> INFO = new HashMap<>();

	static {
		INFO.put("0", "数据返回成功!");
		INFO.put("1", "数据返回失败!");
	}
}

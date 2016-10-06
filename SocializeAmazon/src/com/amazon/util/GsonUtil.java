package com.amazon.util;

import com.google.gson.Gson;

public class GsonUtil {

	public static String toString(Object obj){
		Gson gson = new Gson();
		return gson.toJson(obj);
	}
	
	
}

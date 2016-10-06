package com.amazon.netty.blm;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class AmazonJsonParser {
	private static AmazonJsonParser instance = null;
	private JsonParser parser = new JsonParser();
	public AmazonJsonParser() {
		// TODO Auto-generated constructor stub
	}
	
	public static AmazonJsonParser getInstance(){
		if(instance == null){
			synchronized (AmazonCalendar.class) {
				if(instance == null){
					instance = new AmazonJsonParser();
				}
			}
		}
		return instance;
	}
	
	public JsonArray parseJson(String data){
		if(data != null){
			try{
				JsonElement elm = parser.parse(data);
				return elm.getAsJsonObject().getAsJsonArray("result");
			}catch (JsonSyntaxException e) {
				// TODO: handle exception
				System.out.println("Jsoon parse error accured : "+e.getMessage());
			}
		}
		return null;
	}
	
	public JsonObject parseJsontoObject(String data){
		if(data != null){
			try{
				JsonElement elm = parser.parse(data);
				return elm.getAsJsonObject();
			}catch (JsonSyntaxException e) {
				// TODO: handle exception
				System.out.println("Jsoon parse error accured : "+e.getMessage());
			}
		}
		return null;
	}
	
	public JsonArray parseJsonArray(String data){
		if(data != null){
			try{
				JsonElement elm = parser.parse(data);
				return elm.getAsJsonArray();
			}catch (JsonSyntaxException e) {
				// TODO: handle exception
				System.out.println("Jsoon parse error accured : "+e.getMessage());
			}
		}
		return null;
	}
}

package com.amazon.netty.bl;

import com.amazon.netty.bean.CommonBean;
import com.amazon.netty.blm.MemcacheMgr;
import com.amazon.netty.cache.Cache;
import com.amazon.netty.cache.CacheKeySuffix;
import com.amazon.netty.dao.CommonDao;
import com.amazon.netty.dao.DaoManager;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class CommonSvc {
	
	public int getNotificationCount(String networkId){
		CommonDao commonDao = DaoManager.getInstance().getCommonDao();
		JsonParser parser = new JsonParser();
		JsonElement elm = parser.parse(commonDao.getNotificationCount(networkId));
		int count = elm.getAsJsonObject().getAsJsonArray("result").get(0).getAsJsonObject().getAsJsonPrimitive("notification_count").getAsInt();
		if(count > 0){//if new notification arrived delete notification cache
			MemcacheMgr.getInstance().delete(networkId+"_"+CacheKeySuffix.NotificationList);
		}
		return count;
	}
	
	@Cache(index={0},suffix=CacheKeySuffix.NotificationList)
	public String getNotification(String networkId){
		CommonDao commonDao = DaoManager.getInstance().getCommonDao();
		JsonParser parser = new JsonParser();
		JsonElement elm = parser.parse(commonDao.getNotification(networkId));
		JsonArray array = elm.getAsJsonObject().getAsJsonArray("result");
		return array.toString();
	}
	
	public String getNetworkInventoryDetails(String networkId){
		CommonDao commonDao = DaoManager.getInstance().getCommonDao();
		return commonDao.getNetworkInventoryDetails(networkId);
	}
	
	@Cache(index={},suffix=CacheKeySuffix.PaymentMode)
	public String getPaymentMode(String networkId){
		CommonDao commonDao = DaoManager.getInstance().getCommonDao();
		return commonDao.getPaymentMode(networkId);
	}
	
	@Cache(index={0},suffix=CacheKeySuffix.BillingSettings)
	public String getBillingSettings(String storeId){
		CommonDao commonDao = DaoManager.getInstance().getCommonDao();
		return commonDao.getBillingSettings(storeId);
	}
	
	@Cache(index={0},suffix=CacheKeySuffix.MasterPoints)
	public String getPointsMasterSetting(String networkId){
		CommonDao commonDao = DaoManager.getInstance().getCommonDao();
		return commonDao.getPointsMasterSetting(networkId);
	}
	
	@Cache(index={0},suffix=CacheKeySuffix.Permission)
	public String getUserpermission(String userId){
		CommonDao commonDao = DaoManager.getInstance().getCommonDao();
		return commonDao.getUserpermission(userId);
	}

	//@Cache(index={0},suffix=CacheKeySuffix.Package)
	public String getPackages(String type) {
		// TODO Auto-generated method stub
		CommonDao commonDao = DaoManager.getInstance().getCommonDao();
		return commonDao.getPackages(type);
	}

	public String getPackagePlan(String packId) {
		// TODO Auto-generated method stub
		CommonDao commonDao = DaoManager.getInstance().getCommonDao();
		return commonDao.getPackagePlan(packId);
	}

}

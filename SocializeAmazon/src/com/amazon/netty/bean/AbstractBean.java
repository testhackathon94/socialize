package com.amazon.netty.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class AbstractBean {
	protected String storeId;
	protected String networkId;
	protected SimpleDateFormat currentDate = new SimpleDateFormat("y-M-d h:m:s");
	
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getCurrentDate() {
		return currentDate.format(new Date());
	}
	public void setCurrentDate(SimpleDateFormat currentDate) {
		this.currentDate = currentDate;
	}
	public String getNetworkId() {
		return networkId;
	}
	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}
	
}

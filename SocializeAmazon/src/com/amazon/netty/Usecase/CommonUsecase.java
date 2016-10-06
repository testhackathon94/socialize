package com.amazon.netty.Usecase;


import com.amazon.netty.bean.CommonBean;
import com.amazon.netty.bl.CommonSvc;
import com.amazon.netty.blm.AmazonJsonParser;
import com.amazon.netty.blm.Authenticate;
import com.amazon.netty.blm.PermissionKey;
import com.amazon.netty.blm.SettingServiceMgr;
import com.amazon.netty.http.WHttpRequest;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CommonUsecase {
	
	@Authenticate(permission=PermissionKey.Appointments)
	public String getNotification(WHttpRequest httpRequest,CommonBean bean){
		CommonSvc commonSvc = SettingServiceMgr.getInstance().getCommonSvc();
		int count = commonSvc.getNotificationCount(httpRequest.getSessionValue("applogged_in", "networkid"));
		String array = commonSvc.getNotification(httpRequest.getSessionValue("applogged_in", "networkid"));
		JsonParser parser = new JsonParser();
		JsonElement elm = parser.parse(array);
		JsonObject result = new JsonObject();
		result.addProperty("count",count);
		result.add("records",elm);
		return result.toString();
	}
	
	@Authenticate(permission=PermissionKey.Appointments)
	public String getNetworkInventoryDetails(WHttpRequest httpRequest){
		CommonSvc commonSvc = SettingServiceMgr.getInstance().getCommonSvc();
		String records = commonSvc.getNetworkInventoryDetails(httpRequest.getSessionValue("applogged_in", "networkid"));
		return records;
	}
	
	@Authenticate(permission=PermissionKey.Appointments)
	public String getPaymentMode(WHttpRequest httpRequest){
		CommonSvc commonSvc = SettingServiceMgr.getInstance().getCommonSvc();
		String records = commonSvc.getPaymentMode(httpRequest.getSessionValue("applogged_in", "networkid"));
		return records;
	}
	
	@Authenticate(permission=PermissionKey.Appointments)
	public String getBillingSettings(WHttpRequest httpRequest){
		CommonSvc commonSvc = SettingServiceMgr.getInstance().getCommonSvc();
		String records = commonSvc.getBillingSettings(httpRequest.getSessionValue("defaultstore", "storeid"));
		return records;
	}

	public String getPackages(String type) {
		// TODO Auto-generated method stub
		CommonSvc commonSvc = SettingServiceMgr.getInstance().getCommonSvc();
		String records = commonSvc.getPackages(type);
		JsonArray array = AmazonJsonParser.getInstance().parseJson(records);
		int index = 0;
		for (JsonElement jsonElement : array) {
			String packId = jsonElement.getAsJsonObject().getAsJsonPrimitive("rid").getAsString();
			String plans = commonSvc.getPackagePlan(packId);
			jsonElement.getAsJsonObject().add("plan", AmazonJsonParser.getInstance().parseJson(plans));
			/*List diff = Arrays.asList(jsonElement.getAsJsonObject().getAsJsonArray("features").toString().replaceAll("[\\[\\]]", "").split(","));
			System.out.println("list size :"+diff.size());
			for(int i = index; i>0;i--){
				diff = ListUtils.subtract(diff, Arrays.asList(array.get(index - i).getAsJsonObject().getAsJsonArray("features").toString().replaceAll("[\\[\\]]", "").split(",")));
			}
			System.out.println("list :"+diff.toString());
			jsonElement.getAsJsonObject().add("features", AmazonJsonParser.getInstance().parseJsonArray(diff.toString()));
			*/
			index++;
		}
		return array.toString();
	}
}

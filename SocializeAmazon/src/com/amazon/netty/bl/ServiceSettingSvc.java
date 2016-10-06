package com.amazon.netty.bl;




import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import com.amazon.netty.bean.ServiceBean;
import com.amazon.netty.cache.Cache;
import com.amazon.netty.cache.CacheKeySuffix;
import com.amazon.netty.cache.event.ServiceEvent;
import com.amazon.netty.cache.notify.InternalEventQueueManager;
import com.amazon.netty.dao.DaoManager;
import com.amazon.netty.dao.ServiceDao;
import com.amazon.netty.database.DatabaseConnectionMgr;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ServiceSettingSvc {

	
	
	
	public void addService(String storeId, String serviceName){
		System.out.println("Trying to add service in a store");
		ServiceEvent event = new ServiceEvent(storeId,CacheKeySuffix.ServiceList.getSuffix());
		try {
			InternalEventQueueManager.getInstance().putCacheEvent(event);
		} catch (InterruptedException e) {
			// need to handle properly
		}
	}
	
	public void addServiceGroup(ServiceBean bean,String storeId) throws SQLException, ClassNotFoundException{
		System.out.println("Trying to add service group in a store");
		Connection conn = DatabaseConnectionMgr.getInstance().getConnection();
		conn.setAutoCommit(false);
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("INSERT INTO service_group set name='"+bean.getServiceGroupName()+"', is_deleted = 0,created_date='"+bean.getCurrentDate()+"'");
			rs.next();
			stmt.executeUpdate("CREATE EDGE store_has_service_group from "+storeId+" to "+rs.getString("@rid"));
			rs.close();
			conn.commit();
			
			ServiceEvent event = new ServiceEvent(storeId,CacheKeySuffix.ServiceList.getSuffix());
			try {
				InternalEventQueueManager.getInstance().putCacheEvent(event);
			} catch (InterruptedException e) {
				// need to handle properly
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
				conn.rollback();
		}finally {
			if(stmt !=null){
				stmt.close();
			}
			if(conn!=null){
				conn.close();
			}
		}
		
		
	}
	
	
	@Cache(index={0}, suffix=CacheKeySuffix.ServiceList)
	public String getServiceGroups(String storeId) throws SQLException {
		ServiceDao serviceDao = DaoManager.getInstance().getServiceDao();
		String records = serviceDao.getServiceGroups(storeId);
		String serviceGroupList = this.resolveServiceGroups(records);
		return serviceGroupList;
	}
	
	
	private String resolveServiceGroups(String list){
		JsonParser parser = new JsonParser();
		JsonElement lists = parser.parse(list);
		JsonArray groupList = new JsonArray();
		JsonArray serviceList = new JsonArray(); 
		JsonArray priceList = new JsonArray(); 
		JsonObject groupObj = new JsonObject();
		JsonObject serviceObj = new JsonObject();
		
		try{
			
			
			for (Map.Entry<String, JsonElement> entry: lists.getAsJsonObject().entrySet()) {
				JsonArray array = entry.getValue().getAsJsonArray();
				for(JsonElement  elm : array){
					String clazzName =elm.getAsJsonObject().getAsJsonPrimitive("class").getAsString(); 
					if(clazzName.equalsIgnoreCase("service_group")){
						if(serviceList!=null && serviceList.size() > 0){
							groupObj.add("services", serviceList);
							serviceList = new JsonArray();
						}
						if(groupObj!=null && groupObj.entrySet().size() > 0){
							groupList.add(groupObj);
							groupObj = new JsonObject();
						}
						
						groupObj = elm.getAsJsonObject();
					}else if(clazzName.equalsIgnoreCase("store_service")){
						
						if(priceList!=null && priceList.size() > 0){
							serviceObj.add("pricing", priceList);
							priceList = new JsonArray();
						}
						
						if(serviceObj!=null && serviceObj.entrySet().size() > 0){
							serviceList.add(serviceObj);
							serviceObj = new JsonObject();
						}
						
						serviceObj = elm.getAsJsonObject();
						
					}else if(clazzName.equalsIgnoreCase("service_pricing")){
						priceList.add(elm.getAsJsonObject());
					}
				}
				
				if(serviceList!=null && serviceList.size() > 0){
					groupObj.add("services", serviceList);
				}
				if(groupObj!=null && groupObj.entrySet().size() > 0){
					groupList.add(groupObj);
				}
			    
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Json parse error:"+e.getMessage());
		}
		
		return groupList.toString();

	}
	
	
}

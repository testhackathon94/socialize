package com.amazon.netty.blm;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;

import com.amazon.netty.actions.ServiceSettingAction;
import com.amazon.netty.http.WHttpRequest;
import com.google.gson.JsonArray;

public class AuthenticationHandler {
	public Object handleTransaction(ProceedingJoinPoint pjp) throws Throwable {
		
		System.out.println("Authenticate interceptor called");
		Object result = null;
		Boolean isValidSession = checkSession(pjp);
		if(isValidSession){
			Boolean isValidPermission = checkPermisson(pjp);
			if(isValidPermission){
				result = pjp.proceed(pjp.getArgs());
				return result;
			}else{
				System.out.println("Permission Failed");
				return "Permission Failed";
			}
		}else{
			System.out.println("Session Failed");
			return "Session Failed";
		}
		
	}
	
	
	private Boolean checkPermisson(ProceedingJoinPoint pjp) throws ClassNotFoundException{
		String clazzName  = pjp.getSignature().getDeclaringTypeName();
		String calledMethodName = pjp.getSignature().getName();
		try{
			Object[] args = pjp.getArgs();
			WHttpRequest httpRequest = (WHttpRequest) args[0];
			String userId = httpRequest.getSessionValue("applogged_in", "userid");
			String role = httpRequest.getSessionValue("applogged_in", "role");
			if(role.equalsIgnoreCase("storeuser")){
				if(httpRequest.getSessionValue("defaultstore", "storeid")!=null && userId!=null){
					//To check user permission status
					Boolean flag = false;
					Class implClass = Class.forName(clazzName);
					Method[] methods = implClass.getMethods();
					for (Method m : methods) {
						if(m.getName().equals(calledMethodName)){
							Authenticate authenticate =  m.getAnnotation(Authenticate.class);
							String key = authenticate.permission().key;
							String permission = SettingServiceMgr.getInstance().getCommonSvc().getUserpermission(userId);
							JsonArray jsonArray = AmazonJsonParser.getInstance().parseJson(permission).get(0).getAsJsonObject().getAsJsonPrimitive("permission").getAsJsonArray();
							if(jsonArray.toString().contains(key)){
								flag = true;
							}
							break;
						}
					}
					System.out.println("Permission success");
					return flag; 
				}
			}else if(role.equalsIgnoreCase("store")){
				return true;
			}
		}catch (Exception e ) {
			// TODO: handle exception
			System.out.println("error " + e.getMessage());
			return false;
		}
		
		return false;
		
		
	}
	
	
	private Boolean checkSession(ProceedingJoinPoint pjp){
		try{
			Object[] args = pjp.getArgs();
			WHttpRequest httpRequest = (WHttpRequest) args[0];
			if(httpRequest.getSessionValue("defaultstore", "storeid")!=null){
				//To check Session status
				System.out.println("Session success");
				return true; 
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return false;
		}
		
		return false;
		
		
		
	}
	
}

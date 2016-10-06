package com.amazon.netty.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class DatabaseHTTPMgr {
	
	private static DatabaseHTTPMgr instance = null;
	
	private DatabaseHTTPMgr(){
	}
	
	public static DatabaseHTTPMgr getInstance(){
		if(instance == null){
			synchronized (DatabaseHTTPMgr.class) {
				if(instance == null){
					instance = new DatabaseHTTPMgr();
				}
			}
		}
		
		return instance;
	}
	
	
	public String read(String sql){
		 String stringUrl = "http://"+DatabaseConfig.Url.value+":"+DatabaseConfig.Port.value+"/query/"+DatabaseConfig.Dbname.value+"/sql/"+sql;
		 BufferedReader rd;
		    String line = null;
		    Authenticator.setDefault( new Authenticator(){
		         protected PasswordAuthentication getPasswordAuthentication(){
		            return new PasswordAuthentication(DatabaseConfig.UserName.value, DatabaseConfig.Password.value.toCharArray());
		        }
		    });
		    HttpURLConnection connection = null;
		    try {
		    	URL url = new URL(stringUrl);
		        connection = (HttpURLConnection) url.openConnection();
		        connection.setRequestMethod("GET");
		        /*Map<String, List<String>> map = connection.getHeaderFields();
		        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
		            System.out.println("Key : " + entry.getKey() +  " ,Value : " + entry.getValue());
		        }*/
		        rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		        line = rd.readLine();  
		        rd.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		        return line;
		    } catch (Exception e) {
		        e.printStackTrace();
		        return line;
		    }
		    return line;
	}
}

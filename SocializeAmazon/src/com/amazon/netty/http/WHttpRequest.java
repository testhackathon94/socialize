package com.amazon.netty.http;

import static io.netty.handler.codec.http.HttpHeaders.Names.COOKIE;
import io.netty.handler.codec.http.Cookie;
import io.netty.handler.codec.http.CookieDecoder;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;

import java.math.BigInteger;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import de.ailis.pherialize.MixedArray;
import de.ailis.pherialize.Pherialize;

public class WHttpRequest {

	private HttpRequest request;
	private StringBuilder bodyBuf;
	private Map<String, List<String>> params;
	private Set<Cookie> cookies = new HashSet<Cookie>();
	private Map<String, String> headers;
	private String hostName;
	private String userAgent;
	private boolean isConnectionKeepAlive = false;
	private HttpMethod method;
	private  MixedArray session = null;
	

	public WHttpRequest(HttpRequest request, StringBuilder bodyBuf) {
		this.request = request;
		this.bodyBuf = bodyBuf;
		init();
	}

	private void init(){
		setRequestParam();
		setCookie();
		setHeader();
		setActionMethod();
		setSession();
	}
	
	public String getBodyContent(){
		return this.bodyBuf.toString();
	}
	
	private void setActionMethod(){
		this.method = this.request.method();
	}
	
	public HttpMethod getMethod() {
		return method;
	}
	
	private void setRequestParam(){
        QueryStringDecoder queryStringDecoder = new QueryStringDecoder(request.getUri());
        params = queryStringDecoder.parameters();
	}
	
	private void setCookie(){
        String cookieString = request.headers().get(COOKIE);
        if (cookieString != null){ 
            cookies.addAll(CookieDecoder.decode(cookieString));
        }
	}
	
	private void setSession(){
		String decryptedData = null;
		String secretKey = "!QAZ1qaz!!";
		String initialVectorString = "9999999999999999";
		String rawdata = this.getCookieString("ci_session");
		if(rawdata !=null){
			String encryptedData = URLDecoder.decode(rawdata);
		
		    try {
		    	
		        SecretKeySpec skeySpec = new SecretKeySpec(md5(secretKey).getBytes(), "AES");
		        IvParameterSpec initialVector = new IvParameterSpec(initialVectorString.getBytes());
		        Cipher cipher = Cipher.getInstance("AES/CFB8/NoPadding");
		        cipher.init(Cipher.DECRYPT_MODE, skeySpec, initialVector);
		        byte[] encryptedByteArray = (new org.apache.commons.codec.binary.Base64()).decode(encryptedData.getBytes());
		        byte[] decryptedByteArray = cipher.doFinal(encryptedByteArray);
		        decryptedData = new String(decryptedByteArray, "UTF8");
			 	session = Pherialize.unserialize(decryptedData).toArray();
		    } catch (Exception e) {
		    	System.err.println("Session Parse error :"+e.getMessage());
		    }
		}
	}
	
	private String md5(String input) throws NoSuchAlgorithmException {
	    MessageDigest md = MessageDigest.getInstance("MD5");
	    byte[] messageDigest = md.digest(input.getBytes());
	    BigInteger number = new BigInteger(1, messageDigest);
	    return number.toString(16);
	}
	
	
	private void setHeader(){
		headers = new HashMap<String, String>();
        List<Map.Entry<String, String>> headerObj = request.headers().entries();
        if (!headerObj.isEmpty()) {
            for (Map.Entry<String, String> h: headerObj) {
                String key = h.getKey();
                String value = h.getValue();
                headers.put(key, value);
                if(key.equalsIgnoreCase("Host")){
                	this.hostName = value;
                }else if(key.equalsIgnoreCase("User-Agent")){
                	this.userAgent = value;
                }else if(key.equalsIgnoreCase("Connection")){
                	if(value.equalsIgnoreCase("keep-alive")){
                		this.isConnectionKeepAlive = true;
                	}
                }
            }
        }
	}
	
	public Map<String, String> getHeaders(){
		return headers;
	}
	
	public boolean isConnectionKeepAlive(){
		return isConnectionKeepAlive;
	}
	
	public String getHost(){
		return hostName; 
	}
	
	public String getUserAgent() {
		return userAgent;
	}
	
	public Map<String, List<String>> getRequestParameterMap(){
		return params;
	}
	
	public String getParameter(String name){
		if(params != null){
			List<String> values = params.get(name);
			if(values != null){
				return values.get(0);
			}
		}
		return null;
	}
	
	public String getCookieString(String name){
		if(cookies != null){
			Cookie cookie = getCookie(name);
			if(cookie != null){
				return cookie.getValue();
			}
		}
		return null;
	}
	
	public Cookie getCookie(String name){
		if(cookies != null){
			for (Iterator iterator = cookies.iterator(); iterator.hasNext();) {
				Cookie cookie = (Cookie) iterator.next();
				if(cookie.getName().equals(name)){
					return cookie;
				}
			}
		}
		return null;
	}
	public MixedArray getSession(String name){
		if(session !=null){
			return session.getArray(name);
		}
		return null;
	}
	
	public String getSessionValue(String name, String key){
		if(session !=null){
			return session.getArray(name).getString(key);
		}
		return null;
	}
	
	public String getURI(){
		String uri =request.getUri();
		if(uri.indexOf('?')!=-1){
			uri = uri.substring(0, uri.indexOf('?'));
		}
		return uri;
	}
	
}

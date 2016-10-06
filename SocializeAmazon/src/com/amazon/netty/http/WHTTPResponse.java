package com.amazon.netty.http;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.Cookie;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.ServerCookieEncoder;
import io.netty.handler.codec.http.cookie.CookieEncoder;
import io.netty.util.CharsetUtil;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;

public class WHTTPResponse {

	private HttpObject currentObj; 
	private ChannelHandlerContext ctx;
	private WHttpRequest httpRequest;
	private Map<String, Cookie> updateCookies;
	private Map<String, String> headers;

	public WHTTPResponse(HttpObject currentObj, ChannelHandlerContext ctx, WHttpRequest httpRequest) {
		super();
		this.currentObj = currentObj;
		this.ctx = ctx;
		this. httpRequest =  httpRequest;
	}

	
	/**
	 * TODO
	 * @param name
	 * @param value
	 * @param domain
	 * @param expiryDate
	 * @param path
	 */
	public void addUpdateCookie(String name, String value,String domain, Date expiryDate, String path){
		CookieEncoder cookieEncoder = new CookieEncoder(true) {
			
		};
	}
	
	public void addHeader(String name, String value){
		if(headers == null){
			headers = new HashMap<String, String>();
		}
		headers.put(name, value);
	}
	
	// TODO
	public void addUpdateCookie(Cookie cookie){
		if(updateCookies != null){
			updateCookies = new HashMap<String, Cookie>();
		}
		updateCookies.put(cookie.getName(), cookie);
	}
    public boolean writeResponse(String responseContent) {
    	return writeResponse(responseContent, ContentType.TEXT_JSON);
    }
    
   	// need to handle the error cases
    public boolean writeResponse(String responseContent, ContentType contentType) {
        // Decide whether to close the connection or not.
        boolean keepAlive = httpRequest.isConnectionKeepAlive();
        // Build the response object.
        FullHttpResponse response = new DefaultFullHttpResponse(
	                HTTP_1_1, currentObj.getDecoderResult().isSuccess()? OK : BAD_REQUEST,
	                Unpooled.copiedBuffer(responseContent, CharsetUtil.UTF_8));
        
        response.headers().set(CONTENT_TYPE, contentType.getContentType());

        if (keepAlive) {
            // Add 'Content-Length' header only for a keep-alive connection.
            response.headers().set(CONTENT_LENGTH, response.content().readableBytes());
            // Add keep alive header as per:
            // - http://www.w3.org/Protocols/HTTP/1.1/draft-ietf-http-v11-spec-01.html#Connection
            response.headers().set(CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
        }

        // write header
        if(headers != null){
        	Set<java.util.Map.Entry<String, String>> headersEntrySet =  headers.entrySet();
        	for(java.util.Map.Entry<String, String> headerEntry : headersEntrySet){
        		response.headers().add(headerEntry.getKey(), headerEntry.getValue());
        	}
        }
        
        response.headers().add("Access-Control-Allow-Origin", "http://localhost");
        response.headers().add("Access-Control-Allow-Credentials", true);
        response.headers().add("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
        response.headers().add("Access-Control-Allow-Headers","Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

        // write cokies
        if (updateCookies != null) {
        	Collection<Cookie> cookies = updateCookies.values();
        	for(Cookie cookie : cookies){
        		response.headers().add("Cookie", ServerCookieEncoder.encode(cookie));
        	}
        }
        // Write the response.
        ctx.write(response);

        return keepAlive;
    }
    
    

	
    public static enum ContentType{
    	
    	TEXT_PLAIN("text/plain; charset=UTF-8"),
    	TEXT_HTML("text/html; charset=UTF-8"),
    	GIF("image/gif"),
    	ZIP("application/zip, application/octet-stream"),
    	TEXT_XML("text/xml; charset=UTF-8"),
    	TEXT_JSON("application/json");
    	
    	private String contentType;
    	
    	private ContentType(String contentType) {
    		this.contentType  = contentType;
		}
    	public String getContentType() {
			return contentType;
		}
    }
    
}

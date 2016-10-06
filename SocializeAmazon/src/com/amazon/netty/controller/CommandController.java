package com.amazon.netty.controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

import com.amazon.exceptions.AmazonException;
import com.amazon.netty.controller.AnnotatedPathManager.PathImplementor;
import com.amazon.netty.http.WHTTPResponse;
import com.amazon.netty.http.WHttpRequest;
import com.amazon.netty.http.WHTTPResponse.ContentType;
import com.amazon.netty.validation.ValidationMgr;

import io.netty.handler.codec.http.HttpMethod;

public class CommandController {

	private static CommandController instance = null;
	private CommandController(){
	}
	
	public static CommandController getInstance(){
		if(instance == null){
			synchronized (CommandController.class) {
				if(instance == null){
					instance = new CommandController();
				}
			}
		}
		return instance;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void execute(WHttpRequest request, WHTTPResponse response) throws AmazonException{
	
		String uri = request.getURI();
		System.out.println("Request uri : "+uri);
		System.out.println("Request Method : "+request.getMethod());
		try {
//			URIElement uriElement = resolveCommand(uri);
			AnnotatedPathManager annPath = AnnotatedPathManager.getInstance();
//			System.out.println(uriElement.getActionPath());
			if(uri.equals("/favicon.ico") || request.getMethod() == HttpMethod.OPTIONS){ // todo. Need to stream favIcon
				throw new AmazonException("FavIcon to be implememted");
			}
			PathImplementor pathIml = annPath.getActionImplementor(uri);
			if(pathIml == null){
				StringBuffer message = new StringBuffer("URI : " + uri + " is not defined.");
				if("true".equalsIgnoreCase(System.getProperty("pathDebug"))){
					message .append("<br><br> Supported Path <br><br>");
					Set<String> allPaths = annPath.getAllURI();
					for(String s : allPaths){
						message.append(s).append("<br>");
					}
				}
				throw new AmazonException(message.toString());
			}
			System.out.println(pathIml.getClassDec().getName());
			Constructor constructor = pathIml.getClassDec().getConstructor(WHttpRequest.class, WHTTPResponse.class);
			Object action = constructor.newInstance(request, response);
			setBeanObject(action, request);
			//validation(action);
			String responseContent = executeMethod(action, pathIml.getMethod());
			response.writeResponse(responseContent);
		} catch (Throwable e) {
			e.printStackTrace();
//			throw new ActionMappingNotFoudException("Could not create instance for action", e);
			// TODO - temporary exception handling - need to fix
			response.writeResponse("Error in controller excute mehtod : "+e, ContentType.TEXT_HTML);
		}
		
	}
	
	private void validation(Object action){
		Object obj = ((BeanIdentifier)action).getBean();
		try {
			
			ValidationMgr.getInstance().validation(obj);
			
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	private void setBeanObject(Object action, WHttpRequest request){
		
		if(!(action instanceof BeanIdentifier)){
			return;
		}
		
		Class beanClass = ((BeanIdentifier)action).beanClass();
		
		Object beanObject = null;
		if(request.getMethod() == HttpMethod.GET || request.getMethod() == HttpMethod.OPTIONS){
			beanObject = BeanPopulator.getInstance().populateBeanByParam(request, beanClass);
		}else if(request.getMethod() == HttpMethod.PUT || request.getMethod() == HttpMethod.POST){
			beanObject = BeanPopulator.getInstance().populateBeanByJSON(request, beanClass);
		}
		
		((BeanIdentifier)action).setBean(beanObject);
		
	}
	
	private String executeMethod(Object action, Method method) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		return (String)method.invoke(action, null);
	}
	
	public URIElement resolveCommand(String uri) throws AmazonException{
		return URIElementBuilder.buildURIElement(uri); 
	}

	
	
	
}

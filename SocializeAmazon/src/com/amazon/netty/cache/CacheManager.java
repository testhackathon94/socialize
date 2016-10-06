package com.amazon.netty.cache;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;

import com.amazon.netty.blm.MemcacheMgr;

public class CacheManager {

 	public Object handleTransaction(ProceedingJoinPoint pjp) throws Throwable {

			System.out.println("Interceptor being called");
			
		// call cacheAPI here, by constructing key using pjp.getArgs(), if cache
		// is missing, then call the actual method. Then put the result from the
		// method into cache and return the response
			
			String cacheKey = resolveCacheKey(pjp);
			System.out.println("cacheKey key : " + cacheKey);
			Object result = lookupCache(cacheKey);
			
			if(result == null){
				Object[] arguments = pjp.getArgs();
				result = pjp.proceed(pjp.getArgs());
				writeToCache(cacheKey, (String)result);
			}else{
				//MemcacheMgr.getInstance().delete(cacheKey);
			}
			
			
			return result;
	}
	
 	private void writeToCache(String key, String result){
 		MemcacheMgr.getInstance().set(key, result);
 	}
 	
 	private Object lookupCache(String key){
 		// impplement cache lookup. If not record found for the key, return null
 		return MemcacheMgr.getInstance().get(key);
 	}
 	
 	
 	private String resolveCacheKey(ProceedingJoinPoint pjp) throws ClassNotFoundException{
		String clazzName = pjp.getSignature().getDeclaringTypeName();
		String calledMethodName = pjp.getSignature().getName();
		Object[] args = pjp.getArgs();
		Class implClass = Class.forName(clazzName);
		// assume method overloading is not allowed
		Method[] methods = implClass.getMethods();
		StringBuffer cacheKey = new StringBuffer();
		for(Method m : methods){
			if(m.getName().equals(calledMethodName)){
				Cache cacheAnnotation = m.getAnnotation(Cache.class);
				int[] keyIndex = cacheAnnotation.index();
				String suffix = cacheAnnotation.suffix().suffix;
				for(int i = 0 ; i < keyIndex.length; i++){
					int index = keyIndex[i];
					if(args[index] == null){
						return null;
					}else{
						cacheKey.append(args[index]).append("_");
					}
				}
				cacheKey.append(suffix);
			}
		}
		return cacheKey.toString();
 	}
 	
 	
}

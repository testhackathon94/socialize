package com.amazon.netty.cache;

@java.lang.annotation.Target(value={java.lang.annotation.ElementType.METHOD})
@java.lang.annotation.Retention(value=java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface Cache {

	String names() default "";
	CacheKeySuffix suffix() default CacheKeySuffix.Pratice;
	int[] index() default {};
}

package com.amazon.netty.blm;
@java.lang.annotation.Target(value={java.lang.annotation.ElementType.METHOD})
@java.lang.annotation.Retention(value=java.lang.annotation.RetentionPolicy.RUNTIME) 
public @interface Authenticate {

	PermissionKey permission() default PermissionKey.Settings;
}

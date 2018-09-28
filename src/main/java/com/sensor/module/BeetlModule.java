package com.sensor.module;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

@IocBean
public class BeetlModule {

	@At("/")
	@Ok("beetl:index.html")
	public void a(){
		
	}

}

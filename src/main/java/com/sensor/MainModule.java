package com.sensor;
import org.beetl.ext.nutz.BeetlViewMaker;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.annotation.Views;
import org.nutz.mvc.ioc.provider.ComboIocProvider;


@SetupBy(value=MainSetup.class)
/*@IocBy(type=ComboIocProvider.class, args={"*js", "ioc/",
                                           "*anno", "com.sensor",
                                           "*tx", // 事务拦截 aop
                                           "*async"}) // 异步执行aop
*/@IocBy(type=ComboIocProvider.class, args={
    "*js", "ioc/",
                      "*anno", "com.sensor",
                      "*tx",
                      "*quartz"})
@Modules(scanPackage=true)
@Views({BeetlViewMaker.class})
@Ok("json")
public class MainModule {
	
}


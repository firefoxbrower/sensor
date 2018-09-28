package com.sensor.service;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.service.IdNameEntityService;

import com.sensor.bean.Sensor_Basic;


@IocBean(fields="dao")
public class BasicService  extends IdNameEntityService<Sensor_Basic>{

	
}

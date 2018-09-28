package com.sensor.bean;

import java.util.Date;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Index;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.TableIndexes;

@Table("sensor_real_time")
@TableIndexes(value = { @Index(fields = { "device_id" , "collect_time" , "depth" ,"water"}, name = "first") })
public class Sensor_Real_Time {
	
	@Id
	@Column
	private Integer id ;
	
    @Column
	private Integer device_id;
	
	@Column
	private Date collect_time = null;
	

	@Column
	@ColDefine(type=ColType.FLOAT, width=8, precision=2)
	private Double depth;
	@Column
	@ColDefine(type=ColType.FLOAT, width=8, precision=2)
	private Double temp ;
	@Column
	@ColDefine(type=ColType.FLOAT, width=8, precision=2)
	private Double Ece ;
	@Column
	@ColDefine(type=ColType.FLOAT, width=8, precision=2)
	private Double water;
	@Column
	private String root_level ;
	public Integer getDevice_id() {
		return device_id;
	}
	public void setDevice_id(Integer device_id) {
		this.device_id = device_id;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getCollect_time() {
		return collect_time;
	}
	public void setCollect_time(Date collect_time) {
		this.collect_time = collect_time;
	}
	public Double getDepth() {
		return depth;
	}
	public void setDepth(Double depth) {
		this.depth = depth;
	}
	public Double getTemp() {
		return temp;
	}
	public void setTemp(Double temp) {
		this.temp = temp;
	}
	public Double getEce() {
		return Ece;
	}
	public void setEce(Double ece) {
		Ece = ece;
	}
	public Double getWater() {
		return water;
	}
	public void setWater(Double water) {
		this.water = water;
	}
	public String getRoot_level() {
		return root_level;
	}
	public void setRoot_level(String root_level) {
		this.root_level = root_level;
	}
	
	
	
}

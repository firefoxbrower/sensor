package com.sensor.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.PK;
import org.nutz.dao.entity.annotation.Table;

/**检测点
 * 
 * */
@Table("sensor_basic_t")
/*联合主键*/
@PK({"ip", "id"})
public class Sensor_Basic{

	private static final long serialVersionUID = -2031955995574649327L;
	@Column("device_ip")
	//@Id(auto=false)
	//@Name
	private String  ip;
	
	@Column("device_id")
	private int id ;
	
	@Column("type")
	private int type ;
	
	@Column
	private String longitude;
	@Column
	private String latitude;
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
}

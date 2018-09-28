package com.sensor.bean;


import java.io.Serializable;
import java.util.Date;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.PK;
import org.nutz.dao.entity.annotation.Table;

@Table("sensor_basic_details")
@PK({"sn", "id"})
public class Sensor_Basic_Details implements Serializable {
	
	private static final long serialVersionUID = -5986002427452331204L;
	
	 @Column("sn")
	 @Name
	 private String sn ;
	 
	 @Column
	 private int id;
	 
	 @Column
	 private String name ;
	 
	 @Column
	 private String root;
	 @Column
	 private Double et ;
	 @Column
	 private String water_storage ;
	 @Column
	 private String type;
	 
	 @Column
	 private String crop ;
	 
	 
	 @Column
	 private String area;
	 
	 @Column
	 private String lng ;
	 
	 @Column
	 private String lat;
	 
	 @Column
	 private Date lastest_collect_time;
	 
	 @Column
	 private Date cretate_time;
	 
	 @Column
	 private String status;
		/**       sn
		 *  basic -----> detail 一对一完整配置
		 *  
		 *  其中detail 中sn 来自与basic的ip。
		 */
	 @One(target=Sensor_Basic.class ,field="sn" , key="ip" )
	 private Sensor_Basic sensor_basic ;
	 
	 

	public Sensor_Basic getSensor_basic() {
			return sensor_basic;
		}
		public void setSensor_basic(Sensor_Basic sensor_basic) {
			this.sensor_basic = sensor_basic;
		}
		
		public Double getEt() {
			return et;
		}
		public void setEt(Double et) {
			this.et = et;
		}
	public String getRoot() {
			return root;
		}
		public void setRoot(String root) {
			this.root = root;
		}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
    

	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
    

	public Date getLastest_collect_time() {
		return lastest_collect_time;
	}
	public void setLastest_collect_time(Date lastest_collect_time) {
		this.lastest_collect_time = lastest_collect_time;
	}
	public Date getCretate_time() {
		return cretate_time;
	}
	public void setCretate_time(Date cretate_time) {
		this.cretate_time = cretate_time;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getCrop() {
		return crop;
	}
	public void setCrop(String crop) {
		this.crop = crop;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	 
	 
}

package com.sensor.bean;

import java.util.Date;
import java.util.List;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.ManyMany;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.PK;
import org.nutz.dao.entity.annotation.Table;

@Table("dacuser")
@PK({"username", "password"})
public class User   {

  @Column
  private String username;
  @Column
  private String password ;
  @Column
  private String phone ;
  @Column
  private Integer usertype;
  @Column
  private String record_name;

  
public Integer getUsertype() {
	return usertype;
}
public void setUsertype(Integer usertype) {
	this.usertype = usertype;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getRecord_name() {
	return record_name;
}
public void setRecord_name(String record_name) {
	this.record_name = record_name;
}
  
   
}
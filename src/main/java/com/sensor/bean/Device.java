package com.sensor.bean;

import java.io.Serializable;
import java.util.List;

import org.nutz.dao.DB;
import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Many;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.SQL;
import org.nutz.dao.entity.annotation.Table;



/**
 * 设备
 * 
 * @author xuechangbao
 *
 */
@Table("com_device")
public class Device implements Serializable {

	/**
	 * 序列化标识
	 */
	private static final long serialVersionUID = 4268215570468465252L;

	@Column
	@Name
	@Comment("ID")
	@ColDefine(type = ColType.VARCHAR, width = 32)
	@Prev(els = { @EL("uuid()") })
	private String id;

	@Column
	@Comment("父级Id")
	@ColDefine(type = ColType.VARCHAR, width = 32)
	private String parentId;

	@Column
	@Comment("设备类型Id")
	@ColDefine(type = ColType.VARCHAR, width = 32)
	private String dictId;

	@ColDefine(type = ColType.VARCHAR, width = 32)
	@Comment("楼层、区域ID")
	@Column
	private String buildingId;

	@ColDefine(type = ColType.VARCHAR, width = 32)
	@Comment("单位Id")
	@Column
	private String unitid;

	@Column
	@Comment("树路径")
	@ColDefine(type = ColType.VARCHAR, width = 100)
	private String path;

	@Column
	@Comment("设备编号")
	@ColDefine(type = ColType.VARCHAR, width = 32)
	private String deviceNo;

	@Column
	@Comment("设备名称")
	@ColDefine(type = ColType.VARCHAR, width = 32)
	private String deviceName;

	@Column
	@Comment("设备位置")
	@ColDefine(type = ColType.VARCHAR, width = 32)
	private String deviceSite;

	@Column
	@Comment("设备ID")
	@ColDefine(type = ColType.VARCHAR, width = 32)
	private String deviceIdCode;

	@Column
	@Comment("设备型号")
	@ColDefine(type = ColType.VARCHAR, width = 32)
	private String deviceModel;

	@Column
	@Comment("设备状态（正常，故障）")
	@ColDefine(type = ColType.CHAR, width = 1)
	private String deviceStatus;

	@Column
	@Comment("设备负责人")
	@ColDefine(type = ColType.VARCHAR, width = 32)
	private String deviceManager;

	@Column
	@Comment("安装日期")
	@ColDefine(type = ColType.INT, width = 10)
	private Long deviceInstalDate;

	@Column
	@Comment("描述")
	@ColDefine(type = ColType.VARCHAR, width = 100)
	private String remark;

	@Column
	@Comment("开关状态")
	@ColDefine(type = ColType.CHAR, width = 1)
	private String switchStatus;

	@Column
	@Comment("排序字段")
	@Prev({ @SQL(db = DB.MYSQL, value = "SELECT IFNULL(MAX(location),0)+1 FROM com_device"),
			@SQL(db = DB.ORACLE, value = "SELECT COALESCE(MAX(location),0)+1 FROM com_device") })
	private Integer location;

	// 关联字典表
/*	@One(field = "dictId")
	private Sys_dict dict;*/

	@Column
	@Comment("有子节点")
	private boolean hasChildren;

	// 自关联
	@Many(field = "parentId")
	private List<Device> children;

/*	@One(field = "buildingId")
	private Sys_floor floor;*/

	private String deInstalDate;

	// 上级设备名称
	private String preDeviceName;

	// 所在区域
	private String deviceAreas;

	public List<Device> getChildren() {
		return children;
	}

	public void setChildren(List<Device> children) {
		this.children = children;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getDictId() {
		return dictId;
	}

	public void setDictId(String dictId) {
		this.dictId = dictId;
	}

	public String getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}	

	public String getUnitid() {
		return unitid;
	}

	public void setUnitid(String unitid) {
		this.unitid = unitid;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceSite() {
		return deviceSite;
	}

	public void setDeviceSite(String deviceSite) {
		this.deviceSite = deviceSite;
	}

	public String getDeviceIdCode() {
		return deviceIdCode;
	}

	public void setDeviceIdCode(String deviceIdCode) {
		this.deviceIdCode = deviceIdCode;
	}

	public String getDeviceModel() {
		return deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	public String getDeviceManager() {
		return deviceManager;
	}

	public void setDeviceManager(String deviceManager) {
		this.deviceManager = deviceManager;
	}

	public Long getDeviceInstalDate() {
		return deviceInstalDate;
	}

	public void setDeviceInstalDate(Long deviceInstalDate) {
		this.deviceInstalDate = deviceInstalDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getLocation() {
		return location;
	}

	public void setLocation(Integer location) {
		this.location = location;
	}

	public boolean isHasChildren() {
		return hasChildren;
	}

	public void setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
	}

	

	public String getDeInstalDate() {
		return deInstalDate;
	}

	public void setDeInstalDate(String deInstalDate) {
		this.deInstalDate = deInstalDate;
	}

	public String getDeviceStatus() {
		return deviceStatus;
	}

	public void setDeviceStatus(String deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

	public String getSwitchStatus() {
		return switchStatus;
	}

	public void setSwitchStatus(String switchStatus) {
		this.switchStatus = switchStatus;
	}

	public String getPreDeviceName() {
		return preDeviceName;
	}

	public void setPreDeviceName(String preDeviceName) {
		this.preDeviceName = preDeviceName;
	}

	public String getDeviceAreas() {
		return deviceAreas;
	}

	public void setDeviceAreas(String deviceAreas) {
		this.deviceAreas = deviceAreas;
	}

}

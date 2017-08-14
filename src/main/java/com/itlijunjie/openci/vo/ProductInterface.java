/**
 * 产品接口表
 */
package com.itlijunjie.openci.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 接口对象
 * @author lijunjie
 *
 */
@Entity
@Table(name="t_product_interface")
public class ProductInterface {
	/**
	 * 接口标识
	 */
	private int ifid;
	private String ifname;
	private String ifdescription;
	private String ifresule;
	private String ifparameter;
	private String productname;
	
	public ProductInterface(int ifid, String ifname, String ifdescription,
			String ifresule, String ifparameter, String productname) {
		super();
		this.ifid = ifid;
		this.ifname = ifname;
		this.ifdescription = ifdescription;
		this.ifresule = ifresule;
		this.ifparameter = ifparameter;
		this.productname = productname;
	}

	public ProductInterface() {
		super();
	}

	@Id
	@GeneratedValue
	public int getIfid() {
		return ifid;
	}
	/**
	 * @param ifid the ifId to set
	 */
	public void setIfid(int ifid) {
		this.ifid = ifid;
	}
	/**
	 * @return the ifName
	 */
	@NotEmpty(message="接口名不能为空")
	public String getIfname() {
		return ifname;
	}
	/**
	 * @param ifname the ifName to set
	 */
	public void setIfname(String ifname) {
		this.ifname = ifname;
	}
	/**
	 * @return the ifDescription
	 */
	@NotEmpty(message="接口描述不能为空")
	public String getIfdescription() {
		return ifdescription;
	}
	/**
	 * @param ifdescription the ifDescription to set
	 */
	public void setIfdescription(String ifdescription) {
		this.ifdescription = ifdescription;
	}
	/**
	 * @return the ifResule
	 */
	@NotEmpty(message="接口返回结果不能为空")
	public String getIfresule() {
		return ifresule;
	}
	/**
	 * @param ifresule the ifResule to set
	 */
	public void setIfresule(String ifresule) {
		this.ifresule = ifresule;
	}
	/**
	 * @return the ifParameter
	 */
	@NotEmpty(message="接口参数不能为空")
	public String getIfparameter() {
		return ifparameter;
	}
	/**
	 * @param ifparameter the ifParameter to set
	 */
	public void setIfparameter(String ifparameter) {
		this.ifparameter = ifparameter;
	}
	/**
	 * @return the productName
	 */
	@NotEmpty(message="接口所属项目名称不能为空")
	public String getProductname() {
		return productname;
	}
	/**
	 * @param productname the productName to set
	 */
	public void setProductname(String productname) {
		this.productname = productname;
	}
	
}

/**
 * 用户表
 */
package com.itlijunjie.openci.vo;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_jenkins")
/**
 * 用户对象
 * @author lijunjie
 *
 */
public class Jenkins {
	/**
	 * 用户标识，数据库主键
	 */
	private int id;
	/**
	 * 用户的昵称
	 */
	private String url;

	private String serverName;
	/**
	 * 用户名，也是唯一的
	 */
	private String username;
	/**
	 * 用户的密码
	 */
	private String password;
	/**
	 * 用户的邮箱
	 */
	private String description;
	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the url
	 */
	@NotEmpty(message="服务器url不能为空")
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the serverName
	 */
	@NotEmpty(message="服务器名称不能为空")
	public String getServerName() {
		return serverName;
	}
	/**
	 * @param serverName the serverName to set
	 */
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	/**
	 * @return the username
	 */
	@NotEmpty(message="用户名不能为空")
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	@NotEmpty(message="密码不能为空")
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}

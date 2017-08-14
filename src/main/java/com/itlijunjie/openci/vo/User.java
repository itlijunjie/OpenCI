/**
 * 用户表
 */
package com.itlijunjie.openci.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="t_user")
/**
 * 用户对象
 * @author lijunjie
 *
 */
public class User {
	/**
	 * 用户标识，数据库主键
	 */
	private int id;
	/**
	 * 用户名，也是唯一的
	 */
	private String username;
	/**
	 * 用户的密码
	 */
	private String password;
	/**
	 * 用户的昵称
	 */
	private String nickname;
	/**
	 * 用户的邮箱
	 */
	private String email;
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
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * @return the email
	 */
	@Email(message="邮箱格式不正确")
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
}

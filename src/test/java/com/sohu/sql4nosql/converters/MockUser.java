/**
 * 
 */
package com.sohu.sql4nosql.converters;

import java.util.Date;

/**
 * @author Administrator
 *
 */
public class MockUser {
	/**
	 * Ψһ��ʾ
	 */
	private Integer id;
	/**
	 * �û���
	 */
	private String name;
	private Date birthday;
	
	public MockUser(Integer id, String name, Date birthday) {
		super();
		this.id = id;
		this.name = name;
		this.birthday = birthday;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
}

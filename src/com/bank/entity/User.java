package com.bank.entity;

public class User {
	private int id;
	private String name;
	private String pwd;
	private String repwd;
	private String phone;
	private String email;
	private String sex;
	private float balance;
	/**
	 * 用户组，1--普通用户，0--管理员
	 */
	private int groupId;

	public User(int id, String name, String pwd, String phone,
			String email, String sex, float balance, int groupId) {
		super();
		this.id = id;
		this.name = name;
		this.pwd = pwd;
		this.repwd = repwd;
		this.phone = phone;
		this.email = email;
		this.sex = sex;
		this.balance = balance;
		this.groupId = groupId;
	}

	public User() {
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", pwd=" + pwd
				+ ", phone=" + phone + ", email=" + email + ", sex=" + sex
				+ ", groupId=" + groupId + "]";
	}
	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getRepwd() {
		return repwd;
	}

	public void setRepwd(String repwd) {
		this.repwd = repwd;
	}
	public User(String name, String pwd, String phone, String email, String sex) {
		super();
		this.name = name;
		this.pwd = pwd;
		this.phone = phone;
		this.email = email;
		this.sex = sex;
	}

}

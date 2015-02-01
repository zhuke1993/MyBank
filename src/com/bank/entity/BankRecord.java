package com.bank.entity;

public class BankRecord {
	private String br_action;
	private String br_dateTime;
	private int br_fromId;
	private int br_toId;
	private float br_money;
	public String getBr_action() {
		return br_action;
	}
	public void setBr_action(String br_action) {
		this.br_action = br_action;
	}
	public String getBr_dateTime() {
		return br_dateTime;
	}
	public void setBr_dateTime(String br_dateTime) {
		this.br_dateTime = br_dateTime;
	}
	public int getBr_fromId() {
		return br_fromId;
	}
	public void setBr_fromId(int br_fromId) {
		this.br_fromId = br_fromId;
	}
	public int getBr_toId() {
		return br_toId;
	}
	public void setBr_toId(int br_toId) {
		this.br_toId = br_toId;
	}
	public float getBr_money() {
		return br_money;
	}
	public void setBr_money(float br_money) {
		this.br_money = br_money;
	}
	
}

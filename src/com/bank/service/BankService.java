package com.bank.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.bank.entity.BankRecord;
import com.bank.entity.User;

public interface BankService {
	/**
	 * 存款业务
	 */
	String saveMoney(int id,float saving_money)
			throws SQLException;
	/**
	 * 取款业务
	 */
	String fetchMoney(int id,float fetch_money)
			throws SQLException;
	/**
	 * 查询帐户信息
	 */
	User selectBankAccount(int id)
			throws SQLException; 
	/**
	 * 查询当前登录用户所有交易信息
	 */
	ArrayList<BankRecord> selectBankRecord(int id,String password) 
			throws SQLException; 
	/**
	 * 转账
	 */
	public String transferAccount(int br_fromId, int br_toId,
			float money) throws SQLException; 
}

package com.bank.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import com.bank.dao.BankDao;
import com.bank.dao.impl.BankDaoImpl;
import com.bank.entity.BankRecord;
import com.bank.entity.User;
import com.bank.service.BankService;

public class BankServiceImpl implements BankService{
	
	private BankDao bankDao;
	
	public BankServiceImpl(){
		bankDao = new BankDaoImpl();
	}
	//存款
	public String saveMoney(int id, float saving_money) throws SQLException {
		return bankDao.saveMoney(id, saving_money);
	}

	//取款
	public String fetchMoney(int id, float fetch_money) throws SQLException {
		return bankDao.fetchMoney(id, fetch_money);
	}

	//查询账户信息
	public User selectBankAccount(int id) throws SQLException {
		return bankDao.selectBankAccount(id);
	}

	//查询记录信息
	public ArrayList<BankRecord> selectBankRecord(int id, String password)
			throws SQLException {
		return bankDao.selectBankRecord(id, password);
	}

	//转账
	public String transferAccount(int br_fromId, int br_toId, float money)
			throws SQLException {
		return bankDao.transferAccount(br_fromId, br_toId, money);
	}
	
}

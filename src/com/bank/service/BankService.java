package com.bank.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.bank.entity.BankRecord;
import com.bank.entity.User;

public interface BankService {
	/**
	 * ���ҵ��
	 */
	String saveMoney(int id,float saving_money)
			throws SQLException;
	/**
	 * ȡ��ҵ��
	 */
	String fetchMoney(int id,float fetch_money)
			throws SQLException;
	/**
	 * ��ѯ�ʻ���Ϣ
	 */
	User selectBankAccount(int id)
			throws SQLException; 
	/**
	 * ��ѯ��ǰ��¼�û����н�����Ϣ
	 */
	ArrayList<BankRecord> selectBankRecord(int id,String password) 
			throws SQLException; 
	/**
	 * ת��
	 */
	public String transferAccount(int br_fromId, int br_toId,
			float money) throws SQLException; 
}

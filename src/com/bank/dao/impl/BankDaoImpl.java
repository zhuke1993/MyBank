package com.bank.dao.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bank.dao.BankDao;
import com.bank.entity.BankRecord;
import com.bank.entity.User;
import com.bank.util.HibernateUtil;

public class BankDaoImpl implements BankDao {
	//测试
	public static void main(String[] args) throws Exception {
		BankDaoImpl ba = new BankDaoImpl();
		//System.out.println(ba.saveMoney(2, 100));
		//ba.fetchMoney(2, 200);
		//ArrayList<BankRecord> br = new ArrayList<BankRecord>();
		/*br = ba.selectBankRecord(2,"123");
		for (BankRecord bankRecord : br) {
			System.out.println(bankRecord.getBr_action());
		}*/
		//ba.transferAccount(2,3,100);
		ba.transferAccount(2, 3, 1000);
		
		
	}
	Transaction ts = null;
	Session session = null;
	//存款业务
	public String saveMoney(int id ,float saving_money) throws SQLException {
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			Query hql1 = session.createQuery("update User a set a.balance "
					+ "= a.balance+? where a.id = ?");
			hql1.setParameter(0, saving_money).setParameter(1, id);
			int result = hql1.executeUpdate();// 1 success & 0 fail
			session.flush();
			session.getTransaction().commit();
			if (result == 1) {
				Session s2 = HibernateUtil.openSession();
				s2.beginTransaction();
				Query q1 = s2.createQuery("from User a where a.id=?");
				q1.setParameter(0, id);
				User user = (User) (q1.uniqueResult());
				float balance = user.getBalance();
				s2.close();
				// 添加纪录
				Session s3 = HibernateUtil.openSession();
				s3.beginTransaction();
				BankRecord record = new BankRecord();
				record.setBr_fromId(id);
				record.setBr_money(saving_money);
				Date date = Calendar.getInstance().getTime();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String dateString = formatter.format(date);
				record.setBr_dateTime(dateString);
				record.setBr_action("存款");
				s3.save(record);
				s3.getTransaction().commit();
				s3.close();
				return "save success...Your account balance is：￥" + balance;
			} else if (result == 0) {
				return "save fail！";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			ts.rollback();
		} finally {
			HibernateUtil.close(session);
		}
		return null;
	}

	//取款业务
	public String fetchMoney(int id,float fetch_money) throws SQLException {
		session = HibernateUtil.openSession();
		session.beginTransaction();
		Query hql1 = session.createQuery("update User a set a.balance "
				+ "= a.balance-? where a.id = ?");
		hql1.setParameter(0, fetch_money).setParameter(1, id);
		int result = hql1.executeUpdate();// 1 success & 0 fail
		session.flush();
		session.getTransaction().commit();
		session.close();
		if (result == 1) {
			Session s2 = HibernateUtil.openSession();
			s2.beginTransaction();
			Query q1 = s2.createQuery("from User a where a.id=?");
			q1.setParameter(0, id);
			User user = (User) (q1.uniqueResult());
			float balance = user.getBalance();
			s2.close();
			// 添加纪录
			Session s3 = HibernateUtil.openSession();
			s3.beginTransaction();
			BankRecord record = new BankRecord();
			record.setBr_fromId(id);
			record.setBr_money(fetch_money);
			Date date = Calendar.getInstance().getTime();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateString = formatter.format(date);
			record.setBr_dateTime(dateString);
			record.setBr_action("取款");
			s3.save(record);
			s3.getTransaction().commit();
			s3.close();
			return "success;" + "balance:" + (balance);
		} else {
			return "error!";
		}
	}

	//查询账户信息业务
	public User selectBankAccount(int id) throws SQLException {
		
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		try {
			Query q1 = s.createQuery("from User a where a.id=?")
					.setParameter(0, id);
			User user = (User) q1.uniqueResult();
			if(q1.list().isEmpty()){
				return null;
			}
			s.flush();
			s.getTransaction().commit();
			s.close();
			return user;
		} catch (Exception e) {
			return null;
		}
	}

	//查询交易信息业务
	public ArrayList<BankRecord> selectBankRecord(int id,String password) throws SQLException {
		Session s = HibernateUtil.openSession();
		Transaction tc = s.beginTransaction();
		ArrayList<BankRecord> recorddetails = new ArrayList<BankRecord>();
		try {
			Query q2 = s.createQuery("from User a where a.id=?")
					.setParameter(0, id);
			User user = (User) q2.uniqueResult();
			if(user.getPwd().trim().equals(password))
			{
				Query q1 = s.createQuery("from BankRecord r where r.br_fromId=?")
						.setParameter(0, id);
				ArrayList<BankRecord> records = (ArrayList<BankRecord>)q1.list();
				int i = 0;
				for(BankRecord record:records){
					BankRecord br = new BankRecord();
					br.setBr_dateTime(record.getBr_dateTime().toString());
					br.setBr_action(record.getBr_action());
					br.setBr_fromId(record.getBr_fromId());
					br.setBr_money(record.getBr_money());
					br.setBr_toId(record.getBr_toId());
					recorddetails.add(br);
					i++;
				}
			}else
			{
				return null;
			}
			s.getTransaction().commit();
			s.close();
			return recorddetails;
		} catch (Exception e) {
			tc.rollback();
			return null;
		}
	}
	
	//转账业务
	public String transferAccount(int br_fromId, int br_toId,
			float money) throws SQLException{
				Session s = HibernateUtil.openSession();
				s.beginTransaction();
				float balance = (float)0.0;
				try {
					Query q1 = s.createQuery("from User a where a.id=?")
							.setParameter(0, br_fromId);
					Query q2 = s.createQuery("from User a where a.id=?")
							.setParameter(0, br_toId);
					User user1 = (User) q1.uniqueResult();
					User user2 = (User) q2.uniqueResult();
					balance = user1.getBalance() - money;
					user1.setBalance(user1.getBalance()-money);
					user2.setBalance(user2.getBalance()+money);
					s.createQuery(
							"update User a set a.balance=? where a.id=?")
							.setParameter(0,user1.getBalance())
							.setParameter(1, br_fromId).executeUpdate();
					s.createQuery(
							"update User a set a.balance=? where a.id=?")
							.setParameter(0,user2.getBalance())
							.setParameter(1, br_toId).executeUpdate();
					s.flush();
					s.getTransaction().commit();
					s.close();
					//插入记录
					Session s2 = HibernateUtil.openSession();
					s2.beginTransaction();
					BankRecord record1 = new BankRecord();
					record1.setBr_fromId(br_fromId);
					record1.setBr_toId(br_toId);
					record1.setBr_money(money);
					Date date = Calendar.getInstance().getTime();
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String dateString = formatter.format(date);
					record1.setBr_dateTime(dateString);
					record1.setBr_action("用户转出金额");
					s2.save(record1);
					s2.getTransaction().commit();
					s2.close();
					
					Session s3 = HibernateUtil.openSession();
					s3.beginTransaction();
					BankRecord record2 = new BankRecord();
					record2.setBr_fromId(br_toId);
					record2.setBr_toId(br_fromId);
					record2.setBr_money(money);
					record2.setBr_dateTime(dateString);
					record2.setBr_action("用户转入金额");
					s3.save(record2);
					s3.flush();
					s3.getTransaction().commit();
					s3.close();
				} catch (Exception e) {
					return "error";
				}
				return "success_"+balance;
			}
}

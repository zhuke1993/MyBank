package com.bank.dao;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bank.entity.User;
import com.bank.util.HibernateUtil;
import com.google.gson.Gson;

public class UserDao {
	//����
	public static void main(String[] args){
		try {
			
			System.out.println(new UserDao().deleteUser(4));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * �޸�User
	 * 
	 * @param user
	 * @return
	 */

	public int modifyUser(User user) {

		// ����User�����޸���ӦId��User����
		Transaction ts = null;
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			ts = session.beginTransaction();
			// ����get�����
			User thisu = (User) session.get(User.class, user.getId());
			thisu.setName(user.getName());
			thisu.setPwd(user.getPwd());
			thisu.setEmail(user.getEmail());
			thisu.setPhone(user.getPhone());
			thisu.setSex(user.getSex());
			session.flush();
			ts.commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			ts.rollback();
			return 0;
		} finally {
			HibernateUtil.close(session);
		}
	}

	
	/**
	 * �õ�������ͨ�û�
	 * 
	 * @return
	 */
	public ArrayList<User> allList() {
		Transaction ts = null;
		Session session = null;
		ArrayList<User> list = new ArrayList<>();
		String s;
		s = "from User as u where u.groupId =1 ";
		try {
			session = HibernateUtil.openSession();
			ts = session.beginTransaction();
			Query q = session.createQuery(s);
			list = (ArrayList<User>) q.list();
			session.flush();
			ts.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			ts.rollback();
			return null;
		} finally {
			HibernateUtil.close(session);
		}
	}
	
	
	/**
	 * �õ�������ͨ�û�
	 * 
	 * @return
	 */
	public String  allList(int page, int rows) {
		Transaction ts = null;
		Session session = null;
		ArrayList<User> list = new ArrayList<>();
		String s,s1;
		s = "from User as u where u.groupId =1";
		s1 = "from User as u where u.groupId =1";
		try {
			session = HibernateUtil.openSession();
			ts = session.beginTransaction();
			Query q = session.createQuery(s);
			Query q1 = session.createQuery(s1);
			int total = q1.list().size();
			q.setFirstResult(rows*(page-1));
			q.setMaxResults(rows*page);
			System.out.println(q.getQueryString());
			list = (ArrayList<User>) q.list();
			session.flush();
			ts.commit();
			//ƴ��json�ַ���
			StringBuilder sb = new StringBuilder();
			sb.append("{\"total\":"+total+",\"rows\":");
			Gson json = new Gson();
			sb.append(json.toJson(list)+"}");
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			ts.rollback();
			return null;
		} finally {
			HibernateUtil.close(session);
		}
	}

	/**
	 * �����û����õ��û���ȫ����Ϣ
	 * 
	 * @param name
	 * @return ʧ���򷵻�null
	 */
	public User getUser(String name) {
		Transaction ts = null;
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			ts = session.beginTransaction();
			Query q = session.createQuery("from User u where u.name = ?");
			q.setString(0, name);
			ArrayList<User> list = (ArrayList<User>) q.list();
			session.flush();
			ts.commit();
			return list.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			ts.rollback();
			return null;
		} finally {
			HibernateUtil.close(session);
		}
	}
	
	public User getUser(int id) {
		Transaction ts = null;
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			ts = session.beginTransaction();
			return (User) session.get(User.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			ts.rollback();
			return null;
		} finally {
			HibernateUtil.close(session);
		}
	}

	/**
	 * ���û�ע��
	 * 
	 * @param user
	 * @return 1--ע��ɹ���0--ע��ʧ��
	 */
	public int register(User user) {
		Transaction ts = null;
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			ts = session.beginTransaction();
			session.save(user);
			session.flush();
			ts.commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			ts.rollback();
			return 0;
		} finally {
			HibernateUtil.close(session);
		}
	}

	/**
	 * ��½��֤
	 * 
	 * @param name
	 * @param pwd
	 * @return
	 */
	public int isLogin(String name, String pwd) {
		Session session = HibernateUtil.openSession();
		Transaction ts = null;
		try {
			ts = session.beginTransaction();
			Query query = session
					.createQuery("from User as u where u.name=? and u.pwd = ?");
			query.setString(0, name);
			query.setString(1, pwd);
			ArrayList<User> user = (ArrayList<User>) query.list();
			if (user.size() != 0) {
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			ts.rollback();
			return 0;
		} finally {
			HibernateUtil.close(session);
		}
	}

	/**
	 * ��ָ֤�����û����Ƿ����
	 * 
	 * @param name
	 *            �û���
	 * @return 0--�����ڣ�1--����
	 */
	public int isNameExist(String name) {

		Session session = HibernateUtil.openSession();
		Transaction ts = null;
		try {
			ts = session.beginTransaction();
			Query query = session.createQuery("from User as u where u.name=?");
			query.setString(0, name);
			ArrayList<User> user = (ArrayList<User>) query.list();
			// ����
			if (user.size() != 0) {
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			ts.rollback();
			// ������
			return 0;
		} finally {
			HibernateUtil.close(session);
		}
		return 0;
	}
	
	/**
	 * �����ݿ���ɾ���û�
	 * @param id
	 * @return  1-success, 0-faild
	 */
	public int deleteUser(int id){
		Session session = HibernateUtil.openSession();
		Transaction ts = null;
		try {
			ts = session.beginTransaction();
			User user = new UserDao().getUser(id);
			session.delete(user);
			ts.commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			ts.rollback();
			// ������
			return 0;
		} finally {
			HibernateUtil.close(session);
		}
	}

}

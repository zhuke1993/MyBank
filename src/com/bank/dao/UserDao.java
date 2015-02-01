package com.bank.dao;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bank.entity.User;
import com.bank.util.HibernateUtil;
import com.google.gson.Gson;

public class UserDao {
	//测试
	public static void main(String[] args){
		try {
			
			System.out.println(new UserDao().deleteUser(4));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * 修改User
	 * 
	 * @param user
	 * @return
	 */

	public int modifyUser(User user) {

		// 传入User对象，修改相应Id的User对象
		Transaction ts = null;
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			ts = session.beginTransaction();
			// 首先get对象的
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
	 * 得到所有普通用户
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
	 * 得到所有普通用户
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
			//拼接json字符串
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
	 * 根据用户名得到用户的全部信息
	 * 
	 * @param name
	 * @return 失败则返回null
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
	 * 新用户注册
	 * 
	 * @param user
	 * @return 1--注册成功，0--注册失败
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
	 * 登陆验证
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
	 * 验证指定的用户名是否存在
	 * 
	 * @param name
	 *            用户名
	 * @return 0--不存在，1--存在
	 */
	public int isNameExist(String name) {

		Session session = HibernateUtil.openSession();
		Transaction ts = null;
		try {
			ts = session.beginTransaction();
			Query query = session.createQuery("from User as u where u.name=?");
			query.setString(0, name);
			ArrayList<User> user = (ArrayList<User>) query.list();
			// 存在
			if (user.size() != 0) {
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			ts.rollback();
			// 不存在
			return 0;
		} finally {
			HibernateUtil.close(session);
		}
		return 0;
	}
	
	/**
	 * 从数据库中删除用户
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
			// 不存在
			return 0;
		} finally {
			HibernateUtil.close(session);
		}
	}

}

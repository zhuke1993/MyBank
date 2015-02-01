package com.bank.action;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.DefaultActionSupport;

import com.bank.entity.BankRecord;
import com.bank.entity.User;
import com.bank.service.BankService;
import com.bank.service.UserService;
import com.bank.service.impl.BankServiceImpl;
import com.bank.service.impl.UserServiceImpl;

public class BankAction extends DefaultActionSupport {
	private BankService bankService;
	private UserService userService;
	private User user;
	private BankRecord br;
	
	private float money;
	private float balance;
	private int toId;

	public int getToId() {
		return toId;
	}
	public void setToId(int toId) {
		this.toId = toId;
	}

	private List<BankRecord> list;
	
	public List<BankRecord> getList() {
		return list;
	}
	public void setList(List<BankRecord> list) {
		this.list = list;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public float getMoney() {
		return money;
	}
	public void setMoney(float money) {
		this.money = money;
	}
	public BankAction(){
		bankService = new BankServiceImpl();
		userService = new UserServiceImpl();
		user = new User();
		br = new BankRecord();
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	//取款
	public String fetchMoney() throws SQLException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		int id = ((User)session.getAttribute("loginUser")).getId();
		float balance = bankService.selectBankAccount(id).getBalance();
		//如果取款金额大于存款金额则提示出错
		if(money>balance){
			return "fetchError";
		}else{
			bankService.fetchMoney(id, getMoney());
			return "fetchSuccess";
		}
	}
	//查询余额信息
	public String selectBalance() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		int userId =  ((User) session.getAttribute("loginUser"))
				.getId();
			try {
				user = bankService.selectBankAccount(userId);
				setBalance(user.getBalance());
			} catch (SQLException e) {
				e.printStackTrace();
				return "Error";
			}
			return "selectBalance";
		
	}
	//存款
	public String saveMoney() throws SQLException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		int userNO =  ((User) session.getAttribute("loginUser"))
				.getId();
		bankService.saveMoney(userNO, getMoney());
		return "saveSuccess";
		
	}
	//查询交易信息
	public String tradeInfo() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		int userNO = ((User) session.getAttribute("loginUser"))
				.getId();
		String pwd = ((User) session.getAttribute("loginUser"))
				.getPwd();
		try {
			List<BankRecord> list = bankService.selectBankRecord(userNO, pwd);
			setList(list);
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error";
		}
		return "selectTradeInfo";
	}
	//查询用户
	public String selectUser() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		int userNO =  ((User) session.getAttribute("loginUser"))
				.getId();
		try {
			User user = bankService.selectBankAccount(userNO);
			setUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error";
		}
		return "selectUser";
	}
	//修改用户信息
	public String updateUser() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		int userNO =  ((User) session.getAttribute("loginUser"))
				.getId();
			User user = getUser();
			user.setId(userNO);
			try {
				if(userService.modifyUser(user)==1){
					setUser(user);
					session.setAttribute("loginUser",user);
					System.out.println("用户修改成功");
				}
			} catch (Exception e) {
				e.printStackTrace();
				return "Error";
			}
			return "updateSuccess";	
	}
	//注销用户信息
	public String deleteUser() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		int userNO = ((User) session.getAttribute("loginUser"))
				.getId();
		float balance = 0;
		try {
			User user = bankService.selectBankAccount(userNO);
			balance = user.getBalance();
		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}
		// 如果帐户余额大于零，不允许注销操作，提示出错
		if (balance > 0) {
			setBalance(balance);
			return "deleteError";
		} else {
			try {
				userService.deleteUser(getUser());
				session.setAttribute("user", bankService.selectBankAccount(userNO));
				return "deleteSuccess";
			} catch (Exception e) {
				e.printStackTrace();
				return "Error";
			}
		}
	}
	//转账
	public String transform() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		int fromId =  ((User) session.getAttribute("loginUser"))
				.getId();
		try {
			String result = bankService.transferAccount(fromId, getToId(), getMoney());
			float balance1 = bankService.selectBankAccount(fromId).getBalance();
			if(balance1 >= money){
				return "transformSuccess";
			}else{
				return "transformError";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}
	}
	
	//退出系统
	public String logout() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		// 销毁session
		session.invalidate();
		return "logout";
	}
}

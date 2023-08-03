package com.digit.javaTraining.mvcApp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpSession;

import com.digit.javaTraining.mvcApp.controller.LoginController;

public class BankApp {
	int bank_id;
	String bank_name;
	String ifsc_code;
	int accno;
	int pin;
	int cust_id;
	String cust_name;
	int balance;
	String email;
	long phone;
	int p1,p2,p3;
	int op;
	
	private PreparedStatement pstmt;
	private Object resp;
	private ResultSet res;

	public static Connection con;

	
	
	public int getop() {
		return op;
	}

	public void setop(int op) {
		this.op = op;
	}

	public int getP1() {
		return p1;
	}

	public void setP1(int p1) {
		this.p1 = p1;
	}

	public int getP2() {
		return p2;
	}

	public void setP2(int p2) {
		this.p2 = p2;
	}

	public int getP3() {
		return p3;
	}

	public void setP3(int p3) {
		this.p3 = p3;
	}

	public int getBank_id() {
		return bank_id;
	}

	public void setBank_id(int bank_id) {
		this.bank_id = bank_id;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getIfsc_code() {
		return ifsc_code;
	}

	public void setIfsc_code(String ifsc_code) {
		this.ifsc_code = ifsc_code;
	}

	public int getAccno() {
		return accno;
	}

	public void setAccno(int accno) {
		this.accno = accno;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public int getCust_id() {
		return cust_id;
	}

	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}

	public String getCust_name() {
		return cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public PreparedStatement getPstmt() {
		return pstmt;
	}

	public void setPstmt(PreparedStatement pstmt) {
		this.pstmt = pstmt;
	}

	public Object getResp() {
		return resp;
	}

	public void setResp(Object resp) {
		this.resp = resp;
	}

	public ResultSet getRes() {
		return res;
	}

	public void setRes(ResultSet res) {
		this.res = res;
	}

	public static Connection getCon() {
		return con;
	}

	public static void setCon(Connection con) {
		BankApp.con = con;
	}

	public BankApp() {
		String url = "jdbc:mysql://localhost:3306/project1";
		String user = "root";
		String pwd = "yash8209@";
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection(url, user, pwd);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean register() {
		try {

			pstmt = con.prepareStatement("insert into bankapp values(?,?,?,?,?,?,?,?,?,?)");

			pstmt.setInt(1, bank_id);//null

			pstmt.setString(2, bank_name);//null

			pstmt.setString(3, ifsc_code);

			pstmt.setInt(4, accno);//null

			pstmt.setInt(5, pin);

			pstmt.setInt(6, cust_id);

			pstmt.setString(7, cust_name);

			pstmt.setInt(8, balance);//null

			pstmt.setString(9, email);//null

			pstmt.setLong(10, phone);//null



			int x = pstmt.executeUpdate();

			if(x>0) {

				return true;

			}

			else {

				return false;

			}



		}

		catch (Exception e) {

			e.printStackTrace();

		}
		return false;
	}

	public boolean login() {
		try {


			pstmt = con.prepareStatement("select * from bankapp where cust_id=? and pin=?");

			pstmt.setInt(2, pin);

			pstmt.setInt(1, cust_id);

			res = pstmt.executeQuery();

			if(res.next()==true) {

				this.setAccno(res.getInt("Accno"));
				this.setCust_name(res.getString("customer_name"));
				this.setBank_name(res.getString("Bank_name"));
				this.setPin(res.getInt("pin"));
				

				return true;

			}

			else {

				return false;

			}



		}

		catch (Exception e) {

			e.printStackTrace();

		}
		return false;
	}
	public boolean checkBalance() {
		try {



			pstmt = con.prepareStatement("select Balance from bankapp where accno=?");

			pstmt.setInt(1,accno);

			res = pstmt.executeQuery();

			if(res.next()==true) {

				setBalance(res.getInt("balance"));

				return true;

			}

			else {


				return false;


			}



		}

		catch (Exception e) {

			e.printStackTrace();

		}
		return false;
	}
	public boolean changePassword() {
		int x=0;
		try {

			if(op==p1&&p2==p3) {

				pstmt = con.prepareStatement("update Bankapp set pin=? where accno=? and pin=?");

				pstmt.setInt(1, p2);
				pstmt.setInt(2, accno);
				pstmt.setInt(3, op);

				x = pstmt.executeUpdate();

			}

			if(x>0) {
				return true;
			}

			else {

				return false;

			}

		}
		catch (Exception e) {

			e.printStackTrace();

		}
		return false;
	}
	
	

}

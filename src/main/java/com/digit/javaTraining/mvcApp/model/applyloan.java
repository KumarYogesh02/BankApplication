package com.digit.javaTraining.mvcApp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class applyloan {
	int lid;
	String l_tpe;
	int tenure;
	int interest;
	String description;
	String s1;

	private PreparedStatement pstmt;
	private ResultSet res;
	public static Connection con;

	public String getS1() {
		return s1;
	}
	public void setS1(String s1) {
		this.s1 = s1;
	}

	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	public String getL_tpe() {
		return l_tpe;
	}
	public void setL_tpe(String l_tpe) {
		this.l_tpe = l_tpe;
	}
	public int getTenure() {
		return tenure;
	}
	public void setTenure(int tenure) {
		this.tenure = tenure;
	}
	public int getInterest() {
		return interest;
	}
	public void setInterest(int interest) {
		this.interest = interest;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public applyloan() {
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

	public boolean applyloan() {
		try {


			pstmt = con.prepareStatement("select * from applyloan where Lid=?");

			pstmt.setString(1, getS1());

			res = pstmt.executeQuery();


			if(res.next()==true) {
			this.lid=	res.getInt("Lid");
			this.l_tpe=	res.getString("l_tpe");
			this.tenure=	res.getInt("tenure");
			this.interest=	res.getInt("interest");
			this.description=	res.getString("description");
				return true;


			}
			else {
				return false;

			}



		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}

package com.digit.javaTraining.mvcApp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class transferstates {
	int custid;
	String bank_name;
	String sifsc;
	int saccno;
	int raccno;
	int amount;
	String rifsc;
	int transection_id;
	private Connection con;
	private PreparedStatement pstmt;
	int pin;
	int accno;
	private ResultSet res;

	public int getAccno() {
		return accno;
	}

	public void setAccno(int accno) {
		this.accno = accno;
	}

	public transferstates() {
		String url = "jdbc:mysql://localhost:3306/project1";
		String user = "root";
		String pwd = "yash8209@";
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection(url, user, pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public transferstates(int customerID, String senderBankName, String senderIFSC, int senderAccountNumber,

			String receiverIFSC, int receiverAccountNumber, int amountOfTransfer, int transactionID) {

		super();

		this.custid = customerID;

		this.bank_name = senderBankName;

		this.sifsc = senderIFSC;

		this.saccno = senderAccountNumber;

		this.raccno = receiverAccountNumber;

		this.rifsc = receiverIFSC;

		this.amount = amountOfTransfer;

		this.transection_id = transactionID;

	}

	public int getCustid() {
		return custid;
	}

	public void setCustid(int custid) {
		this.custid = custid;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getSifsc() {
		return sifsc;
	}

	public void setSifsc(String sifsc) {
		this.sifsc = sifsc;
	}

	public int getSaccno() {
		return saccno;
	}

	public void setSaccno(int saccno) {
		this.saccno = saccno;
	}

	public int getRaccno() {
		return raccno;
	}

	public void setRaccno(int raccno) {
		this.raccno = raccno;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getRifsc() {
		return rifsc;
	}

	public void setRifsc(String rifsc) {
		this.rifsc = rifsc;
	}

	public int getTransection_id() {
		return transection_id;
	}

	public void setTransection_id(int transection_id) {
		this.transection_id = transection_id;
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public PreparedStatement getPstmt() {
		return pstmt;
	}

	public void setPstmt(PreparedStatement pstmt) {
		this.pstmt = pstmt;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public boolean transfermoney() {
		try {

			pstmt = con.prepareStatement("select * from bankapp where cust_id=? and ifsc_code=? and accno=? and pin=?");
			pstmt.setInt(1, custid);
			pstmt.setString(2, sifsc);
			pstmt.setInt(3, saccno);
			pstmt.setInt(4, pin);

			ResultSet res = pstmt.executeQuery();

			if (res.next() == true) {
				pstmt = con.prepareStatement("select * from bankapp where ifsc_code=? and Accno=?");
				pstmt.setString(1, rifsc);
				pstmt.setInt(2, raccno);
				ResultSet res2 = pstmt.executeQuery();

				if (res2.next() == true) {
					pstmt = con.prepareStatement("select Balance from bankapp where Accno=?");
					pstmt.setInt(1, saccno);
					ResultSet res3 = pstmt.executeQuery();
					res3.next();
					int bal = res3.getInt(1);

					if (bal > amount) {
						pstmt = con.prepareStatement("update bankapp set balance = balance-? where accno=?");
						pstmt.setInt(1, amount);
						pstmt.setInt(2, saccno);
						int x1 = pstmt.executeUpdate();
						if (x1 > 0) {
							pstmt = con.prepareStatement("update bankapp set balance=balance +? where accno=?");
							pstmt.setInt(1, amount);
							pstmt.setInt(2, raccno);
							int x2 = pstmt.executeUpdate();
							if (x2 > 0) {
								Random r = new Random();
								int transid = (10000 + r.nextInt(900000));

								pstmt = con.prepareStatement("insert into transferstates values (?,?,?,?,?,?,?,?)");
								pstmt.setInt(1, custid);
								pstmt.setString(2, bank_name);
								pstmt.setString(3, sifsc);
								pstmt.setInt(4, saccno);
								pstmt.setInt(5, raccno);
								pstmt.setInt(6, amount);
								pstmt.setString(7, rifsc);
								pstmt.setDouble(8, transid);
								int x3 = pstmt.executeUpdate();
								if (x3 > 0) {
									return true;
								} else {
									return false;
								}
							} else {
								// insert data transaction data in transaction table
								System.out.println("fail due insert");
							}
						} else {
							// update balance in reciver side
							System.out.println("fail due update r");
						}
					} else {
						// update balance in user side
						System.out.println("fail due update user");
					}

				} else {
					// sufficient balance
					System.out.println("fail due insufficient");
				}

			} else {
				// reciver valid checl
				System.out.println("fail due reciever validation");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

//	public boolean transectionHistory() {
//	
//		 try {
//
//               pstmt = con.prepareStatement("select * from transferstates where sender_accno=? or receiver_accno=?");
//               pstmt.setInt(1, accno);
//               pstmt.setInt(2, accno);
//               
//               //ArrayList al= new ArrayList();
//               res = pstmt.executeQuery();
//               
//               
//               while(res.next()==true) {
//            	 
//            	   this.setAmount(res.getInt("amount"));
//            	   
//             	  	return true;
//               }
//               
//
// 	}
// 		catch(Exception e) {
// 			e.printStackTrace();
// 		}
//		return false;
//	}

	public ArrayList<transferstates> alltransaction(int accno) {

		ArrayList<transferstates> list = new ArrayList<transferstates>();

	

		try {

			pstmt=con.prepareStatement("SELECT * FROM transferstates WHERE sender_accno = ? OR receiver_accno = ?");

			pstmt.setInt(1, accno);

			pstmt.setInt(2, accno);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next() == true) {


				int customerID = rs.getInt("cust_id");

				String senderBankName = rs.getString("bank_name");

				String senderIFSC = rs.getString("ifsc_code");

				int senderAccountNumber = rs.getInt("sender_accno");

				String receiverIFSC = rs.getString("receiver_ifsc");

				int receiverAccountNumber = rs.getInt("receiver_accno");

				int amountOfTransfer = rs.getInt("amount");

				int transactionID = rs.getInt("transection_id");

				transferstates t = new transferstates(customerID, senderBankName, senderIFSC, senderAccountNumber,

						receiverIFSC, receiverAccountNumber, amountOfTransfer, transactionID);

				list.add(t);

			}

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return list;

	}

}

package com.digit.javaTraining.mvcApp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.digit.javaTraining.mvcApp.model.BankApp;
@WebServlet("/CheckBalance")
public class CheckBalanceController extends HttpServlet {
	
	private HttpSession session;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		session = req.getSession();
		
		int accno=(int) session.getAttribute("accno");
		
		BankApp bankApp= new BankApp();
		bankApp.setAccno(accno);
		boolean b= bankApp.checkBalance();
		if(b==true) {
			
			session.setAttribute("balance", bankApp.getBalance());
			
			resp.sendRedirect("/MVC_BankApp/Balance.jsp");
			
		}
		else {
			resp.sendRedirect("/MVC_BankApp/BalanceFail.jsp");
		}
	}
	

}

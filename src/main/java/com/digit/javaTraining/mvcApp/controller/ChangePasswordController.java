package com.digit.javaTraining.mvcApp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.digit.javaTraining.mvcApp.model.BankApp;
@WebServlet("/Changepassword")
public class ChangePasswordController extends HttpServlet {
	
	private HttpSession session;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		
		
		BankApp bankApp= new BankApp();
		bankApp.setAccno((int)session.getAttribute("accno"));
		bankApp.setop( (int)session.getAttribute("pwd"));
		bankApp.setP1(Integer.parseInt(req.getParameter("opwd")));
		bankApp.setP2(Integer.parseInt(req.getParameter("npwd")));
		bankApp.setP3(Integer.parseInt(req.getParameter("cpwd")));
		boolean b= bankApp.changePassword();
		if(b==true) {
			resp.sendRedirect("/BankApplication/PasswordChangeSuccess.html");
		}
		else {
			resp.sendRedirect("/BankApplication/PasswordChangeFail.html");
		}
	}

}

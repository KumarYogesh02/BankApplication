package com.digit.javaTraining.mvcApp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.digit.javaTraining.mvcApp.model.transferstates;
@WebServlet("/Transection.java")
public class TransectionController extends HttpServlet{
	private HttpSession session;
	private boolean b;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		transferstates ts= new transferstates();
		 ts.setAccno((int) session.getAttribute("accno"));
		
		b = ts.transectionHistory();
		if(b==true) {
			session.setAttribute("amount", ts.getAmount());
			resp.sendRedirect("/MVC_BankApp/TransectionDetail.jsp");
		}
		else {
			System.out.println("error");
		}
	}
}

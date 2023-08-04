package com.digit.javaTraining.mvcApp.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.digit.javaTraining.mvcApp.model.BankApp;
import com.digit.javaTraining.mvcApp.model.transferstates;

@WebServlet("/Transection.java")
public class TransectionController extends HttpServlet {
	private HttpSession session;
	private boolean b;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		BankApp bankApp = new BankApp();
		transferstates ts = new transferstates();
		int accno = (int) session.getAttribute("accno");
		//System.out.println(accno);
		ts.setAccno(accno);
		ArrayList<transferstates> list = ts.alltransaction(ts.getAccno());

		System.out.println(list.size());

		session.setAttribute("ALL_TRANSACTIONS", list);

		session.setAttribute("isListGenerated", "true");

		resp.sendRedirect("/BankApplication/TransectionDetail.jsp");
	}
}

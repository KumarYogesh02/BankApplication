package com.digit.javaTraining.mvcApp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.digit.javaTraining.mvcApp.model.applyloan;
@WebServlet("/Loan")
public class LoanController extends HttpServlet{
	private HttpSession session;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		session = req.getSession();
		applyloan al=new applyloan();
		al.setS1(req.getParameter("loan"));

		
		System.out.println(al.getLid());

		boolean b=al.applyloan();
		if(b==true) {
			session.setAttribute("id",al.getLid());
			session.setAttribute("type", al.getL_tpe());
			session.setAttribute("tenure", al.getTenure());
			session.setAttribute("interest", al.getInterest());
			session.setAttribute("desc", al.getDescription());
			resp.sendRedirect("/MVC_BankApp/LoanDetail.jsp");
		}
		else {
			resp.sendRedirect("/MVC_BankApp/LoanDetailFail.jsp");
		}

	}

}

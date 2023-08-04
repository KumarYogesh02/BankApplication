package com.digit.javaTraining.mvcApp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.digit.javaTraining.mvcApp.model.transferstates;
@WebServlet("/Transfer")
public class TransferMoneyController extends HttpServlet{
	
	private boolean b;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        transferstates ts= new transferstates();
        ts.setCustid(Integer.parseInt(req.getParameter("cust_id")));
        ts.setBank_name(req.getParameter("bank_name"));
        ts.setSifsc(req.getParameter("ifsc_code"));
        ts.setSaccno(Integer.parseInt(req.getParameter("sender_accno")));
        ts.setRifsc(req.getParameter("receiver_ifsc"));
        ts.setRaccno(Integer.parseInt(req.getParameter("receiver_accno")));
        ts.setAmount(Integer.parseInt(req.getParameter("amount")));
        ts.setPin(Integer.parseInt(req.getParameter("pin")));
        
        b = ts.transfermoney();
        if(b==true) {
        	resp.sendRedirect("/BankApplication/TransferSuccess.html");
        }
        
	}

}

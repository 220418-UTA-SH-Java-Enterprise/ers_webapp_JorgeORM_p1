package com.revature.web;

import com.revature.models.*;
import com.revature.services.*;
import com.revature.dao.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestHelper {
	
	private static ReimbursementService reimbursementv = new ReimbursementServiceImpl(new ReimbursementDAOImpl());

	public static void processError(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		PrintWriter out = resp.getWriter();
		out.print("Oops something went wrong");

	}
	
	public static void processManagerLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


	}
	
	public static void processManagerHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		out.print("Manager home login test");
		Reimbursement r = new Reimbursement();
		r.setAmount(240);
		r.setStatus("PENDING");
		r.setStatusCode(0);
		System.out.println("going to insert values into db");
		int targetId = 1337;
		targetId = reimbursementv.create(r);
		System.out.println(targetId);
		
	}
}

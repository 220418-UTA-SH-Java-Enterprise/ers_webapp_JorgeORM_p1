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
		PrintWriter out = resp.getWriter();
		out.print("You've Logged In");
	}
	
	public static void processManagerLogout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		out.print("You've Logged Out");
		
	}
	
	
	public static void processManagerHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		out.print("Manager home login test");
	}
	
	public static void processManagerReimbursementPending(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	
	}
	

	public static void processManagerReimbursementResolved(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
	}
	
	
	public static void processManagerReimbursementApprove(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
	}


	public static void processManagerReimbursementDeny(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
	}
	
}

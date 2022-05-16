package com.revature.web;

import com.revature.models.*;
import com.revature.services.*;
import com.revature.dao.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestHelper {
	
	private static ReimbursementService reimbursementv = new ReimbursementServiceImpl(new ReimbursementDAOImpl());
	private static ManagerService managerv = new ManagerServiceImpl(new ManagerDAOImpl());
	private static UserService userv = new UserServiceImpl(new UserDAOImpl());
	private static JwtService jwtService = new JwtService();
	
	
	public static UserJwtDTO authenticateUser(HttpServletRequest req) {

		Enumeration<String> headerNames = req.getHeaderNames();
		Map<String, String> map = new HashMap<String, String>();
		while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = req.getHeader(key);
            map.put(key, value);
        }
		String headerValue = req.getHeader("authorization");
		String jwt = headerValue.split(" ")[1];
		UserJwtDTO dto = new UserJwtDTO();
		return dto;
	}

	public static void processError(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		PrintWriter out = resp.getWriter();
		out.print("Oops something went wrong");

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

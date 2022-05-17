package com.revature.web;

import com.revature.models.*;
import com.revature.services.*;
import com.revature.dao.*;

import java.io.BufferedReader;
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
	
	public static void processRegistration(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BufferedReader reader = req.getReader();
		StringBuilder s = new StringBuilder();
		String line = reader.readLine();
		while(line != null) {
			s.append(line);
			line = reader.readLine();
		}
		String body = s.toString(); 
		String [] sepByAmp = body.split("&");
		
		List<String> values = new ArrayList<String>();
		
		for (String pair : sepByAmp) { 
			values.add(pair.substring(pair.indexOf("=") + 1)); 
		}
		
		String username = values.get(0);
		String password = values.get(1);
		String firstName = values.get(2);
		String lastName = values.get(3);
		
		UserRole role = new UserRole(1, "employee");
		User u = new User(username, password, firstName, lastName, role);
		
		int targetId = userv.register(u);
		
		PrintWriter out = resp.getWriter();
		if(targetId != 0) {
			resp.setStatus(200);
			out.print("User Registered");
		}
		else {
			resp.setStatus(204);
			out.print("User Registered");
		}
	}
}

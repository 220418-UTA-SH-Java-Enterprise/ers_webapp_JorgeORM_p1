package com.revature.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestHelper {

	public static void processError(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		req.getRequestDispatcher("error.html").forward(req, resp);

	}
	
	public static void processManagerLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//CODE GOES HERE

	}
	
	public static void processManagerHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//CODE GOES HERE
	}
}

package com.revature.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet{
	private static final long serialVersionUID = 8339100247721381693L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String URI = req.getRequestURI().replace("/project1/", "");

		switch (URI) {
		case "managerhome":
			RequestHelper.processManagerHome(req, resp);
			break;
		case "managerlogin":
			RequestHelper.processManagerLogin(req, resp);
			break;
		case "managerlogout":
			break;
			
		default:
			RequestHelper.processError(req, resp);
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String URI = req.getRequestURI().replace("/project1/", "");
		
		switch (URI) {
		default: 
			RequestHelper.processError(req, resp);
			break;
		}
		
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String URI = req.getRequestURI().replace("/project1/", "");
		switch (URI) {
			default:
				RequestHelper.processError(req, resp);
				break;
		}
	}
	
	@Override 
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String URI = req.getRequestURI().replace("/project1/", "");
		switch (URI){
			default:
				RequestHelper.processError(req, resp);
				break;
		}
	}
}


package com.revature.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.models.*;

public class FrontController extends HttpServlet{
	private static final long serialVersionUID = 8339100247721381693L;
	Manager currentManager = new Manager();
	boolean managerLoggedIn = false;
	boolean employeeLoggedIn = false;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String URI = req.getRequestURI().replace("/project1/", "");

		switch (URI) {
		case "managerHome":
			RequestHelper.processManagerHome(req, resp);
			break;

		
		case "managerReimbursementSearch":
			break;
			
		case "managerReimbursementPending":
			//RequestHelper.processManagerReimbursementPending(req, resp);
			break;
		
		case "managerReimbursementResolved":
			//RequestHelper.processManagerReimbursementResolved(req, resp);
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
		case "register" : 
			RequestHelper.processRegistration(req, resp);
			break;
		default: 

			RequestHelper.processError(req, resp);
			break;
		}
		
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String URI = req.getRequestURI().replace("/project1/", "");
		switch (URI) {
		
		case "managerReimbursementApprove":
			break;
		case "managerReimbursementDeny":
			break;	
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


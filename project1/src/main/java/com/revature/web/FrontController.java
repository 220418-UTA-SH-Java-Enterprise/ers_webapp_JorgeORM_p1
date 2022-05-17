package com.revature.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class FrontController extends HttpServlet{
	private static final long serialVersionUID = 8339100247721381693L;
	
	private static Logger log = Logger.getLogger(FrontController.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String URI = req.getRequestURI().replace("/project1", "");
		log.info("URI: " + URI);
		
		switch (URI) {
		case "employees":
			log.info("employee wants a list of employees from API...");
			RequestHelper.processAllEmployees(req, resp);
		case "employee":
			log.info("employee wants to search an employee from API based on first name or id number. URI " + URI);
			RequestHelper.processAllEmployeeBySearchParam(req, resp);	
			break;
			
		case "reimbursements":
		
		default:
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String URI = req.getRequestURI().replace("/project1", "");
		log.info("URI: " + URI);
		
		switch (URI) {
		/*
		case "register" : 
			RequestHelper.processRegistration(req, resp);
			break;
			*/
		case "register":
			log.info("employee wants to register...");
			RequestHelper.processAllRegistration(req, resp);
			break;
		default:
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

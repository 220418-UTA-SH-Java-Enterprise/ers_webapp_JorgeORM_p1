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
		case "managers":
			System.out.println("manager");
			break;
		default:
			System.out.println("ERROR");
			break;
		}
	}
}

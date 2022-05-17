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
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
	import com.fasterxml.jackson.core.JsonProcessingException;
	import com.fasterxml.jackson.databind.ObjectMapper;
	
	public class RequestHelper {
		
	private static EmployeeService employeev = new EmployeeServiceImpl(new EmployeeDAOImpl());
	private static Logger log = Logger.getLogger(RequestHelper.class);
	private static ObjectMapper om = new ObjectMapper();
	private static ReimbursementService reimbursementv = new ReimbursementServiceImpl(new ReimbursementDAOImpl());
	private static ManagerService managerv = new ManagerServiceImpl(new ManagerDAOImpl());
	private static UserService userv = new UserServiceImpl(new UserDAOImpl());
	private static JwtService jwtService = new JwtService();
	
	
	public static UserJwtDTO authenticateUser(HttpServletRequest req) {
		log.info("Request data:");
		Enumeration<String> headerNames = req.getHeaderNames();
		Map<String, String> map = new HashMap<String, String>();
		while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = req.getHeader(key);
            map.put(key, value);
        }
		log.info(map);
		
		String headerValue = req.getHeader("authorization");
		String jwt = headerValue.split(" ")[1]; 
		
		UserJwtDTO dto = new UserJwtDTO();
		try {
			dto = jwtService.parseJwt(jwt);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	public static void processLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		log.info("inside of request helper...processLogin");
		BufferedReader reader = req.getReader();
		StringBuilder s = new StringBuilder();
		String line = reader.readLine();
		
		while(line!= null) {
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
		
		log.info("User attempted to login with username" + username);
		User u = userv.login(username, password);
		if (u != null) {
			String jwt = jwtService.createJwt(u);
			resp.addHeader("X-Auth-Token", "Bearer " + jwt); 
			
			HttpSession session = req.getSession();
			session.setAttribute("user", u);
			

			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			

			out.println(om.writeValueAsString(u));
			
			
			log.info("The user " + username + " has logged in.");
		} else {
			PrintWriter out = resp.getWriter();
			out.println("wrong credentials");
			resp.setStatus(204); 
		}
	}
	
	public static void processAllEmployees(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		UserJwtDTO dto = authenticateUser(req);
		if(dto != null && dto.getRole().getRoleName().equals("manager")) {
			log.info("in request helper. getting users...");
			
		    resp.setContentType("application/json");
		    resp.setStatus(200);
		    List<User> allEmployees = userv.findAllUsers();
			
		    String json = om.writeValueAsString(allEmployees);
			
		    PrintWriter out = resp.getWriter();
		    
		    out.println(json);
			
		    log.info("leaving requesthelper");
		}
		else {
			log.info("User is unauthorized to perform this operation.");
			resp.setStatus(401);
			PrintWriter out = resp.getWriter();
		    out.print("You need to be logged in as a manager to perform this action");
		}
		
		log.info("leaving requesthelper");
		
	}
	
	public static void processAllEmployeeBySearchParam(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	  log.info("in request helper. searching by param");
	  BufferedReader reader = req.getReader();
	  StringBuilder s = new StringBuilder();
	  String line = reader.readLine();
	  while(line != null) {
	    s.append(line);
	    line = reader.readLine();
	  }
	
	
	  String body = s.toString();
	
	  log.info("employee searching with this info: " + body);
	
	  String[] sepByAmp = body.split("&");
	  List<String> values = new ArrayList<String>();
	
	  for(String pair : sepByAmp) {
	    values.add(pair.substring(pair.indexOf("=") + 1));
	  }
	
	  if(body.startsWith("id")) {
	    log.info("in request helper. getting users by id...");
	
	    resp.setContentType("application/json");
	
	    int id = Integer.parseInt(values.get(0));
	    User user = userv.findUserById(id);
	
	    String json = om.writeValueAsString(user);
	    PrintWriter out = resp.getWriter();
	    out.println(json);
	
	    log.info("leaving requesthelper");
	  }
	 else {
	  log.info("in request helper. getting users by first name...");
	
	  resp.setContentType("application/json");
	
	  String firstname = values.get(0);
	  Employee employee = employeev.findEmployeeByFirstName(firstname);
	
	  String json = om.writeValueAsString(employee);
	
	  PrintWriter out = resp.getWriter();
	
	  out.println(json);
	
	  log.info("leaving requesthelper");
	 }
	}
	public static void processAllRegistration(HttpServletRequest request, HttpServletResponse response) throws IOException {
	  // TODO Auto-generated method stub
	  log.info("inside of request helper...processRegistration...");
	      BufferedReader reader = request.getReader();
	      StringBuilder s = new StringBuilder();
	      // we are just transferring our Reader data to our StringBuilder, line by line
	      String line = reader.readLine();
	      while (line != null) {
	          s.append(line);
	          line = reader.readLine();
	      }
	      String body = s.toString();
	      String [] sepByAmp = body.split("&"); // separate username=bob&password=pass -> [username=bob, password=pass]
	      List<String> values = new ArrayList<String>();
	      for (String pair : sepByAmp) { // each element in array looks like this
	          values.add(pair.substring(pair.indexOf("=") + 1)); // trim each String element in the array to just value -> [bob, pass]
	      }
	      log.info("Employee attempted to register with information:\n " + body);
	      // capture the actual username and password values
	      String username = values.get(0); // bob
	      String password = values.get(1); // pass
	      String firstname = values.get(2);
	      String lastname = values.get(3);
		  UserRole role = new UserRole(1, "employee");
		  
		  
		  User u = new User(username, password, firstname, lastname, role);
		  int targetId = userv.register(u);
	      if (targetId != 0) {
	          PrintWriter pw = response.getWriter();
	          u.setId(targetId);
	          log.info("New employee: " + u);
	          String json = om.writeValueAsString(u);
	          pw.println(json);
	          System.out.println("JSON:\n" + json);
	          response.setContentType("application/json");
	          response.setStatus(200); // SUCCESSFUL!
	          log.info("Employee has successfully been created.");
	      } else {
	          response.setContentType("application/json");
	          response.setStatus(204); // this means that the connection was successful but no user created!
	      }
	      log.info("leaving request helper now...");
	}
	
	public static void processReimbursementCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		UserJwtDTO dto = authenticateUser(req);
		if(dto != null) {
			log.info("inside of request helper...processReimbursementCreate");
			BufferedReader reader = req.getReader();
			StringBuilder s = new StringBuilder(); 
			String line = reader.readLine();
			while (line != null) {
				s.append(line);
				line = reader.readLine();
			}
			String body = s.toString(); 
			String [] sepByAmp = body.split("&"); 
			List<String> values = new ArrayList<String>();
			for (String pair : sepByAmp) { 
				values.add(pair.substring(pair.indexOf("=") + 1));
			}
			log.info("User attempted to create a reimbursement:\n " + body);
			float amount = Float.parseFloat(values.get(0));
			String manager = "";
			int managerId = 0;
			String status = "PENDING";
			int statusCode = 1;
			String employee = dto.getUsername();
			int employeeId = dto.getId();
			
			Reimbursement r = new Reimbursement(amount, status, statusCode, manager, managerId, employee, employeeId);
			int targetId = reimbursementv.create(r);
			if (targetId != 0) {
		          PrintWriter pw = resp.getWriter();
		          r.setId(targetId);
		          log.info("New employee: " + r);
		          String json = om.writeValueAsString(r);
		          pw.println(json);
		          System.out.println("JSON:\n" + json);
		          resp.setContentType("application/json");
		          resp.setStatus(200); // SUCCESSFUL!
		          log.info("Employee has successfully been created.");
		      } else {
		          resp.setContentType("application/json");
		          resp.setStatus(204); // this means that the connection was successful but no user created!
		      }
		      log.info("leaving request helper now...");
			
		}
		else {
			log.info("User is not logged in");
			resp.setStatus(400);
			PrintWriter out = resp.getWriter();
		    out.print("You need to be logged in to file a reimbursement!");
		}
	}
	
	public static void processReimbursementsAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		UserJwtDTO dto = authenticateUser(req);
		if(dto != null && dto.getRole().getRoleName().equals("manager")) {
			log.info("in request helper. getting all reimbursements...");
			
		    resp.setContentType("application/json");
		    resp.setStatus(200);
		    List<Reimbursement> pendingReimbursements = reimbursementv.findAllReimbursements();
			
		    String json = om.writeValueAsString(pendingReimbursements);
			
		    PrintWriter out = resp.getWriter();
		    
		    out.println(json);
			
		    log.info("leaving requesthelper");
		}
		else {
			log.info("User is unauthorized to perform this operation.");
			resp.setStatus(401);
			PrintWriter out = resp.getWriter();
		    out.print("You need to be logged in as a manager to perform this action");
		}
		
		log.info("leaving requesthelper");
	}
	

	public static void processReimbursementByStatus(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		UserJwtDTO dto = authenticateUser(req);
		if(dto != null && dto.getRole().getRoleName().equals("manager")) {
			log.info("in request helper. getting reimbursements by status...");
			BufferedReader reader = req.getReader();
			StringBuilder s = new StringBuilder(); 
			String line = reader.readLine();
			while (line != null) {
				s.append(line);
				line = reader.readLine();
			}
			String body = s.toString(); 
			String [] sepByAmp = body.split("&"); 
			List<String> values = new ArrayList<String>();
			for (String pair : sepByAmp) { 
				values.add(pair.substring(pair.indexOf("=") + 1));
			}
			String status = values.get(0);
		    resp.setContentType("application/json");
		    resp.setStatus(200);
		    List<Reimbursement> pendingReimbursements = reimbursementv.findReimbursementByStatus(status);
			
		    String json = om.writeValueAsString(pendingReimbursements);
			
		    PrintWriter out = resp.getWriter();
		    
		    out.println(json);
			
		    log.info("leaving requesthelper");
		}
		else {
			log.info("User is unauthorized to perform this operation.");
			resp.setStatus(401);
			PrintWriter out = resp.getWriter();
		    out.print("You need to be logged in as a manager to perform this action");
		}
		
		log.info("leaving requesthelper");
	}
	
	public static void processReimbursementByEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserJwtDTO dto = authenticateUser(req);
		if(dto != null) {
			String username = dto.getUsername();
			resp.setContentType("application/json");
		    resp.setStatus(200);
		    List<Reimbursement> pendingReimbursements = reimbursementv.findReimbursementByManager(username);
		    String json = om.writeValueAsString(pendingReimbursements);
			
		    PrintWriter out = resp.getWriter();
		    
		    out.println(json);
			
		    log.info("leaving requesthelper");
		    
		}
		else {
			log.info("User is unauthorized to perform this operation.");
			resp.setStatus(401);
			PrintWriter out = resp.getWriter();
		    out.print("You need to be logged in to view your reimbursements");
		}
		
		log.info("leaving requesthelper");
			
	}
	
	public static void processApprove(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserJwtDTO dto = authenticateUser(req);
		if(dto != null && dto.getRole().getRoleName().equals("manager")) {
			log.info("in request helper. approving a reimbursement...");
			BufferedReader reader = req.getReader();
			StringBuilder s = new StringBuilder(); 
			String line = reader.readLine();
			while (line != null) {
				s.append(line);
				line = reader.readLine();
			}
			String body = s.toString(); 
			String [] sepByAmp = body.split("&"); 
			List<String> values = new ArrayList<String>();
			for (String pair : sepByAmp) { 
				values.add(pair.substring(pair.indexOf("=") + 1));
			}
			int reimbursementId = Integer.parseInt(values.get(0));
			Reimbursement reimbursement = reimbursementv.findReimbursementById(reimbursementId);
			Reimbursement tempReimbursement = new Reimbursement();
			tempReimbursement.setId(reimbursementId);
			tempReimbursement.setAmount(reimbursement.getAmount());
			tempReimbursement.setEmployee(reimbursement.getEmployee());
			tempReimbursement.setEmployeeId(reimbursement.getEmployeeId());
			tempReimbursement.setManager(dto.getUsername());
			tempReimbursement.setManagerId(dto.getId());
			tempReimbursement.setStatus("APPROVED");
			tempReimbursement.setStatusCode(2);
			boolean isUpdated = reimbursementv.editReimbursement(tempReimbursement);
			
			if(isUpdated) {
				PrintWriter pw = resp.getWriter();
				log.info("Edit successful! New reimbursement info: " + tempReimbursement);
				String json = om.writeValueAsString(tempReimbursement);
				pw.println(json);
				System.out.println("JSON:\n" + json);
				resp.setContentType("application/json");
				resp.setStatus(200); // SUCCESSFUL!
				log.info("Reimbursement has successfully been approved.");
				
			} else {
				resp.setContentType("application/json");
				resp.setStatus(400); // this means that the connection was successful but no user was updated!
			}
			
		    log.info("leaving requesthelper");
		}
		else {
			log.info("User is unauthorized to perform this operation.");
			resp.setStatus(401);
			PrintWriter out = resp.getWriter();
		    out.print("You need to be logged in as a manager to perform this action");
		}
		
		log.info("leaving requesthelper");
	}
	
	public static void processDeny(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserJwtDTO dto = authenticateUser(req);
		if(dto != null && dto.getRole().getRoleName().equals("manager")) {
			log.info("in request helper. denying a reimbursement...");
			BufferedReader reader = req.getReader();
			StringBuilder s = new StringBuilder(); 
			String line = reader.readLine();
			while (line != null) {
				s.append(line);
				line = reader.readLine();
			}
			String body = s.toString(); 
			String [] sepByAmp = body.split("&"); 
			List<String> values = new ArrayList<String>();
			for (String pair : sepByAmp) { 
				values.add(pair.substring(pair.indexOf("=") + 1));
			}
			int reimbursementId = Integer.parseInt(values.get(0));
			Reimbursement reimbursement = reimbursementv.findReimbursementById(reimbursementId);
			Reimbursement tempReimbursement = new Reimbursement();
			tempReimbursement.setId(reimbursementId);
			tempReimbursement.setAmount(reimbursement.getAmount());
			tempReimbursement.setEmployee(reimbursement.getEmployee());
			tempReimbursement.setEmployeeId(reimbursement.getEmployeeId());
			tempReimbursement.setManager(dto.getUsername());
			tempReimbursement.setManagerId(dto.getId());
			tempReimbursement.setStatus("DENIED");
			tempReimbursement.setStatusCode(3);
			boolean isUpdated = reimbursementv.editReimbursement(tempReimbursement);
			
			if(isUpdated) {
				PrintWriter pw = resp.getWriter();
				log.info("Edit successful! New reimbursement info: " + tempReimbursement);
				String json = om.writeValueAsString(tempReimbursement);
				pw.println(json);
				System.out.println("JSON:\n" + json);
				resp.setContentType("application/json");
				resp.setStatus(200); // SUCCESSFUL!
				log.info("Reimbursement has successfully been denied.");
				
			} else {
				resp.setContentType("application/json");
				resp.setStatus(400); // this means that the connection was successful but no user was updated!
			}
			
		    log.info("leaving requesthelper");
		}
		else {
			log.info("User is unauthorized to perform this operation.");
			resp.setStatus(401);
			PrintWriter out = resp.getWriter();
		    out.print("You need to be logged in as a manager to perform this action");
		}
		
		log.info("leaving requesthelper");
	}
	
	
	public static void processError(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
	    PrintWriter out = resp.getWriter();
	    out.print("Oops something went wrong!");
	}
    




}

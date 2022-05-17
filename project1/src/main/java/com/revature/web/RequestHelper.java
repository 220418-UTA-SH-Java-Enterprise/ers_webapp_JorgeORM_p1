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

public static void processAllEmployees(HttpServletRequest req, HttpServletResponse resp) throws IOException {

  log.info("in request helper. getting users...");

  resp.setContentType("application/json");

  List<User> allEmployees = userv.findAllUsers();

  String json = om.writeValueAsString(allEmployees);

  PrintWriter out = resp.getWriter();

  out.println(json);

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

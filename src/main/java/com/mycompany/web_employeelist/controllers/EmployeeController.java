package com.mycompany.web_employeelist.controllers;

import com.mycompany.web_employeelist.data.Employee;
import com.mycompany.web_employeelist.data.Meeting;
import com.mycompany.web_employeelist.server.IServerProxy;
import com.mycompany.web_employeelist.server.ServerProxyFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "EmployeeController", urlPatterns = {"/EmployeeController"})
public class EmployeeController extends HttpServlet {

    String requestType = "unknown";
    List<Employee> employees;
    
    IServerProxy serverProxy = ServerProxyFactory.getInstance();

    public EmployeeController() {
        employees = serverProxy.getEmployees();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        request.setAttribute("employees", employees);
        
        String selectedEmployee = new BufferedReader(new InputStreamReader(request.getInputStream())).readLine();
        request.setAttribute("selectedEmployee", selectedEmployee);
       
        request.getRequestDispatcher("index_json.jsp").forward(request, response);
    }

 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        requestType = "GET";
        processRequest(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        requestType = "POST";
        
        processRequest(request, response);
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

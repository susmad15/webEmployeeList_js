package com.mycompany.web_employeelist.controllers;

import com.mycompany.web_employeelist.data.Employee;
import com.mycompany.web_employeelist.data.Meeting;
import com.mycompany.web_employeelist.server.IServerProxy;
import com.mycompany.web_employeelist.server.ServerProxyFactory;
import java.io.IOException;
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
    List<Meeting> meetings;
    List<Employee> participants;
    
    IServerProxy serverProxy = ServerProxyFactory.getInstance();

    public EmployeeController() {
        employees = serverProxy.getEmployees();
        meetings = serverProxy.getMeetings();
    }
    
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        request.setAttribute("employees", employees);
        //request.setAttribute("meetings", meetings);
        //request.setAttribute("participants", participants);
       
        
        //request.getRequestDispatcher("index.jsp").forward(request, response);
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
        request.setAttribute("something", 5);
        
        String selectedEmployee = request.getParameter("employeeSelect");
        String selectedMeeting = request.getParameter("meetingSelect");
        
        request.setAttribute("selectedEmployee", selectedEmployee);
        request.setAttribute("selectedMeeting", selectedMeeting);
        System.out.println(selectedEmployee);
        System.out.println(selectedMeeting);
        
        meetings = serverProxy.getMeetingsForEmployee(selectedEmployee);
        
        System.out.println("###############################################################################");
        System.out.println(selectedMeeting);
        
        if(selectedMeeting != null){
            String [] tmp = selectedMeeting.split(",");
            int meetingID = Integer.parseInt(tmp [0]);
            participants = serverProxy.getParticipantsForMeeting(meetingID);
        }
        
        if(request.getParameter("btAddMeeting") != null){
            serverProxy.addMeeting(null);
        }
        
        processRequest(request, response);
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

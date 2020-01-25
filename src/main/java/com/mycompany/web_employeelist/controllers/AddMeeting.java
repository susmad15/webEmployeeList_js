package com.mycompany.web_employeelist.controllers;

import com.google.gson.Gson;
import com.mycompany.web_employeelist.data.Meeting;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mycompany.web_employeelist.server.ServerProxyFactory;
import com.mycompany.web_employeelist.server.IServerProxy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

@WebServlet(name = "AddMeeting", urlPatterns = {"/AddMeeting"})
public class AddMeeting extends HttpServlet {

    IServerProxy proxy = ServerProxyFactory.getInstance();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        System.out.println("AddMeeting Servlet: Start");

        String selectedEmployee = new BufferedReader(new InputStreamReader(request.getInputStream())).readLine();
        
        request.setAttribute("selectedEmployee", selectedEmployee);
        
        Long id = proxy.getEmployeeID(selectedEmployee);
        proxy.addMeeting(id);
        
        List<Meeting> fillteredMeetings = proxy.getMeetingsForEmployee(selectedEmployee);
        
        Gson gson = new Gson();
        
        String jsonAnswer = gson.toJson(fillteredMeetings);
        
        log("Hi jultschgi");
        
        System.out.println("AddMeeting.processRequest, filteredMeetings.size : " + fillteredMeetings.size());
        
        System.out.println("AddMeeting.processRequest, jsonAnswer: "+jsonAnswer);
        
        OutputStreamWriter out = new OutputStreamWriter(response.getOutputStream());
        
        out.write(jsonAnswer);
        
        out.flush();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

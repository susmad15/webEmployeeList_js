package com.mycompany.web_employeelist.controllers;

import com.google.gson.Gson;
import com.mycompany.web_employeelist.data.Employee;
import com.mycompany.web_employeelist.server.IServerProxy;
import com.mycompany.web_employeelist.server.ServerProxyFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "RetrieveParticipantsForMeeting", urlPatterns = {"/RetrieveParticipantsForMeeting"})
public class RetrieveParticipantsForMeeting extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        String selectedMeeting = new BufferedReader(new InputStreamReader(request.getInputStream())).readLine();
        
        System.out.println("RetrieveParticipants.processRequest: " + selectedMeeting);
        
        IServerProxy proxy = ServerProxyFactory.getInstance();
        
        List<Employee> participants = proxy.getParticipantsForMeeting(Integer.parseInt(selectedMeeting));
        
        Gson gson = new Gson();
        
        String jsonAnswer = gson.toJson(participants);
        
        System.out.println("RetrievedParticipants.processRequest, participants.size : " + participants.size());
        
        System.out.println("RetievedParticipants.processRequest, jsonAnswer: "+jsonAnswer);
        
        response.setContentType("text/html;charset=UTF-8");
        
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
    }

}


package com.mycompany.web_employeelist.controllers;

import com.google.gson.Gson;
import com.mycompany.web_employeelist.data.Meeting;
import com.mycompany.web_employeelist.server.IServerProxy;
import com.mycompany.web_employeelist.server.ServerProxyFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "RetrieveMeetingsForEmployee", urlPatterns = {"/RetrieveMeetingsForEmployee"})
public class RetrieveMeetingsForEmployee extends HttpServlet {

    private String requestType = "";

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        System.out.println("RetrieveMeetingsForEmployee: requestType= " + requestType);
        
        String selectedEmployee = new BufferedReader(new InputStreamReader(request.getInputStream())).readLine();
        
        System.out.println("RetrieveMeetings.processRequest: " + selectedEmployee);
        
        IServerProxy proxy = ServerProxyFactory.getInstance();
        
        List<Meeting> meetings = proxy.getMeetings();
        
        List fillteredMeetings = meetings
                .stream()
                .filter(m -> m.getCreatedBy().getName().equals(selectedEmployee))
                .collect(Collectors.toList());
        
        Gson gson = new Gson();
        
        String jsonAnswer = gson.toJson(fillteredMeetings);
        
        System.out.println("RetrievedMeetings.processRequest, filteredMeetings.size : " + fillteredMeetings.size());
        
        System.out.println("RetievedMeetings.processRequest, jsonAnswer: "+jsonAnswer);
        
        response.setContentType("text/html;charset=UTF-8");
        
        OutputStreamWriter out = new OutputStreamWriter(response.getOutputStream());
        
        out.write(jsonAnswer);
        
        out.flush();
        
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


package com.mycompany.web_employeelist.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mycompany.web_employeelist.server.ServerProxyFactory;
import com.mycompany.web_employeelist.server.IServerProxy;

@WebServlet(name = "AddMeeting", urlPatterns = {"/AddMeeting"})
public class AddMeeting extends HttpServlet {

    IServerProxy proxy = ServerProxyFactory.getInstance();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        request.getRequestDispatcher("EmployeeController").forward(request, response);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String selectedEmployee = request.getParameter("selectedEmployee");
        System.out.println("selected: " + selectedEmployee);
        Long id = proxy.getEmployeeID(selectedEmployee);
        System.out.println("selected ID:" + id);
        proxy.addMeeting(id);
        
        processRequest(request, response);
        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

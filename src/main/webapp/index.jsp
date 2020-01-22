
<%@page import="com.mycompany.web_employeelist.data.Meeting"%>
<%@page import="com.mycompany.web_employeelist.data.Employee"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Employee List</h1>
        
        <div>These are all employees:
        <%for (Employee employee : (ArrayList<Employee>) request.getAttribute("employees")) {

                    out.println(employee.getName() + ", ");

                    }
                %>
        </div>
        
        <h2>Select Employee</h2>
        <form action="EmployeeController" method="post">
            <select name="employeeSelect" size="10" onchange="onSelectEmployee(this);">
            <%  
                //jstl is used here to replace a normal java for-loop
            %>
                <c:forEach var="oneEmployee" items="${employees}">
                    <option ${oneEmployee.name == selectedEmployee ? 'selected' : ''}>${oneEmployee.name}</option> <!--if the employee was selected before, he is marked-->
                    
                </c:forEach>
     
            </select>
                
                <select name="meetingSelect" size="10" onchange="submit();">
                 <c:forEach var="oneMeeting" items="${meetings}">
                    <option ${oneMeeting.toString() == selectedMeeting ? 'selected' : ''}>${oneMeeting.toString()}</option>
                    <!--in jstl, other comperators are used, so a string can be compared using == (instead .equals) -->
                </c:forEach>
                </select>
                
                <select name="participantSelect" size="10" onchange="">
                 <c:forEach var="oneParticipant" items="${participants}">
                    <option>${oneParticipant.toString()}</option>
                    
                </c:forEach>
                </select>
                
        </form>
                
        <form action="addMeeting.jsp" method="POST">
                    
            <input type="submit" id="addMeeting" value="Add Meeting" />
            <input type="hidden" value="${selectedEmployee}" name="selectedEmployee"/>
        </form>
                
        <script type="text/javascript" src="onSelect.js"></script>
        
        <div>Selected: ${selectedEmployee}</div>
       <!-- <%= request.getAttribute("selectedEmployee") %>  or use this to embed it directly--->
            
    </body>
</html>

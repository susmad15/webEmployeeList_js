<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Add Meeting</h1>
        <%
        String selectedEmployee = request.getParameter("selectedEmployee");
        request.setAttribute("selectedEmployee", selectedEmployee);
        System.out.println(selectedEmployee + "!!!!");
        %>
        
        Selected Employee: ${selectedEmployee}
        
        <form action="AddMeeting" method="POST">
            <input type="text" name="text"/>
            <input type="submit" value="AddMeeting" />
            <input type="hidden" value="${selectedEmployee}" name="selectedEmployee"/>
            
        </form>
    </body>
</html>

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

        <script type="text/javascript" src="selectCreator.js">

        </script>

        <script>
            const onSelectEmployee = (employee) => {
                var sc = new selectCreator(document.getElementById('meetingSelect'), 'RetrieveMeetingsForEmployee', employee.value);
                sc.create('egal');
            }

            const onSelectMeeting = (meeting) => {
                var sc = new selectCreator(document.getElementById('participantSelect'), 'RetrieveParticipantsForMeeting', meeting.value);
                sc.create('egal');
            }
        </script>
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
        <form>
            <select name="employeeSelect" size="10" onchange="onSelectEmployee(this);">
                <c:forEach var="oneEmployee" items="${employees}">
                    <option ${oneEmployee.name == selectedEmployee ? 'selected' : ''}>${oneEmployee.name}</option> <!--if the employee was selected before, he is marked-->

                </c:forEach>

            </select>

            <select name="meetingSelect" id="meetingSelect" size="10" onchange="onSelectMeeting(this);">

            </select>

            <select name="participantSelect" id="participantSelect" size="10" onchange="">

            </select>

        </form>

        <form action="addMeeting.jsp" method="POST">

            <input type="submit" id="addMeeting" value="Add Meeting" />
            <input type="hidden" value="${selectedEmployee}" name="selectedEmployee"/>
        </form>

        <div id="seljava">Selected: ${selectedEmployee}</div>
       <!-- <%= request.getAttribute("selectedEmployee")%>  or use this to embed it directly--->

    </body>
</html>
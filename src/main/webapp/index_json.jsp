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

        <script type="text/javascript" src="selectCreator.js"></script>

        <script>
            
            const onSelectEmployee = (employee) => {
                
                console.log ("onSelectEmployee: start");
                
                var sc = new selectCreator(document.getElementById('meetingSelect'), 'RetrieveMeetingsForEmployee', employee.value);
                sc.create();
            }

            const onSelectMeeting = (meeting) => {
                var sc = new selectCreator(document.getElementById('participantSelect'), 'RetrieveParticipantsForMeeting', meeting.value);
                sc.create();
            }

            const onShowMeetingDialog = () => {
                console.log("Start vom Show Meeting Dialog")
                document.getElementById("createMeetingLabel").style.display ="block";
                document.getElementById("createMeetingBox").style.display = "block";
                document.getElementById("createMeetingConfirm").style.display = "block";
                
                
            }
            
            const onConfirm = (employee) => {
                console.log("onConfirm: ")
                var title = document.getElementById("createMeetingBox");
                console.log("Title: "+title);
                var sc = new selectCreator(document.getElementById("meetingSelect"), "AddMeeting", employee.value);
                sc.create();
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
            <select name="employeeSelect" id ="employeeSelect" size="10" onchange="onSelectEmployee(this);">
                <c:forEach var="oneEmployee" items="${employees}">
                    <option ${oneEmployee.name == selectedEmployee ? 'selected' : ''}>${oneEmployee.name}</option> <!--if the employee was selected before, he is marked-->

                </c:forEach>

            </select>

            <select name="meetingSelect" id="meetingSelect" size="10" onchange="onSelectMeeting(this);">

            </select>

            <select name="participantSelect" id="participantSelect" size="10" onchange="">

            </select>

        </form>

        <form>

            <input type="button" id="addMeeting" class="addMeeting" value="Add Meeting" onClick="onShowMeetingDialog();" />
            <input type="hidden" value="${selectedEmployee}" name="selectedEmployee"/>
            <br>
            <br>
            <label id="createMeetingLabel" style="display: none">Please enter a title for your meeting: </label>
            <input type="text" id="createMeetingBox" style="display: none" />
            <br>
            <input type="button" id="createMeetingConfirm" value= "Confirm" style="display: none" onClick="onConfirm(this);"/>
        </form>

       

    </body>
</html>
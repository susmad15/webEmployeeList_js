
package com.mycompany.web_employeelist.server;



import com.mycompany.web_employeelist.pojo.Employee;
import com.mycompany.web_employeelist.pojo.Meeting;
import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class MockServerProxy implements IServerProxy{
    //this implements the Interface IServerProxy
    
    private List employees;
    private List meetings;
    
    
    @Override
    public List getEmployees(){
        employees = new ArrayList<Employee>();
        
        employees.add(new Employee("Employee1 from MockServerProxy"));
        employees.add(new Employee("Employee2 from MockServerProxy"));
        employees.add(new Employee("Employee3 from MockServerProxy"));
        employees.add(new Employee("Employee4 from MockServerProxy"));
        employees.add(new Employee("Employee5 from MockServerProxy"));
        employees.add(new Employee("Employee6 from MockServerProxy"));
    
        
        return employees;
    }
    
    
    @Override
    public List getMeetings(){
        meetings = new ArrayList<Meeting>();
        
        for(Employee employee : (List<Employee>) getEmployees()){
            Meeting meeting = new Meeting();
            //meeting.setId(1L);
            meeting.setCreatedBy(employee);
            meeting.setStartsAt(
                ZonedDateTime.of(
                        2017, 6, 25,
                        11, 30, 0, 0,
                        ZoneId.systemDefault()
                )
                );
                meeting.setDuration(
                Duration.of(45, ChronoUnit.MINUTES));
                
                meetings.add(meeting);
                meetings.add(meeting);
                meetings.add(meeting);
        }
        
        return meetings;
    }
    
    
    @Override
    public List getMeetingsForEmployee(String employeeName){
        List meetings = new ArrayList<>();
        
        
        meetings = (List) getMeetings().stream()
                .filter(m -> ((Meeting) m)
                .getCreatedBy()
                .getName()
                .contains(employeeName))
                .collect(Collectors.toList());

        return meetings;

    }

    @Override
    public List getParticipantsForMeeting(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addMeeting(Long id) {
        
    }

    @Override
    public Long getEmployeeID(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
    
}

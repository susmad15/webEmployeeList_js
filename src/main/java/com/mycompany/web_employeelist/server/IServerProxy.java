
package com.mycompany.web_employeelist.server;

import com.mycompany.web_employeelist.data.Meeting;
import java.util.List;


public interface IServerProxy {
    
    public List getEmployees();
    
    public List getMeetings();
    
    public List getMeetingsForEmployee(String employeeName);
    
    public List getParticipantsForMeeting(int id);
    
    public void addMeeting(Long employeeID);
    
    public Long getEmployeeID(String name);
}

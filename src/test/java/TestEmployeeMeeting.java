import com.mycompany.web_employeelist.data.Employee;
import com.mycompany.web_employeelist.data.Meeting;
import com.mycompany.web_employeelist.server.IServerProxy;
import com.mycompany.web_employeelist.server.ServerProxyFactory;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;


public class TestEmployeeMeeting {

    public TestEmployeeMeeting () {
    }

    @BeforeClass
    public static void setUpClass () {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testEmployees () {

        IServerProxy serverProxy = ServerProxyFactory.getInstance ();
        //DBServerProxy serverProxy = new DBServerProxy();

        List<Employee> employees = serverProxy.getEmployees ();
        int empcount = employees.size ();

        assertEquals ( empcount, 103 );

    }

    @Test
    public void testMeetings () {

        IServerProxy serverProxy = ServerProxyFactory.getInstance ();
        //DBServerProxy serverProxy = new DBServerProxy();

        List<Meeting> meetings = serverProxy.getMeetings ();
        int meetcount = meetings.size ();

        assertTrue ( meetcount > 77 );

    }

    @Test
    public void testMeetingsForLastEmployee () {

        IServerProxy serverProxy = ServerProxyFactory.getInstance ();
        //DBServerProxy serverProxy = new DBServerProxy();

        List<Employee> employees = serverProxy.getEmployees ();
        int empcount = employees.size ();

        String lastEmployee = employees.get ( empcount - 1 ).getName ();
        
        System.out.println ("testMeetingsForLastEmployee: lastEmployee="+lastEmployee);
        //int meetingsForLast = serverProxy.getMeetingsForEmployee(lastEmployee)
        int meetingsForLast = serverProxy.getMeetingsForEmployee (
                employees.get ( empcount - 1 ).getName () ).size ();
        //employees.get ( empcount - 1 ).getId ().intValue () ).size ();

        assertTrue ( meetingsForLast > 1 );

    }

    @Test
    public void testAddMeeting () {

        IServerProxy serverProxy = ServerProxyFactory.getInstance ();
        //DBServerProxy serverProxy = new DBServerProxy();
        List<Meeting> meetingsBeforeAdd = serverProxy.getMeetings ();

        int countMeetings = serverProxy.getMeetings ().size ();
        //by PO for those who use addMeeting (int id);
        int countEmployees = serverProxy.getEmployees ().size ();
        Long lastEmployeeId = (( Employee ) serverProxy.getEmployees ().get ( countEmployees - 1 )).getId ();
        //serverProxy.addMeeting (lastEmployeeId);
        // in the PLF should be
        serverProxy.addMeeting(lastEmployeeId);

        System.out.println ( "testAddMeeting: lastEmployeeId=" + lastEmployeeId );

        List<Meeting> meetingsAfterAdd = serverProxy.getMeetings ();

        assertEquals ( meetingsAfterAdd.size () - meetingsBeforeAdd.size (), 1 );
    }

    @Test
    public void testAll () {

        IServerProxy serverProxy = ServerProxyFactory.getInstance ();
        //DBServerProxy serverProxy = new DBServerProxy();

        List<Employee> employees = serverProxy.getEmployees ();
        int empcount = employees.size ();

        List<Meeting> meetings = serverProxy.getMeetings ();
        int meetcount = meetings.size ();

        int meetforlast = serverProxy.getMeetingsForEmployee(
                //employees.get ( empcount - 1 ).getId ().intValue () ).size ();
                employees.get ( empcount - 1 ).getName () ).size ();

        System.out.printf ( "number of employees %d, number of meetings %d, meetforlast=%d\n",
                empcount, meetcount, meetforlast );
        //serverProxy.addMeeting ();

        assertEquals ( empcount, 103 );

    }

}
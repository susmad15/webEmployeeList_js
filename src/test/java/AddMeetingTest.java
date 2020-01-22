

import com.mycompany.web_employeelist.server.ServerProxyFactory;
import com.mycompany.web_employeelist.server.IServerProxy;
import com.mycompany.web_employeelist.data.Meeting;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;


public class AddMeetingTest {
    
    public AddMeetingTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

   
   // @Test
    public void testAddMeeting() {
        IServerProxy db = ServerProxyFactory.getInstance();
        List<Meeting> meetingsBeforeAdd = db.getMeetings();
        db.addMeeting(null);
        
        List<Meeting> meetingsAfterAdd = db.getMeetings();
        assertEquals(meetingsAfterAdd.size()- meetingsBeforeAdd.size(), 1);
    }
}

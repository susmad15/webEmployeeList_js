package com.mycompany.web_employeelist.server;

import com.mycompany.web_employeelist.data.Meeting;
import com.mycompany.web_employeelist.data.Employee;
import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class DBServerProxy implements IServerProxy {
    //this implements the Interface IServerProxy

    EntityManagerFactory emf;
    EntityManager em;

    public DBServerProxy() {

        emf = Persistence.createEntityManagerFactory("my-persistence-unit"); //the name of the persistence unit is passed
        em = emf.createEntityManager(); //an entity manager is created

    }

    @Override
    public List getEmployees() {
        Query query = em.createQuery("FROM Employee");

        //List employees = (List) query.getResultList()
        //.stream()
        //.collect(Collectors.toList());
        //OR
        return query.getResultList();
    }

    @Override
    public List getMeetings() {
        Query query = em.createQuery("FROM Meeting");

        //List employees = (List) query.getResultList()
        //.stream()
        //.collect(Collectors.toList());
        //OR
        return query.getResultList();
    }

    @Override
    public List getMeetingsForEmployee(String employeeName) {

        return (List) getMeetings()
                .stream() //stream is a special form of list, that is available for all kinds of collection. You can then use the provided methods of the 'stream object'
                .filter(m -> ((Meeting) m).getCreatedBy().getName().equals(employeeName))//m is kind of a 'variable' for each element in the stream, getCreatedBy()... is executed for every
                .collect(Collectors.toList());//used to get a 'normal' collection, in our case a List

    }

    //finds the employee and creates a meeting for him
    @Override
    public void addMeeting(Long employeeId) {
        em.getTransaction().begin();
        List meetings = getMeetings();
        List employees = getEmployees();

        List<Employee> result = (List) employees.stream().filter(e ->((Employee) e).getId() == employeeId).collect(Collectors.toList());
        Employee creator = result.get(0);
        
        
        Employee participant1 = (Employee) employees.get(employees.size() - 4);
        Employee participant2 = (Employee) employees.get(employees.size() - 5);

        Meeting meeting = new Meeting();
        meeting.setCreatedBy(creator);
        meeting.setStartsAt(
                ZonedDateTime.of(
                        2019, 11, 13,
                        20, 05, 0, 0,
                        ZoneId.systemDefault()
                )
        );
        meeting.setDuration(
                Duration.of(45, ChronoUnit.MINUTES)
        );
        List<Employee> participants = new ArrayList<Employee>();
        meeting.setParticipants(participants);

        participants.add(participant1);
        participants.add(participant2);

        meeting.setParticipants(participants);

        em.persist(meeting);
        em.getTransaction().commit();

    }
    
    @Override
    public Long getEmployeeID(String name) {
        List employees = getEmployees();

        List<Employee> result = (List) employees
                .stream()
                .filter(e -> ((Employee) e).getName().equals(name)).collect(Collectors.toList());
        
        long id = result.get(0).getId();

        return id;
    }


    @Override
    public List<Employee> getParticipantsForMeeting(int id) {

        List<Meeting> meetings = getMeetings();

        Meeting meeting = (Meeting) meetings.stream().filter(m -> m.getId() == id).collect(Collectors.toList()).get(0);

        return meeting.getParticipants();

    }

}

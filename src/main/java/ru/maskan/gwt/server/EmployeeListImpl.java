package ru.maskan.gwt.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import ru.maskan.gwt.client.Employee;
import ru.maskan.gwt.client.EmployeeList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akonshina on 23.05.14.
 */
public class EmployeeListImpl extends RemoteServiceServlet implements EmployeeList {


    @Override
    public List<Employee> getAll() {
        List<Employee> l = new ArrayList<Employee>();

        Employee e = new Employee();

        l.add(e);

        e.setId(1);
        e.setFirstname("Anna");
        e.setSecondname("V");
        e.setLastname("Kon");
        e.setExperience("Best");
        e.setAge(25);
        e.setDescription("my");

        e = new Employee();
        l.add(e);

        e.setId(1);
        e.setFirstname("Boris");
        e.setSecondname("V");
        e.setLastname("Ber");
        e.setExperience("Best of the best");
        e.setAge(24);
        e.setDescription("my ex");

        e = new Employee();
        l.add(e);

        e.setId(1);
        e.setFirstname("Rima");
        e.setSecondname("V");
        e.setLastname("Ol");
        e.setExperience("Best of the the best ");
        e.setAge(26);
        e.setDescription("my 7");

        return l;
    }
}
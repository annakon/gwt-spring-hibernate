package ru.maskan.gwt.server;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.springframework.stereotype.Service;
import ru.maskan.gwt.client.Employee;
import ru.maskan.gwt.client.EmployeeList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akonshina on 23.05.14.
 */
@Service("employeeService")
public class EmployeeListImpl implements RemoteService, EmployeeList {

    @Override
    public String update(Employee employee) {
        System.out.print(employee.getAge());
        return null;
    }

    @Override
    public List<Employee> search(String str) {
        List<Employee> l = new ArrayList<Employee>();
        Employee e = new Employee();

        e.setId("1");
        e.setFirstname("Anna");
        e.setSecondname("V");
        e.setLastname("Kon");
        e.setExperience("Best");
        e.setAge("25");
        e.setDescription("my");
        l.add(e);
        return l;
    }

    @Override
    public List<Employee> getAll() {
        List<Employee> l = new ArrayList<Employee>();

        Employee e = new Employee();

        l.add(e);

        e.setId("1");
        e.setFirstname("Anna");
        e.setSecondname("V");
        e.setLastname("Kon");
        e.setExperience("Best");
        e.setAge("25");
        e.setDescription("my");

        e = new Employee();
        l.add(e);

        e.setId("2");
        e.setFirstname("Boris");
        e.setSecondname("V");
        e.setLastname("Ber");
        e.setExperience("Best of the best");
        e.setAge("24");
        e.setDescription("my ex");

        e = new Employee();
        l.add(e);

        e.setId("3");
        e.setFirstname("Rima");
        e.setSecondname("V");
        e.setLastname("Ol");
        e.setExperience("Best of the the best ");
        e.setAge("26");
        e.setDescription("my 7");

        return l;
    }
}
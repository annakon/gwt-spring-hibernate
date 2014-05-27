package ru.maskan.gwt.server;

import com.google.gwt.user.client.rpc.RemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.maskan.gwt.client.Employee;
import ru.maskan.gwt.client.EmployeeList;
import ru.maskan.gwt.server.dao.EmployeeDAO;
import ru.maskan.gwt.server.domain.HbmEmployee;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akonshina on 23.05.14.
 */
@Service("employeeService")
public class EmployeeListImpl implements RemoteService, EmployeeList {


    @Autowired
    private EmployeeDAO dao;

    @Override
    public String update(Employee employee) {
        System.out.print(employee.getAge());
        return null;
    }

    @Override
    public List<Employee> search(String str) {

        List<Employee> l = new ArrayList<Employee>();

        for (HbmEmployee c : dao.search(str)) {
            l.add(c.toGwtObject());
        }

        return l;
    }

    @Override
    public List<Employee> getAll() {

        List<Employee> l = new ArrayList<Employee>();

        for (HbmEmployee c : dao.findAll()) {
            l.add(c.toGwtObject());
        }

        return l;
    }
}
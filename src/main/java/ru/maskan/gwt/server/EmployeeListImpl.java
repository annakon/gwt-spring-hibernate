package ru.maskan.gwt.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import ru.maskan.gwt.client.Employee;
import ru.maskan.gwt.client.EmployeeList;

/**
 * Created by akonshina on 23.05.14.
 */
public class EmployeeListImpl extends RemoteServiceServlet implements EmployeeList {


    @Override
    public Employee[] getAll() {
        return new Employee[2];
    }
}
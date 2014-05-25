package ru.maskan.gwt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.List;

/**
 * Created by akonshina on 23.05.14.
 */
@RemoteServiceRelativePath("springGwtServices/employeeService")
public interface EmployeeList extends RemoteService {
    List<Employee> getAll();

    String update(Employee employee);


    List<Employee> search(String str);


    /**
     * Utility/Convenience class.
     * Use EmployeeList.App.getInstance() to access static instance of EmployeeListAsync
     */
    public static class App {
        private static final EmployeeListAsync ourInstance = (EmployeeListAsync) GWT.create(EmployeeList.class);

        public static EmployeeListAsync getInstance() {
            return ourInstance;
        }
    }
}

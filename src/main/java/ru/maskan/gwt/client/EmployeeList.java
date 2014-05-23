package ru.maskan.gwt.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.core.client.GWT;

/**
 * Created by akonshina on 23.05.14.
 */
@RemoteServiceRelativePath("EmployeeList")
public interface EmployeeList extends RemoteService {
    Employee[] getAll();

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

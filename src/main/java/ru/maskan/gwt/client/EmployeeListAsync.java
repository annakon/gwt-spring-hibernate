package ru.maskan.gwt.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Created by akonshina on 23.05.14.
 */
public interface EmployeeListAsync {
    void getAll(AsyncCallback<Employee[]> async);
}

package ru.maskan.gwt.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

/**
 * Created by akonshina on 23.05.14.
 */
public interface EmployeeListAsync {
    void getAll(AsyncCallback<List<Employee>> async);

    void update(Employee employee, AsyncCallback<String> async);

    void search(String str, AsyncCallback<List<Employee>> async);

    void remove(int id, AsyncCallback<String> async);
}

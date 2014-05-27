package ru.maskan.gwt.client;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

/**
 * Created by akonshina on 26.05.14.
 */
public class EmployeeListAsyncCallback implements AsyncCallback<List<Employee>> {

    private CellTable table;

    public EmployeeListAsyncCallback(CellTable table) {
        this.table = table;
    }

    @Override
    public void onFailure(Throwable caught) {
        Window.alert(caught.getMessage());
    }

    @Override
    public void onSuccess(List<Employee> result) {
        table.setRowData(result);
    }
}

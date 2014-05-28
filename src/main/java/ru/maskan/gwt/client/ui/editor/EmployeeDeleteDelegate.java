package ru.maskan.gwt.client.ui.editor;

import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.maskan.gwt.client.Employee;
import ru.maskan.gwt.client.EmployeeListAsync;
import ru.maskan.gwt.client.EmployeeListAsyncCallback;

/**
 * Created by akonshina on 26.05.14.
 */
public class EmployeeDeleteDelegate implements ActionCell.Delegate<Employee> {

    private final EmployeeListAsync service;
    private CellTable<Employee> table;

    public EmployeeDeleteDelegate(EmployeeListAsync service) {
        this.service = service;
    }

    public void setTable(CellTable<Employee> table) {
        this.table = table;
    }

    @Override
    public void execute(Employee object) {

        boolean canDelete = Window.confirm("Are you sure?");
        if (!canDelete) {
            return;
        }

        AsyncCallback<String> callback = new AsyncCallback<String>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert(caught.getMessage());
            }

            @Override
            public void onSuccess(String result) {
                Window.alert(result);

                EmployeeListAsyncCallback callback2 = new EmployeeListAsyncCallback(table);
                service.getAll(callback2);
            }
        };
        service.remove(Integer.valueOf(object.getId()), callback);


    }
}

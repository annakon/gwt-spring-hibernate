package ru.maskan.gwt.client.ui.editor;

import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.maskan.gwt.client.Employee;
import ru.maskan.gwt.client.EmployeeListAsync;
import ru.maskan.gwt.client.EmployeeListAsyncCallback;
import ru.maskan.gwt.client.Test;

/**
 * Created by akonshina on 26.05.14.
 */
public class EmployeeDeleteDelegate implements ActionCell.Delegate<Employee> {

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

                Test.service.getAll(new EmployeeListAsyncCallback());
            }
        };
        Test.service.remove(Integer.valueOf(object.getId()), callback);


    }
}

package ru.maskan.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import ru.maskan.gwt.client.ui.EmployeeTableBuilder;
import ru.maskan.gwt.client.ui.editor.EmployeeEditor;

import java.util.List;

/**
 * Created by akonshina on 23.05.14.
 */
public class Test implements EntryPoint {




    private EmployeeTableBuilder tableBuilder = new EmployeeTableBuilder();

    //    private ArrayList<String> stocks = new ArrayList<String>();
    private EmployeeListAsync service = GWT.create(EmployeeList.class);

    public void onModuleLoad() {

        EmployeeEditor editor = new EmployeeEditor();

        AsyncDataProvider<Employee> provider = new AsyncDataProvider<Employee>() {
            @Override
            protected void onRangeChanged(HasData<Employee> display) {
                final int start = display.getVisibleRange().getStart();

                AsyncCallback<List<Employee>> callback = new AsyncCallback<List<Employee>>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        Window.alert(caught.getMessage());
                    }

                    @Override
                    public void onSuccess(List<Employee> result) {
                        updateRowData(start, result);
                    }
                };
                // The remote service that should be implemented
                service.getAll(callback);
            }
        };


        CellTable table = tableBuilder.buildTable(provider, editor);

        RootPanel.get("empl-list").add(table);
        RootPanel.get("edit-form").add(editor);
    }
}
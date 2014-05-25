package ru.maskan.gwt.client.ui.editor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import ru.maskan.gwt.client.Employee;
import ru.maskan.gwt.client.EmployeeListAsync;

import java.util.List;

/**
 * Created by akonshina on 25.05.14.
 */
public class TopPanel extends Composite {

    private final EmployeeListAsync service;
    private final CellTable table;

    interface TopPanelBinder extends UiBinder<Widget, TopPanel> {}
    private static TopPanelBinder uiBinder = GWT.create(TopPanelBinder.class);

    EmployeeEditorDriver driver = GWT.create(EmployeeEditorDriver.class);

    @UiField TextBox search;

    public TopPanel(EmployeeListAsync service, CellTable table) {
        initWidget(uiBinder.createAndBindUi(this));

        this.service = service;
        this.table = table;
    }

    public EmployeeEditorDriver getDriver() {
        return driver;
    }

    @UiHandler("btnSearch")
    void save(ClickEvent e) {

        AsyncCallback<List<Employee>> callback = new AsyncCallback<List<Employee>>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert(caught.getMessage());
            }

            @Override
            public void onSuccess(List<Employee> result) {
                table.setRowData(result);
            }
        };

        service.search(search.getValue(), callback);
    }

    public void edit(Employee employee) {
        driver.edit(employee);
    }
}



package ru.maskan.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.RootPanel;
import ru.maskan.gwt.client.ui.EmployeeTableBuilder;
import ru.maskan.gwt.client.ui.editor.EmployeeDeleteDelegate;
import ru.maskan.gwt.client.ui.editor.EmployeeEditDelegate;
import ru.maskan.gwt.client.ui.editor.EmployeeEditor;
import ru.maskan.gwt.client.ui.editor.TopPanel;

/**
 * Created by akonshina on 23.05.14.
 */
public class Test implements EntryPoint {

    private EmployeeTableBuilder tableBuilder = new EmployeeTableBuilder();

    //    private ArrayList<String> stocks = new ArrayList<String>();
    private EmployeeListAsync service = GWT.create(EmployeeList.class);

    public void onModuleLoad() {

        final EmployeeEditor editor = new EmployeeEditor(service);
        editor.setVisible(false);

        EmployeeEditDelegate editDelegate = new EmployeeEditDelegate(editor);
        EmployeeDeleteDelegate deleteDelegate = new EmployeeDeleteDelegate(service);

        final CellTable table = tableBuilder.buildTable(editDelegate, deleteDelegate);

        EmployeeListAsyncCallback callback = new EmployeeListAsyncCallback(table);
        service.getAll(callback);



        TopPanel topPanel = new TopPanel(service, callback, editDelegate);

        RootPanel.get("empl-top-panel").add(topPanel);
        RootPanel.get("empl-list").add(table);
        RootPanel.get("edit-form").add(editor);
    }
}
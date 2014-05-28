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

    public static EmployeeListAsync service;

    public static TopPanel topPanel;
    public static CellTable table;
    public static EmployeeEditor editor;

    public static EmployeeEditDelegate editDelegate;
    public static EmployeeDeleteDelegate deleteDelegate;

    public void onModuleLoad() {

        service = GWT.create(EmployeeList.class);

        editor = new EmployeeEditor(service);
        editor.setVisible(false);

        editDelegate = new EmployeeEditDelegate();
        deleteDelegate = new EmployeeDeleteDelegate();

        table = tableBuilder.buildTable();

        service.getAll(new EmployeeListAsyncCallback());

        topPanel = new TopPanel();

        RootPanel.get("empl-top-panel").add(topPanel);
        RootPanel.get("empl-list").add(table);
        RootPanel.get("edit-form").add(editor);
    }
}
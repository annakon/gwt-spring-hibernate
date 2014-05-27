package ru.maskan.gwt.client.ui.editor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import ru.maskan.gwt.client.Employee;
import ru.maskan.gwt.client.EmployeeListAsync;
import ru.maskan.gwt.client.EmployeeListAsyncCallback;

/**
 * Created by akonshina on 25.05.14.
 */
public class TopPanel extends Composite {

    private final EmployeeListAsync service;
    private final EmployeeListAsyncCallback callback;
    private final EmployeeEditDelegate editDelegate;

    interface TopPanelBinder extends UiBinder<Widget, TopPanel> {}
    private static TopPanelBinder uiBinder = GWT.create(TopPanelBinder.class);


    @UiField TextBox search;

    public TopPanel(EmployeeListAsync service, EmployeeListAsyncCallback callback, EmployeeEditDelegate editDelegate) {
        initWidget(uiBinder.createAndBindUi(this));

        this.service = service;
        this.callback = callback;
        this.editDelegate = editDelegate;
    }

    @UiHandler("btnSearch")
    void save(ClickEvent e) {
        service.search(search.getValue(), callback);
    }

    @UiHandler("btnAdd")
    public void showForm(ClickEvent e) {

        editDelegate.execute(new Employee());
    }
}



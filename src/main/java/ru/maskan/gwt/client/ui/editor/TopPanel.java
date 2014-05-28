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
import ru.maskan.gwt.client.Test;

/**
 * Created by akonshina on 25.05.14.
 */
public class TopPanel extends Composite {

    interface TopPanelBinder extends UiBinder<Widget, TopPanel> {}
    private static TopPanelBinder uiBinder = GWT.create(TopPanelBinder.class);


    @UiField TextBox search;

    public TopPanel() {
        initWidget(uiBinder.createAndBindUi(this));
}

    @UiHandler("btnSearch")
    void save(ClickEvent e) {
        Test.service.search(search.getValue(), new EmployeeListAsyncCallback());
    }

    @UiHandler("btnAdd")
    public void showForm(ClickEvent e) {

        Test.editDelegate.execute(new Employee());
    }
}



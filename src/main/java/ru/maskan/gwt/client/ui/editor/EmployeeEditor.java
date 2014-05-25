package ru.maskan.gwt.client.ui.editor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import ru.maskan.gwt.client.Employee;

/**
 * Created by akonshina on 25.05.14.
 */
public class EmployeeEditor extends Composite implements Editor<Employee> {

    interface EmployeeEditorBinder extends UiBinder<Widget, EmployeeEditor> {}
    private static EmployeeEditorBinder uiBinder = GWT.create(EmployeeEditorBinder.class);

    private int id;
    @UiField TextBox lastname;
    @UiField TextBox firstname;
    @UiField TextBox secondname;
    @UiField TextBox age;
    @UiField TextBox experience;
    @UiField TextBox description;

    public EmployeeEditor() {
        initWidget(uiBinder.createAndBindUi(this));
    }

}


package ru.maskan.gwt.client.ui.editor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.validation.client.impl.Validation;
import ru.maskan.gwt.client.Employee;
import ru.maskan.gwt.client.EmployeeListAsync;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.List;
import java.util.Set;

/**
 * Created by akonshina on 25.05.14.
 */
public class EmployeeEditor extends Composite implements Editor<Employee> {

    private final EmployeeListAsync service;

    interface EmployeeEditorBinder extends UiBinder<Widget, EmployeeEditor> {}
    private static EmployeeEditorBinder uiBinder = GWT.create(EmployeeEditorBinder.class);

    EmployeeEditorDriver driver = GWT.create(EmployeeEditorDriver.class);

    @UiField Hidden id;
    @UiField TextBox lastname;
    @UiField TextBox firstname;
    @UiField TextBox secondname;
    @UiField TextBox age;
    @UiField TextBox experience;
    @UiField TextBox description;

    public EmployeeEditor(EmployeeListAsync service) {
        initWidget(uiBinder.createAndBindUi(this));

        driver.initialize(this);
        this.service = service;
    }

    public EmployeeEditorDriver getDriver() {
        return driver;
    }

    @UiHandler("btnSave")
    void save(ClickEvent e) {
        Employee employee = driver.flush();


//        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
//
//        Set<ConstraintViolation<Employee>> violations = validator.validate(employee, Default.class);
//        if (!violations.isEmpty()) {
//            // client-side violation(s) occurred
//            Window.alert("not valid");
//        } else {
//            // client-side validation passed so check server-side
//
//        }

        AsyncCallback<String> callback = new AsyncCallback<String>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert(caught.getMessage());
            }

            @Override
            public void onSuccess(String result) {
                Window.alert(result);
            }
        };

        service.update(employee,callback);
    }

    public void edit(Employee employee) {
        driver.edit(employee);
    }
}


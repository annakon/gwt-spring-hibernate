package ru.maskan.gwt.client;

import com.google.gwt.cell.client.*;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.cellview.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import ru.maskan.gwt.client.ui.ActionHasCell;
import ru.maskan.gwt.client.ui.InputAndLabel;

import java.util.LinkedList;
import java.util.List;

import static com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.DISABLED;

/**
 * Created by akonshina on 23.05.14.
 */
public class Test implements EntryPoint {

    //    private ArrayList<String> stocks = new ArrayList<String>();
    private EmployeeListAsync service = GWT.create(EmployeeList.class);

    public void onModuleLoad() {


        final CellTable<Employee> table = new CellTable<Employee>();
        table.setStyleName("table table-striped");
        table.setKeyboardSelectionPolicy(DISABLED);

        TextColumn<Employee> lastNameColumn = new TextColumn<Employee>() {
            @Override
            public String getValue(Employee employee) {
                return employee.getLastname();
            }
        };

        TextColumn<Employee> firstNameColumn = new TextColumn<Employee>() {
            @Override
            public String getValue(Employee employee) {
                return employee.getFirstname();
            }
        };

        TextColumn<Employee> secondNameColumn = new TextColumn<Employee>() {
            @Override
            public String getValue(Employee employee) {
                return employee.getSecondname();
            }
        };

        TextColumn<Employee> ageColumn = new TextColumn<Employee>() {
            @Override
            public String getValue(Employee employee) {
                return String.valueOf(employee.getAge());
            }
        };

        TextColumn<Employee> descriptionColumn = new TextColumn<Employee>() {
            @Override
            public String getValue(Employee employee) {
                return employee.getDescription();
            }
        };

        TextColumn<Employee> experienceColumn = new TextColumn<Employee>() {
            @Override
            public String getValue(Employee employee) {
                return employee.getExperience();
            }
        };

        TextColumn<Employee> idColumn = new TextColumn<Employee>() {
            @Override
            public String getValue(Employee employee) {
                return String.valueOf(employee.getId());
            }
        };

        //TODO use constants
        SafeHtml s = new SafeHtml() {
            @Override
            public String asString() {
                return "<span class=\"glyphicon glyphicon-edit\"></span>";
            }
        };


        List<HasCell<Employee, ?>> cells = new LinkedList<HasCell<Employee, ?>>();
        cells.add(new ActionHasCell(s, new ActionCell.Delegate<Employee>() {

            @Override
            public void execute(Employee object) {
                // EDIT CODE
            }
        }));
        cells.add(new ActionHasCell(s, new ActionCell.Delegate<Employee>() {

            @Override
            public void execute(Employee object) {
                // DELETE CODE
            }
        }));
        CompositeCell<Employee> cell = new CompositeCell<Employee>(cells);

        Column<Employee, Employee> editColumn = new Column<Employee, Employee>(cell) {
                @Override
                public Employee getValue(Employee employee) {
                    return employee;
                }
            };

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

        // Add the columns.
        table.addColumn(idColumn, "#");
        table.addColumn(lastNameColumn, "Фамилия");
        table.addColumn(firstNameColumn, "Имя");
        table.addColumn(secondNameColumn, "Отчество");
        table.addColumn(ageColumn, "Возраст");
        table.addColumn(experienceColumn, "Опыт");
        table.addColumn(descriptionColumn, "Описание");
        table.addColumn(editColumn, "Действия");

        table.setVisibleRange(0, 3);

        // Connect the list to the data provider.
        provider.addDataDisplay(table);

        // We know that the data is sorted alphabetically by default.
        table.getColumnSortList().push(lastNameColumn);

        // Add it to the root panel.
        RootPanel.get("qweqew").add(table);

        // Create a FormPanel and point it at a service.
        final FormPanel form = new FormPanel();
        form.setAction("/myFormHandler");

        // Because we're going to add a FileUpload widget, we'll need to set the
        // form to use the POST method, and multipart MIME encoding.
        form.setEncoding(FormPanel.ENCODING_MULTIPART);
        form.setMethod(FormPanel.METHOD_POST);

        // Create a panel to hold all of the form widgets.
        FlowPanel panel = new FlowPanel();
        form.setWidget(panel);

        // Create a TextBox, giving it a name so that it will be submitted.
//        FlowPanel panel1 = new FlowPanel();
        final InputAndLabel lastnameElement = new InputAndLabel("Фамилия", "Введите фамилию");
        panel.add(lastnameElement);

        final InputAndLabel firstnameElement = new InputAndLabel("Имя", "Введите имя");
        panel.add(firstnameElement);

        final InputAndLabel secondnameElement = new InputAndLabel("Отчество", "Введите отчество");
        panel.add(secondnameElement);

        final InputAndLabel ageElement = new InputAndLabel("Возраст", "Введите возраст");
        panel.add(ageElement);

        final InputAndLabel experienceElement = new InputAndLabel("Опыт", "Введите опыт");
        panel.add(experienceElement);

        final InputAndLabel descriptionElement = new InputAndLabel("Описание", "Введите описание");
        panel.add(descriptionElement);

        Button b = new Button("Сохранить", new ClickHandler() {
            public void onClick(ClickEvent event) {
                form.submit();
            }
        });
        b.setStyleName("btn btn-default");

        // Add a 'submit' button.
        panel.add(b);

        // Add an event handler to the form.
//        form.addSubmitHandler(new FormPanel.SubmitHandler() {
//            public void onSubmit(FormPanel.SubmitEvent event) {
//                // This event is fired just before the form is submitted. We can take
//                // this opportunity to perform validation.
//                if (tb.getText().length() == 0) {
//                    Window.alert("The text box must not be empty");
//                    event.cancel();
//                }
//            }
//        });
//        form.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
//            public void onSubmitComplete(FormPanel.SubmitCompleteEvent event) {
//                // When the form submission is successfully completed, this event is
//                // fired. Assuming the service returned a response of type text/html,
//                // we can get the result text here (see the FormPanel documentation for
//                // further explanation).
//                Window.alert(event.getResults());
//            }
//        });

        RootPanel.get("edit-form").add(form);
    }
}

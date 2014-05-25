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
import ru.maskan.gwt.client.ui.EmployeeTableBuilder;
import ru.maskan.gwt.client.ui.InputAndLabel;

import java.util.LinkedList;
import java.util.List;

import static com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.DISABLED;

/**
 * Created by akonshina on 23.05.14.
 */
public class Test implements EntryPoint {

    private EmployeeTableBuilder tableBuilder = new EmployeeTableBuilder();

    //    private ArrayList<String> stocks = new ArrayList<String>();
    private EmployeeListAsync service = GWT.create(EmployeeList.class);

    public void onModuleLoad() {

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


        CellTable table = tableBuilder.buildTable(provider);

        // Add it to the root panel.
        RootPanel.get("empl-list").add(table);

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

package ru.maskan.gwt.client;

import com.google.gwt.cell.client.*;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;

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

//        Column<Employee> editColumn = new Column<Employee>(new ButtonCell() {
//
//            @Override
//            public Employee getValue(Object o) {
//                return null;
//            }
//        });

//        private <C> Column<ContactInfo, C> addColumn(Cell<C> cell, String headerText,
//        final GetValue<C> getter, FieldUpdater<ContactInfo, C> fieldUpdater) {
//            Column<ContactInfo, C> column = new Column<ContactInfo, C>(cell) {
//                @Override
//                public C getValue(ContactInfo object) {
//                    return getter.getValue(object);
//                }
//            };
//            column.setFieldUpdater(fieldUpdater);
//            if (cell instanceof AbstractEditableCell<?, ?>) {
//                editableCells.add((AbstractEditableCell<?, ?>) cell);
//            }
//            contactList.addColumn(column, headerText);
//            return column;
//        }

//        addColumn(new ButtonCell(), "Button", new GetValue<String>() {
//            @Override
//            public String getValue(ContactInfo contact) {
//                return "Click " + contact.getFirstName();
//            }
//        }, new FieldUpdater<ContactInfo, String>() {
//            @Override
//            public void update(int index, ContactInfo object, String value) {
//                Window.alert("You clicked " + object.getFullName());
//            }
//        });

//        ButtonCell b = new ButtonCell() {
//
//            @Override
//            public void render(Context context, SafeHtml data, SafeHtmlBuilder sb) {
//                super.render(context, data, sb);
//            }
//
//            @Override
//            protected void onEnterKeyDown(Context context, Element parent, String value, NativeEvent event, ValueUpdater<String> valueUpdater) {
//                super.onEnterKeyDown(context, parent, value, event, valueUpdater);
//
//                Window.alert(context.getKey().toString());
//            }
//        };

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
    }

    private class ActionHasCell implements HasCell<Employee, Employee> {
        private ActionCell<Employee> cell;

        public ActionHasCell(SafeHtml text, ActionCell.Delegate<Employee> delegate) {
            cell = new ActionCell<Employee>(text, delegate);
        }

        @Override
        public Cell<Employee> getCell() {
            return cell;
        }

        @Override
        public FieldUpdater<Employee, Employee> getFieldUpdater() {
            return null;
        }

        @Override
        public Employee getValue(Employee object) {
            return object;
        }
    }
}

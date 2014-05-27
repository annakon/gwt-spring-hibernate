package ru.maskan.gwt.client.ui;

import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.cell.client.CompositeCell;
import com.google.gwt.cell.client.HasCell;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import ru.maskan.gwt.client.Employee;
import ru.maskan.gwt.client.ui.editor.EmployeeEditDelegate;

import java.util.LinkedList;
import java.util.List;

import static com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.DISABLED;

/**
 * Created by akonshina on 25.05.14.
 */
public class EmployeeTableBuilder {

    private final SafeHtml editIcon = new SafeHtml() {
        @Override
        public String asString() {
            return "<span class=\"glyphicon glyphicon-edit\"></span>";
        }
    };

    private final SafeHtml removeIcon = new SafeHtml() {
        @Override
        public String asString() {
            return "<span class=\"glyphicon glyphicon-remove\"></span>";
        }
    };

    public CellTable buildTable(EmployeeEditDelegate editDelegate) {
        final CellTable<Employee> table = new CellTable<Employee>();
        table.setStyleName("table table-striped");
        table.setKeyboardSelectionPolicy(DISABLED);

        editDelegate.setTable(table);

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
                return employee.getAge();
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
                return employee.getId();
            }
        };



        List<HasCell<Employee, ?>> cells = new LinkedList<HasCell<Employee, ?>>();
        cells.add(new ActionHasCell(editIcon, editDelegate));
        cells.add(new ActionHasCell(removeIcon, new ActionCell.Delegate<Employee>() {

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

        // We know that the data is sorted alphabetically by default.
        table.getColumnSortList().push(lastNameColumn);

        return table;
    }
}

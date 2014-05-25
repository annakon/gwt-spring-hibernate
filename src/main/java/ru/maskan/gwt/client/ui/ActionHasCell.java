package ru.maskan.gwt.client.ui;

import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.HasCell;
import com.google.gwt.safehtml.shared.SafeHtml;
import ru.maskan.gwt.client.Employee;

/**
 * Created by akonshina on 25.05.14.
 */
public class ActionHasCell implements HasCell<Employee, Employee> {

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


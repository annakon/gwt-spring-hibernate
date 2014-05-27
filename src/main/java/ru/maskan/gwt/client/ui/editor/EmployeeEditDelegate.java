package ru.maskan.gwt.client.ui.editor;

import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.user.cellview.client.CellTable;
import ru.maskan.gwt.client.Employee;

/**
 * Created by akonshina on 26.05.14.
 */
public class EmployeeEditDelegate implements ActionCell.Delegate<Employee> {

    private final EmployeeEditor editor;
    private CellTable<Employee> table;

    public EmployeeEditDelegate(EmployeeEditor editor) {
        this.editor = editor;
    }

    public void setTable(CellTable<Employee> table) {
        this.table = table;
    }

    @Override
    public void execute(Employee object) {
        table.setVisible(false);
        editor.edit(object);
        editor.setVisible(true);
    }
}

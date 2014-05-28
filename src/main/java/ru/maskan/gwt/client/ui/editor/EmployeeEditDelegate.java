package ru.maskan.gwt.client.ui.editor;

import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.user.cellview.client.CellTable;
import ru.maskan.gwt.client.Employee;
import ru.maskan.gwt.client.Test;

/**
 * Created by akonshina on 26.05.14.
 */
public class EmployeeEditDelegate implements ActionCell.Delegate<Employee> {


    @Override
    public void execute(Employee object) {
        Test.topPanel.setVisible(false);
        Test.table.setVisible(false);
        Test.editor.edit(object);
        Test.editor.setVisible(true);
    }

}

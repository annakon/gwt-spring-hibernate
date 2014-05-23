package ru.maskan.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

/**
 * Created by akonshina on 23.05.14.
 */
public class Test implements EntryPoint {

//    private ArrayList<String> stocks = new ArrayList<String>();
    private EmployeeListAsync service = GWT.create(EmployeeList.class);

    public void onModuleLoad() {

        final Grid grid = new Grid(4, 1);

        grid.setText(0,0,"One");
        grid.setText(1,0,"Two");
        grid.setText(2,0,"three");
        grid.setText(3,0,"four");
//        grid.addStyleName("cw-FlexTable");

        Label a = new Label();

        service.getAll(new MyAsyncCallback(a));

        RootPanel.get("slot1").add(grid);
        RootPanel.get("slot2").add(a);
    }

    private static class MyAsyncCallback implements AsyncCallback<Employee[]> {
        private Label label;

        public MyAsyncCallback(Label label) {
            this.label = label;
        }

        public void onSuccess(Employee[] result) {
            label.getElement().setInnerHTML(String.valueOf(result.length));
        }

        public void onFailure(Throwable throwable) {
            label.setText("Failed to receive answer from server!");
        }
    }
}

package ru.maskan.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.*;

/**
 * Created by akonshina on 23.05.14.
 */
public class Test implements EntryPoint {
    public void onModuleLoad() {

        final Grid grid = new Grid(4, 1);

        grid.setText(0,0,"One");
        grid.setText(1,0,"Two");
        grid.setText(2,0,"three");
        grid.setText(3,0,"four");
//        grid.addStyleName("cw-FlexTable");

        RootPanel.get("slot1").add(grid);
    }
}

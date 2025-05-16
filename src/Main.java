//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import controller.AppController;
import model.DatabaseManager;
import view.AppView;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        AppView view = new AppView();
        DatabaseManager dbManager = new DatabaseManager();
        AppController controller = new AppController(view, dbManager);

        controller.start();
    }
}
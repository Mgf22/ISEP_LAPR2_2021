package app.ui.console;

import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */

public class AdminUI implements Runnable{
    public AdminUI()
    {
    }

    public void run()
    {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Get list of employees by the respective role ", new EmployeeListUI()));
        options.add(new MenuItem("Register a new vaccination center ", new RegisterCenterUI()));
        options.add(new MenuItem("Register Employee ", new RegisterEmployeeUI()));
        options.add(new MenuItem("Register a Vaccine", new VaccineUI()));
        options.add(new MenuItem("Load a CSV file with users", new LoadCSVUI()));

        int option = 0;
        do
        {
            option = Utils.showAndSelectIndex(options, "\n\nAdmin Menu:");

            if ( (option >= 0) && (option < options.size()))
            {
                options.get(option).run();
            }
        }
        while (option != -1 );
    }
}

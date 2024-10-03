package app.ui.console;


import app.domain.model.Company;
import app.ui.console.utils.Utils;
import app.controller.App;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class MainMenuUI {
    private App app;
    private Company company;
    public MainMenuUI() {
        this.app = App.getInstance();
        this.company = app.getCompany();
    }

    public void run()
    {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Do Login", new AuthUI()));
        options.add(new MenuItem("Know the Development Team",new DevTeamUI()));
        int option = 0;
        do
        {
            option = Utils.showAndSelectIndex(options, "\n\nMain Menu");

            if ( (option >= 0) && (option < options.size()))
            {
                options.get(option).run();
            }
        }
        while (option != -1 );
        company.shutdownTimer();
    }
}

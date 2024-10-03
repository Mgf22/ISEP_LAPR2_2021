package app.ui.console;

import app.controller.LoadCSVController;
import app.controller.mappers.dto.SNSUserDTO;
import app.ui.console.utils.Utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Mateus Fernandes <1210821@isep.ipp.pt>
 */

public class LoadCSVUI implements Runnable {

    private final LoadCSVController controller;

    public LoadCSVUI() {
        this.controller = new LoadCSVController();
    }

    public void run() {
        String path;
        path = Utils.readLineFromConsole("Insert the desired path:");
        File f = new File(path);
        if (f.exists()) {

            try {
                if (controller.loadCSV(path)) {
                    /*List<SNSUserDTO> sns = controller.getSNSUser();
                    for (SNSUserDTO user : sns) {
                        System.out.println(user.toString());
                    }*/
                    Utils.printToConsole("\nFile was read with success");
                } else {
                    System.out.println("Incorret path, file does not exist or file corrupt");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


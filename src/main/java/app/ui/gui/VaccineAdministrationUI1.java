package app.ui.gui;

import app.controller.App;
import app.controller.VaccineAdministrationController;
import app.controller.mappers.dto.VaccineListDTO;
import app.domain.model.VaccineAdministration;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Tomás Pereira <1210830@isep.ipp.pt>
 */
public class VaccineAdministrationUI1 extends Application implements Runnable {
    private VaccineAdministrationController controller;
    private Stage stage;
    private static boolean javaFxLaunched;
    @FXML
    Button searchBtn;
    @FXML
    Button cancelBtn;
    @FXML
    TextField snsNumText;

    public VaccineAdministrationUI1() {
        this.controller = new VaccineAdministrationController();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VaccineAdministrationUI1.fxml"));
        Parent root = null;
        root = loader.load();
        Scene scene = new Scene(root);
        this.stage = stage;
        this.stage.setTitle("Vaccine Administration");
        this.stage.setResizable(false);
        this.stage.setScene(scene);
        this.stage.show();
    }


    @Override
    public void run() {
        this.openFX();
    }

    @FXML
    public void handleSearchButtonOnClick() throws IOException {
        if (controller.checkUserInWaitingList(snsNumText.getText())) {
            VaccineAdministration va = controller.checkVaccineRecord(snsNumText.getText());
            if (va == null) {
                VaccineListDTO vlDTO = controller.showVaccineList();
                if (vlDTO.size() != 0) {
                    //Hide stage and change scene
                    Stage stage = (Stage) searchBtn.getScene().getWindow();
                    stage.hide();
                    changeScene(vlDTO);
                } else {
                    createWarningAlert("Error", "No vaccines registered for this vaccine type.").show();
                }
            } else {
                VaccineListDTO vlDTO = controller.createDTO(va.getVaccine());

                //Hide stage and change scene
                Stage stage = (Stage) searchBtn.getScene().getWindow();
                stage.hide();
                changeScene(vlDTO);
            }
        } else {
            createWarningAlert("Error", "The user is not on the waiting list.").show();
        }
    }

    @FXML
    public void handleCancelButtonOnClick() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    private Alert createWarningAlert(String title, String headerText) {
        Alert alert = new Alert(Alert.AlertType.WARNING);

        alert.setTitle(title);
        alert.setHeaderText(headerText);

        return alert;
    }

    public Stage changeScene(VaccineListDTO vlDTO) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VaccineAdministrationUI2.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));

        VaccineAdministrationUI2 controller = loader.getController();
        controller.initData(vlDTO, this.controller);

        stage.show();

        return stage;
    }

    /**
     * Opens FX in another thread, which avoids blocking the command-line
     */
    public void openFX() {
        if (!App.getIsJavaFxLaunched()) { // First time
            Platform.setImplicitExit(false);
            new Thread(()->Application.launch(this.getClass())).start();
            App.setIsJavaFxLaunched(true);
        } else { // Next times
            Platform.runLater(()->{
                try {
                    Application application = this.getClass().newInstance();
                    Stage primaryStage = new Stage();
                    application.start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }
}

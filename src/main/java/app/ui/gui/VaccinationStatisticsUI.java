package app.ui.gui;

import app.controller.App;
import app.controller.VaccinationStatisticsController;

import app.tools.VaccineAdministrationCounter;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class VaccinationStatisticsUI extends Application implements Runnable {
    private int centerIndex;

    private static boolean javaFxLaunched;

    private Stage stage;

    private VaccinationStatisticsController controller;

    @FXML
    private DatePicker datePicker1;

    @FXML
    private DatePicker datePicker2;

    @FXML
    private Button showBtn;

    @FXML
    private TableView statisticsTable;

    @FXML
    private TableColumn c1;

    @FXML
    private TableColumn c2;

    @FXML
    private Button exportBtn;


    public VaccinationStatisticsUI() {
        this.controller = new VaccinationStatisticsController();
        this.centerIndex = App.getInstance().getCurrentCenterIndex();
    }

    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VaccinationStatisticsUI.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            this.stage = stage;
            this.stage.setTitle("Vaccination Statistics");
            this.stage.setResizable(false);
            this.stage.setAlwaysOnTop(true);
            this.stage.setScene(scene);
            this.stage.show();

        } catch (IOException ex) {

        }
    }

    public void run() {
        this.openFX();
    }

    /**
     * Checks if the dates are valid, and if so, unlocks the export button
     */
    @FXML
    private void handleCheckDates() {
        Stage stage = (Stage) datePicker1.getScene().getWindow();

        LocalDate today = LocalDate.now();
        LocalDate date1 = datePicker1.getValue();
        LocalDate date2 = datePicker2.getValue();

        if(date1 != null && date2 != null) {
            if(!date1.isAfter(today) && !date2.isAfter(today) && !date2.isBefore(date1)) {
                this.showBtn.setDisable(false);
            } else {
                this.showBtn.setDisable(true);
                this.statisticsTable.setDisable(true);
                this.exportBtn.setDisable(true);
            }
        }

    }

    @FXML
    private void handleExportButtonAction() {
        Stage stage = (Stage) exportBtn.getScene().getWindow();

        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV File (*.csv)", ".csv"));

        File file = fileChooser.showSaveDialog(stage);

        if(file != null) {
            boolean success = this.controller.exportVaccinationStatistics(centerIndex, file.getAbsolutePath(), datePicker1.getValue(), datePicker2.getValue());

            if(success) {
                createInfoAlert("Success", "The file was exported successfully to " + file.getAbsolutePath()).show();
            } else {
                createWarningAlert("Error", "The export failed").show();
            }
            stage.close();
        }
    }

    @FXML
    private void handleShowButtonAction() {
        Stage stage = (Stage) showBtn.getScene().getWindow();
        LocalDate date1 = datePicker1.getValue();
        LocalDate date2 = datePicker2.getValue();
        List<VaccineAdministrationCounter> vaccineAdministrationCounterList = this.controller.generateVaccineAdministrationCounterList(this.centerIndex, date1, date2);
        statisticsTable.setDisable(false);
        exportBtn.setDisable(false);

        statisticsTable.getItems().clear();

        this.c1.setCellValueFactory(new PropertyValueFactory<>("date"));
        this.c2.setCellValueFactory(new PropertyValueFactory<>("count"));

        for(VaccineAdministrationCounter vaccineAdministrationCounter : vaccineAdministrationCounterList) {
            statisticsTable.getItems().add(vaccineAdministrationCounter);
        }


    }



    private Alert createWarningAlert(String title, String headerText) {
        Alert alert = new Alert(Alert.AlertType.WARNING);

        alert.setTitle(title);
        alert.setHeaderText(headerText);

        return alert;
    }

    private Alert createInfoAlert(String title, String headerText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(title);
        alert.setHeaderText(headerText);

        return alert;
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



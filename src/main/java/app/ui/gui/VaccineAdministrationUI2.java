package app.ui.gui;

import app.controller.VaccineAdministrationController;
import app.controller.mappers.dto.VaccineDTO;
import app.controller.mappers.dto.VaccineListDTO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * @author Tomás Pereira <1210830@isep.ipp.pt>
 */
public class VaccineAdministrationUI2 implements Initializable {
    private VaccineAdministrationController controller;
    @FXML
    private Button addButton;
    @FXML
    private ChoiceBox<VaccineDTO> vaccineDropDown;

    public VaccineAdministrationUI2() {

    }

    public void initData(VaccineListDTO vaccineListDTO, VaccineAdministrationController controller) {
        this.controller = controller;
        for (int i = 0; i < vaccineListDTO.size(); i++) {
            vaccineDropDown.getItems().add(vaccineListDTO.get(i));
        }

    }

    public void handleAddButton() {
        VaccineDTO vaccineDTO = vaccineDropDown.getValue();
        Optional<ButtonType> confirmation;
        if (controller.isVaNull()) {
            confirmation = createConfirmationAlert("Confirm data", "Do you wish to administer \"" + vaccineDTO + "\" as dose no." + 1 + "?").showAndWait();
        } else {
            confirmation = createConfirmationAlert("Confirm data", "Do you wish to administer \"" + vaccineDTO + "\" as dose no." + controller.getNOfDose() + "?").showAndWait();
        }

        if (confirmation.get() == ButtonType.OK) {

            controller.addAdministration(vaccineDTO.getId());
            createInformationAlert("Success", "New administration added successfully!").showAndWait();

            //Close Stage
            Stage stage = (Stage) addButton.getScene().getWindow();
            stage.hide();
        }
    }

    private Alert createConfirmationAlert(String title, String headerText) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle(title);
        alert.setHeaderText(headerText);

        return alert;
    }

    private Alert createInformationAlert(String title, String headerText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(title);
        alert.setHeaderText(headerText);

        return alert;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

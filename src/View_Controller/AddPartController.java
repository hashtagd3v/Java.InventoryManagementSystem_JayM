package View_Controller;

import Model.InHousePart;
import Model.Inventory;
import Model.OutSourcedPart;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddPartController implements Initializable {
    public ToggleGroup addPartToggle;
    public Label addPartMachineCompanyLabel;
    public TextField addPartIdText;
    public TextField addPartNameText;
    public TextField addPartInvText;
    public TextField addPartPriceText;
    public TextField addPartMaxText;
    public TextField addPartMinText;
    public TextField addPartMachineCompanyText;
    public RadioButton inHouseRadioBtn;
    public RadioButton outSourcedRadioBtn;

    Stage stage;
    Parent scene;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        isInHouseOrOutSourcedClicked();

    }

    public void onActionAddPartSaveButton(ActionEvent actionEvent) throws IOException {

        try {
            // GET TEXT FROM TEXT FIELDS:

            int id = 0;
            String name = addPartNameText.getText();
            int stock = Integer.parseInt(addPartInvText.getText());
            double price = Double.parseDouble(addPartPriceText.getText());
            int max = Integer.parseInt(addPartMaxText.getText());
            int min = Integer.parseInt(addPartMinText.getText());


                if (min > max) {
                    AlertMessage.errorInPart(1);
                } else if (max < min) {
                    AlertMessage.errorInPart(2);
                } else {

                    // DETERMINE IF IN-HOUSE OR OUTSOURCED PART:

                    if (inHouseRadioBtn.isSelected()) {
                        int machineId = Integer.parseInt(addPartMachineCompanyText.getText());
                        Inventory.addPart(new InHousePart(id, name, price, stock, min, max, machineId));
                    } else if (outSourcedRadioBtn.isSelected()) {
                        String companyName = addPartMachineCompanyText.getText();
                        Inventory.addPart(new OutSourcedPart(id, name, price, stock, min, max, companyName));
                    }

                    // SWITCHES SCREEN BACK TO MAIN SCREEN:

                    stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.show();
                }
        } catch (NumberFormatException e) {
                AlertMessage.errorInPart(3);
        }


    }

    public void onActionAddPartCancelButton(ActionEvent actionEvent) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Required");
        alert.setHeaderText("All progress will not be saved.");
        alert.setContentText("Do you wish to proceed?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }

    }

    public void onActionInHouseRadioClicked(ActionEvent actionEvent) {

        isInHouseOrOutSourcedClicked();

    }

    public void onActionOutSourcedRadioClicked(ActionEvent actionEvent){

        isInHouseOrOutSourcedClicked();

        }

    // CHANGES LABEL WHETHER IN-HOUSE OR OUTSOURCED IS CLICKED:

    public void isInHouseOrOutSourcedClicked() {

        if (inHouseRadioBtn.isSelected()) {
            addPartMachineCompanyLabel.setText("Machine ID");
            addPartMachineCompanyText.setPromptText("Machine ID");
        } else if (outSourcedRadioBtn.isSelected()) {
            addPartMachineCompanyLabel.setText("Company Name");
            addPartMachineCompanyText.setPromptText("Comp Nm");
        }

    }

}


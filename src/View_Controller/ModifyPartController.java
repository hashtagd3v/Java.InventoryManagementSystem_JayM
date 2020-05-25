package View_Controller;

import Model.InHousePart;
import Model.Inventory;
import Model.OutSourcedPart;
import Model.Part;
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

public class ModifyPartController implements Initializable {
    public ToggleGroup modifyPartToggle;
    public Label modifyPartMachineCompanyLabel;
    public TextField modifyPartIdText;
    public TextField modifyPartNameText;
    public TextField modifyPartInvText;
    public TextField modifyPartPriceText;
    public TextField modifyPartMaxText;
    public TextField modifyPartMinText;
    public TextField modifyPartMachineCompanyText;
    public RadioButton inHouseRadioBtn;
    public RadioButton outSourcedRadioBtn;

    Stage stage;
    Parent scene;
    private int currentId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onActionModifyPartSaveButton(ActionEvent actionEvent) throws IOException {

        try {
            // GET TEXT FROM TEXT FIELDS:

            String name = modifyPartNameText.getText();
            int stock = Integer.parseInt(modifyPartInvText.getText());
            double price = Double.parseDouble(modifyPartPriceText.getText());
            int max = Integer.parseInt(modifyPartMaxText.getText());
            int min = Integer.parseInt(modifyPartMinText.getText());

            if (min > max) {
                AlertMessage.errorInPart(1);
            } else if (max < min) {
                AlertMessage.errorInPart(2);
            } else {

                // DETERMINE IF IN-HOUSE OR OUTSOURCED PART:

                Part oldPart = Inventory.selectPart(currentId);
                if (inHouseRadioBtn.isSelected()) {
                    int machineId = Integer.parseInt(modifyPartMachineCompanyText.getText());
                    Inventory.deletePart(oldPart);
                    Inventory.addPart(new InHousePart(currentId, name, price, stock, min, max, machineId));
                } else if (outSourcedRadioBtn.isSelected()) {
                    String companyName = modifyPartMachineCompanyText.getText();
                    Inventory.deletePart(oldPart);
                    Inventory.addPart(new OutSourcedPart(currentId, name, price, stock, min, max, companyName));
                }

                stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }
        } catch (NumberFormatException e) {
            AlertMessage.errorInPart(3);
        }

    }

    public void getPart(Part part) {
        currentId = part.getId();
        modifyPartNameText.setText(part.getName());
        modifyPartInvText.setText(String.valueOf(part.getStock()));
        modifyPartPriceText.setText(String.valueOf(part.getPrice()));
        modifyPartMaxText.setText(String.valueOf(part.getMax()));
        modifyPartMinText.setText(String.valueOf(part.getMin()));

        if (part instanceof InHousePart) {
            inHouseRadioBtn.setSelected(true);
            modifyPartMachineCompanyText.setText(String.valueOf(((InHousePart) part).getMachineId()));                   //---- CASTS PART INTO IN HOUSE PART
            modifyPartMachineCompanyLabel.setText("Machine ID: ");
        } else if (part instanceof  OutSourcedPart) {
            outSourcedRadioBtn.setSelected(true);
            modifyPartMachineCompanyText.setText(((OutSourcedPart) part).getCompanyName());                             //---- CASTS PART INTO OUTSOURCE PART
            modifyPartMachineCompanyLabel.setText("Company Name: ");
        }

    }

    public void onActionModifyPartCancelButton(ActionEvent actionEvent) throws IOException {

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

    public void onActionModifyPartInHouseRadioButton(ActionEvent actionEvent) {

        isInHouseOrOutSourcedClicked();

    }

    public void onActionModifyPartOutsourcedRadioButton(ActionEvent actionEvent) {

        isInHouseOrOutSourcedClicked();

    }

    //CHANGES LABEL WHETHER IN-HOUSE OR OUTSOURCED IS CLICKED:
    public void isInHouseOrOutSourcedClicked() {

        if (inHouseRadioBtn.isSelected()) {
            modifyPartMachineCompanyLabel.setText("Machine ID");
            modifyPartMachineCompanyText.setPromptText("Machine ID");
        } else if (outSourcedRadioBtn.isSelected()) {
            modifyPartMachineCompanyLabel.setText("Company Name");
            modifyPartMachineCompanyText.setPromptText("Comp Nm");
        }

    }

}

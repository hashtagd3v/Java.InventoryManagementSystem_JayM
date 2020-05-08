package View_Controller;

import Model.InHousePart;
import Model.Inventory;
import Model.OutSourcedPart;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //DEFAULT VIEW IS IN-HOUSE RADIO BUTTON CLICKED WITH TEXT MACHINE ID:
        modifyPartMachineCompanyLabel.setText("Machine ID");

        isInHouseOrOutSourcedClicked();

    }

    public void onActionModifyPartSaveButton(ActionEvent actionEvent) throws IOException {

        //GET TEXT FROM TEXT FIELDS:
        int id = 0;
        String name = modifyPartNameText.getText();
        int stock = Integer.parseInt(modifyPartInvText.getText());
        double price = Double.parseDouble(modifyPartPriceText.getText());
        int max = Integer.parseInt(modifyPartMaxText.getText());
        int min = Integer.parseInt(modifyPartMinText.getText());

        //DETERMINE IF IN-HOUSE OR OUTSOURCED PART:
        if (inHouseRadioBtn.isSelected()) {
            int machineId = Integer.parseInt(modifyPartMachineCompanyLabel.getText());
            Inventory.addPart(new InHousePart(id, name, price, stock, min, max, machineId));
        } else if (outSourcedRadioBtn.isSelected()){
            String companyName = modifyPartMachineCompanyLabel.getText();
            Inventory.addPart(new OutSourcedPart(id, name, price, stock, min, max, companyName));
        }

        //TODO DELETE OLD OBJECT SELECTED BEFORE SAVING NEW PART!!!

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    public void onActionModifyPartCancelButton(ActionEvent actionEvent) throws IOException {

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

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
            modifyPartMachineCompanyText.setText("Machine ID");
        } else if (outSourcedRadioBtn.isSelected()) {
            modifyPartMachineCompanyLabel.setText("Company Name");
            modifyPartMachineCompanyText.setText("Comp Nm");
        }

    }

}

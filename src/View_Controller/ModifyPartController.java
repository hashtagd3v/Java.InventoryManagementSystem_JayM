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
    public static int keepId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onActionModifyPartSaveButton(ActionEvent actionEvent) throws IOException {

        //GET TEXT FROM TEXT FIELDS:
        keepId = currentId;
        System.out.println("keepId in Save Handler: " + keepId); //-------------------   TEST
        String name = modifyPartNameText.getText();
        int stock = Integer.parseInt(modifyPartInvText.getText());
        double price = Double.parseDouble(modifyPartPriceText.getText());
        int max = Integer.parseInt(modifyPartMaxText.getText());
        int min = Integer.parseInt(modifyPartMinText.getText());

        //DETERMINE IF IN-HOUSE OR OUTSOURCED PART:
        try {
            if (inHouseRadioBtn.isSelected()) {
                int machineId = Integer.parseInt(modifyPartMachineCompanyText.getText());
                Inventory.addPart(new InHousePart(keepId, name, price, stock, min, max, machineId));
            } else if (outSourcedRadioBtn.isSelected()) {
                String companyName = modifyPartMachineCompanyText.getText();
                Inventory.addPart(new OutSourcedPart(keepId, name, price, stock, min, max, companyName));
            }
        } catch (NumberFormatException e) {
            //ignore exception due to parseInt()
        }

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    public void getPart(Part part) {
        currentId = part.getId();
        System.out.println("currentId in getPart(): " + currentId); //-------------------   TEST
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

        Inventory.deletePart(part);
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
            modifyPartMachineCompanyText.setPromptText("Machine ID");
        } else if (outSourcedRadioBtn.isSelected()) {
            modifyPartMachineCompanyLabel.setText("Company Name");
            modifyPartMachineCompanyText.setPromptText("Comp Nm");
        }

    }

}

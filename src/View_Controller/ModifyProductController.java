package View_Controller;

import Model.Part;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static Model.Inventory.*;

public class ModifyProductController implements Initializable {
    public TextField modifyProductIdText;
    public TextField modifyProductNameText;
    public TextField modifyProductInvText;
    public TextField modifyProductPriceText;
    public TextField modifyProductMaxText;
    public TextField modifyProductMinText;
    public TextField modifyProductSearchText;
    public TableView modifyProductTableViewTop;
    public TableColumn modifyProductTopPartIdCol;
    public TableColumn modifyProductTopPartNameCol;
    public TableColumn modifyProductTopInventoryCol;
    public TableColumn modifyProductTopPriceCol;
    public TableView modifyProductTableViewBottom;
    public TableColumn modifyProductBottomPartIdCol;
    public TableColumn modifyProductBottomPartNameCol;
    public TableColumn modifyProductBottomInventoryCol;
    public TableColumn modifyProductBottomPriceCol;

    Stage stage;
    Parent scene;
    private ObservableList<Part> chosenParts = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // DISPLAY ALL PARTS DATA ON TOP TABLE VIEW:
        modifyProductTopPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        modifyProductTopPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        modifyProductTopInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modifyProductTopPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        modifyProductTableViewTop.setItems(getAllParts());

        // DISPLAY ASSOCIATED PARTS ON BOTTOM TABLE VIEW WHEN ADD BUTTON IS CLICKED:
        modifyProductBottomPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        modifyProductBottomPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        modifyProductBottomInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modifyProductBottomPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        modifyProductTableViewBottom.setItems(chosenParts);

    }

    public void onActionModifyProductSearchButton(ActionEvent actionEvent) {

        String searchText = modifyProductSearchText.getText().trim().toUpperCase();

        if(searchText.isEmpty()) {
            modifyProductTableViewTop.setItems(getAllParts());
        }

        int counter;
        boolean pureTextOnly = false;
        for (counter = 0; counter < searchText.length(); counter++) {
            if (Character.isLetter(searchText.charAt(counter))) {
                pureTextOnly = true;
                break;
            } else {
                pureTextOnly = false;
            }
        }

        if(pureTextOnly) {
            lookupPart(searchText);
        } else {
            try {
                int valueOfText;
                valueOfText = Integer.parseInt(searchText);
                lookupPart(valueOfText);
            } catch (NumberFormatException e) {
                // ignore exception
            }
        }
        modifyProductTableViewTop.setItems(getAllFilteredParts());

        if (getAllFilteredParts().isEmpty()) {
            modifyProductTableViewTop.setItems(getAllParts());
        }

    }

    public void onActionModifyProductAddButton(ActionEvent actionEvent) {

        Part associatedPart = (Part) modifyProductTableViewTop.getSelectionModel().getSelectedItem();
        if(associatedPart == null) {
            return;
        } else {
            chosenParts.add(associatedPart);
        }

    }

    public void onActionModifyProductDeleteButton(ActionEvent actionEvent) {

        Part associatedPart = (Part) modifyProductTableViewBottom.getSelectionModel().getSelectedItem();
        if(associatedPart == null) {
            return;
        } else {
            chosenParts.remove(associatedPart);
        }

    }

    public void onActionModifyProductSaveButton(ActionEvent actionEvent) throws IOException {

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    public void onActionModifyProductCancelButton(ActionEvent actionEvent) throws IOException {

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    public void onMouseModifyPartSearch(MouseEvent mouseEvent) {

        getAllFilteredParts().clear();
        modifyProductTableViewTop.setItems(getAllParts());

    }

}

package View_Controller;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static Model.Inventory.*;
import static Model.Inventory.getAllFilteredParts;

public class AddProductController implements Initializable {
    public TextField addProductIdText;
    public TextField addProductNameText;
    public TextField addProductInvText;
    public TextField addProductPriceText;
    public TextField addProductMaxText;
    public TextField addProductMinText;
    public TextField addProductSearchText;
    public TableView addProductTableViewTop;
    public TableColumn addProductTopPartIdCol;
    public TableColumn addProductTopPartNameCol;
    public TableColumn addProductTopInventoryCol;
    public TableColumn addProductTopPriceCol;
    public TableView addProductTableViewBottom;
    public TableColumn addProductBottomPartIdCol;
    public TableColumn addProductBottomPartNameCol;
    public TableColumn addProductBottomInventoryCol;
    public TableColumn addProductBottomPriceCol;

    Stage stage;
    Parent scene;
    private ObservableList<Part> selectedParts = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // DISPLAY ALL PARTS DATA ON TOP TABLE VIEW:

        addProductTopPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        addProductTopPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addProductTopInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addProductTopPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        addProductTableViewTop.setItems(getAllParts());

        // DISPLAY ASSOCIATED PARTS ON BOTTOM TABLE VIEW WHEN ADD BUTTON IS CLICKED:

        addProductBottomPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        addProductBottomPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addProductBottomInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addProductBottomPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        addProductTableViewBottom.setItems(selectedParts);

    }

    public void onActionAddProductSearchButton(ActionEvent actionEvent) {

        String searchText = addProductSearchText.getText().trim().toUpperCase();

        lookupPart(searchText);
        if (getAllFilteredParts().size() == 0) {
            try {
                lookupPart(Integer.parseInt(searchText));
            } catch (NumberFormatException e) {
                // ignore exception due to parseInt()
            }
        }

        addProductTableViewTop.setItems(getAllFilteredParts());

        if (getAllFilteredParts().isEmpty()) {
            addProductTableViewTop.setItems(getAllParts());
        }

    }

    public void onActionAddProductAddButton(ActionEvent actionEvent) {

        //FIXME: CANNOT ADD SAME PART!

        Part associatedPart = (Part) addProductTableViewTop.getSelectionModel().getSelectedItem();
        if(associatedPart == null) {
            return;
        } else {
            selectedParts.add(associatedPart);
        }

    }

    public void onActionAddProductDeleteButton(ActionEvent actionEvent) {

        Alert alertDelete = new Alert(Alert.AlertType.CONFIRMATION);
        alertDelete.setTitle("Confirmation Required");
        alertDelete.setHeaderText("This part will be deleted as an associated part of the product.");
        alertDelete.setContentText("Do you wish to proceed?");

        Optional<ButtonType> result = alertDelete.showAndWait();
        if (result.get() == ButtonType.OK){
            Part associatedPart = (Part) addProductTableViewBottom.getSelectionModel().getSelectedItem();
            if(associatedPart == null) {
                return;
            } else {
                selectedParts.remove(associatedPart);
            }
        }

    }

    public void onActionAddProductSaveButton(ActionEvent actionEvent) throws IOException {

        // GET TEXT FROM FIELDS:
        try {
            int id = 0;
            String name = addProductNameText.getText();
            double price = Double.parseDouble(addProductPriceText.getText());
            int stock = Integer.parseInt(addProductInvText.getText());
            int min = Integer.parseInt(addProductMinText.getText());
            int max = Integer.parseInt(addProductMaxText.getText());

            if (min > max) {
                AlertMessage.errorInProduct(5);
            } else if (max < min) {
                AlertMessage.errorInProduct(6);
            } else if (selectedParts.isEmpty()) {
                AlertMessage.errorInProduct(1);
            } else {

                Product product = (new Product(id, name, price, stock, min, max));

                // ADD selectedParts list to getAllAssociatedParts() list in PRODUCT:

                for (int i = 0; i < selectedParts.size(); i++) {
                    product.addAssociatedPart(selectedParts.get(i));
                }

                Inventory.addProduct(product);

                stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();

            }
        } catch (NumberFormatException e) {
            AlertMessage.errorInProduct(3);
        }

    }

    public void onActionAddProductCancelButton(ActionEvent actionEvent) throws IOException {

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

    public void onMouseProductSearchText(MouseEvent mouseEvent) {

        getAllFilteredParts().clear();
        addProductTableViewTop.setItems(getAllParts());

    }

}

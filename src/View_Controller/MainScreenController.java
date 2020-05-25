package View_Controller;

import Model.Part;
import Model.Product;
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

public class MainScreenController implements Initializable {
    public TextField partSearchText;
    public TableView<Part> partTableView;
    public TableColumn<Part, Integer> partIdColumn;
    public TableColumn<Part, String> partNameColumn;
    public TableColumn<Part, Integer> partInventoryColumn;
    public TableColumn<Part, Double> partPriceColumn;
    public TextField productSearchText;
    public TableView<Product> productTableView;
    public TableColumn<Product, Integer> productIdColumn;
    public TableColumn<Product, String> productNameColumn;
    public TableColumn<Product, Integer> productInventoryColumn;
    public TableColumn<Product, Double> productPriceColumn;

    Stage stage;
    Parent scene;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        partTableView.setItems(getAllParts());
        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        productTableView.setItems(getAllProducts());
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    public void onActionSearchPartButton(ActionEvent actionEvent) {

        String searchText = partSearchText.getText().trim().toUpperCase();

        lookupPart(searchText);
        if (getAllFilteredParts().size() == 0) {
            try {
                lookupPart(Integer.parseInt(searchText));
            } catch (NumberFormatException e) {
                // ignore exception due to parseInt()
            }
        }

        partTableView.setItems(getAllFilteredParts());

        if (getAllFilteredParts().isEmpty()) {
            partTableView.setItems(getAllParts());
        }

    }

    public void onActionPartAddButton(ActionEvent actionEvent) throws IOException {

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/AddPartScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    public void onActionPartModifyButton(ActionEvent actionEvent) throws IOException {

        // USED TO TRANSFER DATA; CANNOT JUST SWITCH SCREENS - GET CONTROLLER:

        Part selectedPart = partTableView.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            AlertMessage.errorInPart(4);
        } else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View_Controller/ModifyPartScreen.fxml"));
            loader.load();

            ModifyPartController MODController = loader.getController();
            MODController.getPart(selectedPart);

            stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }

    }

    public void onActionPartDeleteButton(ActionEvent actionEvent) {

        Alert alertDelete = new Alert(Alert.AlertType.CONFIRMATION);
        alertDelete.setTitle("Confirmation Required");
        alertDelete.setHeaderText("This part will be deleted from the inventory.");
        alertDelete.setContentText("Do you wish to proceed?");

        Optional<ButtonType> result = alertDelete.showAndWait();
        if (result.get() == ButtonType.OK){
            deletePart(partTableView.getSelectionModel().getSelectedItem());
        }

    }

    public void onActionSearchProductButton(ActionEvent actionEvent) {

        String searchText = productSearchText.getText().trim().toUpperCase();

        lookupProduct(searchText);
        if (getAllFilteredProducts().size() == 0) {
            try {
                lookupProduct(Integer.parseInt(searchText));
            } catch (NumberFormatException e) {
                // ignore exception due to parseInt()
            }
        }

        productTableView.setItems(getAllFilteredProducts());

        if (getAllFilteredProducts().isEmpty()) {
            productTableView.setItems(getAllProducts());
        }

    }

    public void onActionProductAddButton(ActionEvent actionEvent) throws IOException {

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/AddProductScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    public void onActionProductModifyButton(ActionEvent actionEvent) throws IOException {

        // USED TO TRANSFER DATA; CANNOT JUST SWITCH SCREENS - GET CONTROLLER:

        Product selectedProduct = productTableView.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            AlertMessage.errorInProduct(4);
        } else {
            FXMLLoader productLoader = new FXMLLoader();
            productLoader.setLocation(getClass().getResource("/View_Controller/ModifyProductScreen.fxml"));
            productLoader.load();

            ModifyProductController MPController = productLoader.getController();
            MPController.getProduct(selectedProduct);

            stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            Parent scene = productLoader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }

    }

    public void onActionProductDeleteButton(ActionEvent actionEvent) {

        Alert alertDelete = new Alert(Alert.AlertType.CONFIRMATION);
        alertDelete.setTitle("Confirmation Required");
        alertDelete.setHeaderText("This part will be deleted from the inventory.");
        alertDelete.setContentText("Do you wish to proceed?");

        Optional<ButtonType> result = alertDelete.showAndWait();
        if (result.get() == ButtonType.OK){
            deleteProduct(productTableView.getSelectionModel().getSelectedItem());
        }

    }

    public void onActionExitButton(ActionEvent actionEvent) {

        System.exit(0);

    }

    public void onActionPartText(MouseEvent actionEvent) {

        getAllFilteredParts().clear();
        partTableView.setItems(getAllParts());

    }

    public void onActionProductText(MouseEvent actionEvent) {

        getAllFilteredProducts().clear();
        productTableView.setItems(getAllProducts());

    }

}

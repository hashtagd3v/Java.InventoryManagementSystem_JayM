package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

        //TODO: REMOVE TESTED CODES HERE/CLEAN UP CODE.
        //TODO: CONTINUE WORKING ON SEARCH, MODIFY AND DELETE.

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

        //TODO WORK ON SELECTING AN ITEM IN TABLE VIEWS TO DELETE/MODIFY

//        SAMPLE CODE:
//        partTableView.getSelectionModel().select(selectPart(1));
//        partTableView.getSelectionModel().select(Inventory.lookupPart(2));                                    //FIXME
//        productTableView.getSelectionModel().select(Inventory.lookupProduct(1));                              //FIXME


    }

    public void onActionSearchPartButton(ActionEvent actionEvent) {

        String searchText = partSearchText.getText().toString().trim().toUpperCase();

        if(searchText.isEmpty()) {
            partTableView.setItems(getAllParts());
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
                //ignore exception
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

        //TODO: WORK ON MODIFYING PARTS/PRODUCTS
        selectPart(partTableView.getSelectionModel().getSelectedIndex()); //returns Part type!


        //FIXME:
        Inventory.updatePart(partTableView.getSelectionModel().getSelectedIndex(), partTableView.getSelectionModel().getSelectedItem());


        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/ModifyPartScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    public void onActionPartDeleteButton(ActionEvent actionEvent) {

        deletePart(partTableView.getSelectionModel().getSelectedItem());

    }

    public void onActionSearchProductButton(ActionEvent actionEvent) {

        String searchText = productSearchText.getText().toString().trim().toUpperCase();

        if(searchText.isEmpty()) {
            productTableView.setItems(getAllProducts());
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
            lookupProduct(searchText);
        } else {
            try {
                int valueOfText;
                valueOfText = Integer.parseInt(searchText); //FIXME!
                lookupProduct(valueOfText);
            } catch (NumberFormatException e) {
                //ignore exception
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

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/ModifyProductScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    public void onActionProductDeleteButton(ActionEvent actionEvent) {

        deleteProduct(productTableView.getSelectionModel().getSelectedItem());

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

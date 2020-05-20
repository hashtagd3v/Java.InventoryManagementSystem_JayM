package View_Controller;

import Model.Inventory;
import Model.Product;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static Model.Inventory.getAllParts;
import static Model.Inventory.getAllProducts;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // DISPLAY ALL PARTS DATA ON TOP TABLE VIEW:
        addProductTopPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        addProductTopPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addProductTopInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addProductTopPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        addProductTableViewTop.setItems(getAllProducts());

    }

    public void onActionAddProductSearchButton(ActionEvent actionEvent) {
    }

    public void onActionAddProductAddButton(ActionEvent actionEvent) {

        // TODO: WORK ON SELECTING PARTS FROM ALL PRODUCTS TABLE VIEW AND ADD TO ASSOCIATED PARTS TABLE VIEW.


    }

    public void onActionAddProductDeleteButton(ActionEvent actionEvent) {
    }

    public void onActionAddProductSaveButton(ActionEvent actionEvent) throws IOException {

        // GET TEXT FROM FIELDS:
        int id = 0;
        String name = addProductNameText.getText();
        double price = Double.parseDouble(addProductPriceText.getText());
        int stock = Integer.parseInt(addProductInvText.getText());
        int min = Integer.parseInt(addProductMinText.getText());
        int max = Integer.parseInt(addProductMaxText.getText());

        // ADD/CREATE NEW PRODUCT:
        Inventory.addProduct(new Product(id, name, price, stock, min, max));

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    public void onActionAddProductCancelButton(ActionEvent actionEvent) throws IOException {

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

}

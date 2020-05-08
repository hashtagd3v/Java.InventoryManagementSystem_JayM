package View_Controller;

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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onActionModifyProductSearchButton(ActionEvent actionEvent) {
    }

    public void onActionModifyProductAddButton(ActionEvent actionEvent) {
    }

    public void onActionModifyProductDeleteButton(ActionEvent actionEvent) {
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
}

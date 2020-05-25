package View_Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class AlertMessage {

    public static void errorInPart(int code) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Cannot proceed!");
        switch (code) {
            case 1: {
                alert.setContentText("Inventory min value cannot be greater than max value.");
                break;
            }
            case 2: {
                alert.setContentText("Inventory max value cannot be less than min value.");
                break;
            }
            case 3: {
                alert.setContentText("Please enter all required valid inputs.");
                break;
            }
            case 4: {
                alert.setContentText("Please select a part to modify.");
                break;
            }
            default:
                break;
        }


        alert.showAndWait();

    }

    public static void errorInProduct(int code) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Cannot proceed!");
        switch (code) {
            case 1: {
                alert.setContentText("Please add at least one associated part to the product.");
                break;
            }
            case 2: {
                alert.setContentText("Price of product is more than total price of parts. Please change product price.");
                break;
            }
            case 3: {
                alert.setContentText("Please enter all required valid inputs.");
                break;
            }
            case 4: {
                alert.setContentText("Please select a product to modify.");
                break;
            }
            case 5: {
                alert.setContentText("Inventory min value cannot be greater than max value.");
            }
            case 6: {
                alert.setContentText("Inventory max value cannot be less than min value.");
            }
            default:
                break;
        }


        alert.showAndWait();

    }

}

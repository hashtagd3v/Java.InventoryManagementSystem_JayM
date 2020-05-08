package Main;

import Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicInteger;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));
        primaryStage.setTitle("Jay Michalek Inventory System");
        primaryStage.setScene(new Scene(root, 950, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {

        //Parts:
        InHousePart part1 = new InHousePart(1, "USB cable", 3.99, 5, 1, 100, 3133);
        OutSourcedPart part2 = new OutSourcedPart(2, "Antenna", 2.00, 3, 1, 50, "MicroCenter");
        OutSourcedPart part3 = new OutSourcedPart(3, "TV Remote", 29.99, 10, 5, 30, "Samsung");

        Inventory.addPart(part1);
        Inventory.addPart(part2);
        Inventory.addPart(part3);

        //Products:
        Product product1 = new Product(1, "TV", 399.99, 10, 5, 25);
        Product product2 = new Product(2, "Sound Bar", 299.99, 5, 5, 30);
        Product product3 = new Product(3, "SubWoofer", 199.99, 8, 3, 15);
        Inventory.addProduct(product1);
        Inventory.addProduct(product2);
        Inventory.addProduct(product3);

        launch(args);
    }
}


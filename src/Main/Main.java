package Main;

import Model.InHousePart;
import Model.Inventory;
import Model.OutSourcedPart;
import Model.Product;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));
        primaryStage.setTitle("Jay M Inventory System");
        primaryStage.setScene(new Scene(root, 950, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {

        // Declare Test Parts:

        InHousePart part1 = new InHousePart(0, "Part A", 3.99, 5, 1, 100, 3133);
        OutSourcedPart part2 = new OutSourcedPart(0, "Part B", 2.00, 3, 1, 50, "MicroCenter");
        OutSourcedPart part3 = new OutSourcedPart(0, "Part C", 29.99, 10, 5, 30, "Samsung");

        Inventory.addPart(part1);
        Inventory.addPart(part2);
        Inventory.addPart(part3);

        //Declare Test Products:

        Product product1 = new Product(0, "Product A", 399.99, 10, 5, 25);
        Product product2 = new Product(0, "Product B", 299.99, 5, 5, 30);
        Product product3 = new Product(0, "Product C", 199.99, 8, 3, 15);
        Inventory.addProduct(product1);
        Inventory.addProduct(product2);
        Inventory.addProduct(product3);

        launch(args);
    }
}


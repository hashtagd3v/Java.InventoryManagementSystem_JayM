package Model;

import View_Controller.AddProductController;
import View_Controller.ModifyPartController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class Product {

    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    private static AtomicInteger autoId = new AtomicInteger(0);

    public Product(int id, String name, double price, int stock, int min, int max) {
        setId(id);
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
            if(id <= 0) {
                this.id = autoId.incrementAndGet();
            } else {
                this.id = id;
            }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void addAssociatedPart(Part part) {

        associatedParts.add(part);
        return;

    }

    public boolean deleteAssociatedPart(Part selectedPart) {

        //TODO change return statement
        return false;
    }

    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

}

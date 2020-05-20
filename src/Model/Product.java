package Model;

import javafx.collections.ObservableList;

import java.util.concurrent.atomic.AtomicInteger;

public class Product {

    private ObservableList<Part> associatedParts;
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    private static AtomicInteger autoId = new AtomicInteger(0);

    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = autoId.incrementAndGet();
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
        this.id = id;
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

    }

    public boolean deleteAssociatedPart(Part selectedPart) {

        //TODO change return statement
        return false;
    }

    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

}

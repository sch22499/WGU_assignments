package sample.Model;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Exceptions.AddModifyExceptions;

import java.util.ArrayList;
import java.util.List;

public class Product {

    private  ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;


    public Product( String name, double price, int stock, int min, int max){
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;

    }

    public Product(){

    }

    private double getPartCost(){
        double totalPrice = associatedParts.stream().mapToDouble(Part::getPrice).sum();
        return totalPrice;

    }
    public void removeAssociatedParts(){
        associatedParts = FXCollections.observableArrayList();
    }


    public boolean isValid() throws AddModifyExceptions {
        if (getName().equals("")) {
            throw new AddModifyExceptions("The name field cannot be empty.");
        }

        if(getAllAssociatedParts().isEmpty()){
            throw new AddModifyExceptions("All products must have at least one associated part");
        }

        if(getPrice() < getPartCost()){
            throw new AddModifyExceptions("The price of the product cannot be less than the total cost of the parts");
        }

        if (getStock() < 0) {
            throw new AddModifyExceptions("The current inventory must be greater than 0.");
        }

        if (getPrice() < 0) {
            throw new AddModifyExceptions("The price must be greater than $0");
        }

        if (getMin() < 0) {
            throw new AddModifyExceptions("The minimum inventory must be greater than 0.");
        }

        if (getMin() > getMax()) {
            throw new AddModifyExceptions("The minimum inventory must be less than the maximum.");
        }

        if (getStock() < getMin() || getStock() > getMax()) {
            throw new AddModifyExceptions("The current inventory must be between the minimum and maximum inventory.");
        }

        return true;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) { this.stock = stock; }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getId() {
        return id;
    }

    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public String getName() {
        return name;
    }

    public void addAssociatedPart(Part part){
        associatedParts.add(part);
    }

    public void deleteAssociatedPart(Part part){
        associatedParts.remove(part);
    }

    public ObservableList<Part> getAllAssociatedParts(){
        return associatedParts;
    }
}

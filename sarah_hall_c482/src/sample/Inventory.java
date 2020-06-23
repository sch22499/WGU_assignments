package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Inventory {

    private ObservableList<Part> allParts;

    private ObservableList<Product> allProducts;


    public Inventory(){

        allParts = FXCollections.observableList(populateMockParts());
        allProducts = FXCollections.observableList(populateMockProducts());



    }

    public List<Product> populateMockProducts(){
        List<Product> product = new ArrayList<>();


        product.add(new Product(1, "Product 1", 5.00, 5, 1, 10));
        product.add(new Product(2, "Product 2", 10.00, 4, 1, 10));
        product.add(new Product(3, "Product 3", 15.00, 5, 1, 10));

        return product;
    }
//int id, String name, double price, int stock, int min, int max
    public List<Part> populateMockParts(){
        List<Part> parts = new ArrayList<>();

        parts.add(new InHouse(1, "Part 1", 5000, 5, 1, 10, 1));
        parts.add(new Outsourced(2, "Part 2", 5430, 4, 1, 10, "Sarah Is Cool"));
        parts.add(new InHouse(3, "Part 3", 5066, 5, 1, 10, 15));

        return parts;

    }

    //Setters/getters for products

    public void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }

    public void lookUpProduct(int productId){

    }

    public void updateProduct(int index, Product selectedProduct ){

    }

    public void deleteProduct(Product selectedProduct){

    }

    //methods for parts

    public void addPart(Part newPart)
    {
        allParts.add(newPart);
    }



    public Part lookUpPart(int partId){
        ///NOTE test sadPath testing???
       return allParts.stream().filter(part ->
               part.getId() == partId
       ).findFirst().get();
    }



    public void updatePart(int index, Part selectedPart ){

    }

    public void deletePart(Part selectedPart){

    }


    public ObservableList<Part> getAllParts(){
        return allParts;
    }

    public ObservableList<Product> getAllProducts(){
        return allProducts;
    }
}

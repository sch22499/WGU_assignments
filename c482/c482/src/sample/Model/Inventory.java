package sample.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    private static int partIDCount = 0;
    private static int productIDCount = 0;

    public Inventory(){

    }



    public static int getProductIDCount() {
        productIDCount++;
        return productIDCount;
    }


    public static boolean validatePartDelete(Part part) {
        boolean isFound = false;

        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getAllAssociatedParts().contains(part)) {
                isFound = true;
            }
        }
        return isFound;
    }


    public static int getPartIDCount() {
        partIDCount++;
        return partIDCount;
    }

    //Setters/getters for products

    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);

    }

    public static Product lookUpProduct(int productId){
        return allProducts.stream().filter(product ->
                product.getId() == productId
        ).findFirst().get();
    }

    public static void updateProduct(Product selectedProduct ){
        allProducts.set(selectedProduct.getId() - 1, selectedProduct);
    }

    public static void deleteProduct(Product selectedProduct){
        allProducts.remove(selectedProduct);
    }

    //methods for parts

    public static void addPart(Part newPart)
    {
        allParts.add(newPart);
    }



    public static Part lookUpPart(int partId){
        return allParts.stream().filter(part ->
                part.getId() == partId
        ).findFirst().get();
    }



    public static void updatePart(Part selectedPart ){
        allParts.set(selectedPart.getId() - 1, selectedPart);
    }

    public static void deletePart(Part selectedPart){
        allParts.remove(selectedPart);
    }


    public static ObservableList<Part> getAllParts(){
        return allParts;
    }

    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }
}

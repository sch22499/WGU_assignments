package sample.Controller;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;

import javafx.scene.control.*;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;
import sample.Model.Part;
import sample.Model.Product;
import sample.Model.Inventory;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private static Part modifiedPart;

    private static Product modifiedProduct;

    /*
        TableViews for Parts and Products
     */

    @FXML
    private TableView<Part> partsTableView;
    @FXML
    private TableView<Product> productsTableView;

    /*
        Search fields for product and parts TableViews
     */


    @FXML
    private TextField partsSearchField;
    @FXML
    private TextField productsSearchField;


    /*
        Columns for the Parts TableView
     */

    @FXML
    private TableColumn<Part, Integer> partIdColumn;

    @FXML
    private TableColumn<Part, String> partNameColumn;

    @FXML
    private TableColumn<Part, Integer> partInventoryLvlColumn;

    @FXML
    private TableColumn<Part, Double> partPriceColumn;

    /*
        Columns for the Products TableView
     */

    @FXML
    private TableColumn<Product, Integer> productIdColumn;

    @FXML
    private TableColumn<Product, String> productNameColumn;

    @FXML
    private TableColumn<Product, Integer> productInventoryLvlColumn;

    @FXML
    private TableColumn<Product, Double> productPriceColumn;


    // Default constructor
    public MainController(){

    }

    public void setMainApp(Main mainApp) {
        populatePartsTable();
        populateProductsTable();
    }



    public static Part getModifiedPart(){
        return modifiedPart;
    }
    public void setModifiedPart(Part part){
        this.modifiedPart = part;
    }

    public static Product getModifiedProduct(){
        return modifiedProduct;
    }
    public void setModifiedProduct(Product product){
        this.modifiedProduct = product;
    }



    @FXML
    private void onAddPart(Event event) throws IOException{
        showPartsMenu(event);
    }


    @FXML
    private void onModifyPart(Event event) throws IOException{
        modifiedPart = partsTableView.getSelectionModel().getSelectedItem();
        if(modifiedPart != null) {
            setModifiedPart(modifiedPart);
            showPartsMenu(event);
        }
    }

    @FXML
    private void onPartDelete(Event event) throws IOException{
        Part part = partsTableView.getSelectionModel().getSelectedItem();
        if(Inventory.validatePartDelete(part) == false){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initModality(Modality.NONE);
            alert.setTitle("Part Delete");
            alert.setHeaderText("Confirm deletion");
            alert.setContentText("Are you sure you want to Delete " + part.getName() + " ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Inventory.deletePart(part);
            }
        }

        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Part Deletion Error!");
            alert.setHeaderText("This part is associated with a product");
            alert.setContentText("Please remove it before deleting");
            alert.showAndWait();
        }

    }

    @FXML
    private void onPartsSearch(Event event) throws IOException{
        String searchFilter = partsSearchField.getText();
        Part filteredPart = Inventory.lookUpPart(Integer.parseInt(searchFilter));

        if(filteredPart != null){
            ObservableList<Part> filteredParts = FXCollections.observableArrayList();
            filteredParts.add(filteredPart);
            partsTableView.setItems(filteredParts);
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Search Error");
            alert.setHeaderText("Part not found");
            alert.setContentText("The search term entered does not match any part!");
            alert.showAndWait();
        }
    }

    private void showPartsMenu(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/sample/controller/partsMenuView.fxml"));
        AnchorPane pane = loader.load();
        Scene scene = new Scene(pane);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }


    @FXML
    private void onAddProduct(Event event) throws IOException{
        showProductsMenu(event);
    }

    @FXML
    private void onModifyProduct(Event event) throws IOException{
        modifiedProduct = productsTableView.getSelectionModel().getSelectedItem();

        if(modifiedProduct != null) {
            setModifiedProduct(modifiedProduct);
            showProductsMenu(event);
        }
    }

    @FXML
    private void onDeleteProduct(Event event) throws IOException{

        Product product = productsTableView.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Part Delete");
        alert.setHeaderText("Confirm deletion");
        alert.setContentText("Are you sure you want to delete " + product.getName() + " ?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            Inventory.deleteProduct(product);
        }

    }

    @FXML
    private void onExit(Event event) throws IOException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Part Delete");
        alert.setHeaderText("Confirm deletion");
        alert.setContentText("Are you sure you want to exit?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            System.exit(0);
        }
    }

    @FXML
    private void onSearchProduct(Event event) throws IOException{
        String searchFilter = productsSearchField.getText();
        Product filteredProduct = Inventory.lookUpProduct(Integer.parseInt(searchFilter));

        if(filteredProduct != null){
            ObservableList<Product> filteredProducts = FXCollections.observableArrayList();
            filteredProducts.add(filteredProduct);
            productsTableView.setItems(filteredProducts);
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Search Error");
            alert.setHeaderText("Part not found");
            alert.setContentText("The search term entered does not match any part!");
            alert.showAndWait();
        }
    }

    private  void showProductsMenu(Event event) throws IOException{
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/sample/controller/productMenuController.fxml"));
        AnchorPane pane = loader.load();
        Scene scene = new Scene(pane);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }

    private void populateProductsTable(){
        productIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        productNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        productInventoryLvlColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getStock()).asObject());
        productPriceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());

        productsTableView.setItems(Inventory.getAllProducts());


    }

    private void populatePartsTable(){

        partIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        partNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        partInventoryLvlColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getStock()).asObject());
        partPriceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());
        partsTableView.setItems(Inventory.getAllParts());


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setModifiedPart(null);
        setModifiedProduct(null);
        populatePartsTable();
        populateProductsTable();

    }
}

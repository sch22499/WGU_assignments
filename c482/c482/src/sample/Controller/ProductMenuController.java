package sample.Controller;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
//import jdk.jfr.Event;
import sample.Exceptions.AddModifyExceptions;
import sample.Main;
import sample.Model.Inventory;
import sample.Model.Part;


import javafx.event.ActionEvent;
import javafx.event.Event;

import java.util.Optional;
import java.util.ResourceBundle;

import sample.Model.Product;

import java.io.IOException;
import java.net.URL;


import static sample.Controller.MainController.getModifiedProduct;

public class ProductMenuController implements Initializable {


    private Product modifiedProduct;


    private static ObservableList<Part> parts = FXCollections.observableArrayList();


    @FXML
    private Label titleLabel;

    @FXML
    private TextField productIdTxtField;

    @FXML
    private TextField productNameTxtField;

    @FXML
    private TextField productInvLvlTxtField;

    @FXML
    private TextField productPriceTxtField;

    @FXML
    private TextField productMaxTxtField;

    @FXML
    private TextField productMinTxtField;

    @FXML
    private TextField partsSearchTxtField;



    /*
            Columns for the Addable Parts TableView
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
    Columns for the Parts TableView
     */

    @FXML
    private TableColumn<Part, Integer> associatedPartIdColumn;

    @FXML
    private TableColumn<Part, String> associatedPartNameColumn;

    @FXML
    private TableColumn<Part, Integer> associatedPartInventoryLvlColumn;

    @FXML
    private TableColumn<Part, Double> associatedPartPriceColumn;


    /*
        TableViews for Parts
     */

    @FXML
    private TableView<Part> partsTableView;
    @FXML
    private TableView<Part> associatedPartsTableView;


    public ProductMenuController(){
        this.modifiedProduct = getModifiedProduct();
    }

    @FXML
    private void onAddPart(Event event){
        Part part  = partsTableView.getSelectionModel().getSelectedItem();
        if(!parts.contains(part) && part != null) {
            parts.add(part);
        }
        populateAssociatedPartsTable();

    }

    @FXML
    private void onCancel(Event event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Part Delete");
        alert.setHeaderText("Confirm deletion");
        alert.setContentText("Are you sure you want to cancel? ");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/sample/controller/mainController.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }
    }

    @FXML
    private  void onSearch(Event event){
        String searchFilter = partsSearchTxtField.getText();
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


    @FXML
    private void onSave(Event event){

        Product newProduct;

            double productPrice = Double.parseDouble(productPriceTxtField.getText());
            int productInvLvl = Integer.parseInt(productInvLvlTxtField.getText());
            int productMax = Integer.parseInt(productMaxTxtField.getText());
            int productMin = Integer.parseInt(productMinTxtField.getText());
            String productName = productNameTxtField.getText();

            newProduct = new Product(
                    productName,
                    productPrice,
                    productInvLvl,
                    productMin,
                    productMax);

            if (modifiedProduct != null) {
                modifiedProduct.removeAssociatedParts();
            }

            parts.forEach((part -> {
                newProduct.addAssociatedPart(part);
            }));

        try{
            newProduct.isValid();



            if(modifiedProduct == null){
                newProduct.setId(Inventory.getProductIDCount());
                Inventory.addProduct(newProduct);
            }else{
                newProduct.setId(modifiedProduct.getId());
                Inventory.updateProduct(newProduct);
            }

            Parent loader = FXMLLoader.load(getClass().getResource("mainController.fxml"));
            Scene scene = new Scene(loader);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
            modifiedProduct = null;


        }  catch (AddModifyExceptions | IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ValidationError");
            alert.setHeaderText("Product not valid");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }

    @FXML
    private void onDeleteAssociatedPart(Event event){

        if (parts.size() > 1) {
            Part part = associatedPartsTableView.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initModality(Modality.NONE);
            alert.setTitle("Part Delete");
            alert.setHeaderText("Confirm deletion");
            alert.setContentText("Are you sure you want to disassociate " + part.getName() + " ?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                parts.remove(part);
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Part Deletion Error!");
            alert.setHeaderText("Product requires one part!");
            alert.setContentText("This product must have at least one part.");
            alert.showAndWait();
        }

    }




    private void populatePartsTable(){
        partsTableView.setItems(Inventory.getAllParts());
    }

    private void populateAssociatedPartsTable(){
        if(modifiedProduct == null){
            associatedPartsTableView.setItems(parts);
        }else{
            associatedPartsTableView.setItems(modifiedProduct.getAllAssociatedParts());
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(modifiedProduct == null){
            parts = FXCollections.observableArrayList();
            titleLabel.setText("Add Product");
            productIdTxtField.setText("AutoGen - Disabled");
        }else{
            System.out.println(modifiedProduct.getAllAssociatedParts());

           parts = modifiedProduct.getAllAssociatedParts();

            titleLabel.setText("Modify Product");

            productIdTxtField.setText("AutoGen - Disabled");
            productNameTxtField.setText(modifiedProduct.getName());
            productInvLvlTxtField.setText(Integer.toString(modifiedProduct.getStock()));
            productMaxTxtField.setText(Integer.toString(modifiedProduct.getMax()));
            productMinTxtField.setText(Integer.toString(modifiedProduct.getMin()));
            productPriceTxtField.setText(Double.toString(modifiedProduct.getPrice()));
        }

        associatedPartPriceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());
        associatedPartIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        associatedPartNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        associatedPartInventoryLvlColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getStock()).asObject());

        partPriceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());
        partIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        partNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        partInventoryLvlColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getStock()).asObject());


        populatePartsTable();
        populateAssociatedPartsTable();

    }




}


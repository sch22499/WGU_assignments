package sample;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.beans.EventHandler;
import java.io.IOException;
import java.util.EventObject;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainContainer {

    private String labelText;

    private Inventory inventory;

    private String searchFilter;

    public TextField search;

    private boolean isProducts;

    public MainContainer(String labelText, boolean isProducts, Inventory inventory){
        this.inventory = inventory;
        this.isProducts = isProducts;
        this.labelText = labelText;
    }

    public VBox renderContainer(){
        VBox container = new VBox();
        container.getChildren().addAll(renderHeader(), renderBody(), renderFooter());

        return container;
    }

    private HBox renderHeader(){
        Label headerLabel = new Label(labelText);
        HBox header = new HBox();
        TextField search = new TextField();


        header.setSpacing(10);
        header.setPadding(new Insets(10));
        header.getChildren().addAll(headerLabel, renderSearchButton());
        return header;
    }


    private HBox renderBody(){

        HBox body = new HBox();
        body.setSpacing(10);
        body.setPadding(new Insets(10));

        String label;

        if( isProducts == false) body.getChildren().addAll(partsTableView());
        else body.getChildren().addAll(productTableView());

        return body;
    }

    private TableView productTableView() {
        TableView<Product> productTableView = new TableView<Product>();

        ObservableList allProducts = inventory.getAllProducts();

        productTableView.setItems(allProducts);

        TableColumn<Product, String> productIdCol = new TableColumn<Product, String>("Product ID");
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Product, String> productNameCol = new TableColumn<Product, String>("Product Name");
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Product, String> inventoryCol = new TableColumn<>("Inventory Level");
        inventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));

        //NOTE I think we need to work with the stock thing because reasons

        TableColumn<Product, String> priceCol = new TableColumn<>("Price per unit");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        FilteredList<Product> filteredList = new FilteredList<>(allProducts, b -> true);

        productTableView.getColumns().addAll(productIdCol,productNameCol, inventoryCol, priceCol);


//        search.textProperty().addListener((observable, oldValue, newValue)->{
//            filteredList.setPredicate(product -> {
//                if (newValue == null || newValue.isEmpty())
//                    return true;
//
//                String lowerCaseFilter = newValue.toLowerCase();
//
//                if(product.getName().toLowerCase().indexOf(lowerCaseFilter) != 1)
//                    return true;
//                else
//                    return false;
//            });
    //    });

        SortedList<Product> sortedList = new SortedList<>(filteredList);

        sortedList.comparatorProperty().bind(productTableView.comparatorProperty());

        productTableView.setItems(sortedList);

        return productTableView;


    }



    private TableView partsTableView(){
        TableView<Part> partsTableView = new TableView<Part>();

        ObservableList allParts = inventory.getAllParts();

        partsTableView.setItems(allParts);

        TableColumn<Part, String> partIdCol = new TableColumn<Part, String>("Part ID");
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Part, String> partNameCol = new TableColumn<Part, String>("Part Name");
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Part, String> inventoryCol = new TableColumn<>("Inventory Level");
        inventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));

        //NOTE I think we need to work with the stock thing because reasons

        TableColumn<Part, String> priceCol = new TableColumn<>("Price per unit");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

      //  partsTableView.

        partsTableView.getColumns().addAll(partIdCol,partNameCol, inventoryCol, priceCol);

        return partsTableView;
    }

    private HBox renderFooter(){
        HBox footer = new HBox();
        footer.setSpacing(10);
        footer.setPadding(new Insets(10));

        Button addBtn = renderAddButton(isProducts, "Add");
        Button modifyBtn = renderModifyButton(isProducts, "modify");
        Button deleteBtn = renderButton("Delete");
        footer.getChildren().addAll(addBtn,modifyBtn,deleteBtn);

        return footer;
    }

    private Button renderButton(String thing){
        return new Button();
    }

    private Button renderModifyButton(boolean isProducts, String btnLabel){
        Button btn = new Button();
        btn.setText("Modify");


        btn.setOnAction((new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent actionEvent) {
                Stage stage = new Stage();
                String label;
                Scene scene;

                // ADD PRODUCT PAGES
                if(isProducts == false) {
                    label = "Modify Part";
                    scene = new Scene(new PartMenu().renderAddMenu());

                }
                else {
                    label = "Modify Product";
                    scene = new Scene(new PartMenu().renderAddMenu());

                }

                stage.setTitle(label);

                stage.setScene(scene);
                stage.show();
            }
        }));

        return btn;
    }

    private Button renderSearchButton( ) {
        String searchFilter1 = "sarah";
        Button btn = new Button();
        btn.setText("Search");
        btn.setOnAction((new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent actionEvent) {
//                inventory.getAllParts().stream().filter(part ->
//                        part.getName().toLowerCase().contains(searchFilter1.toLowerCase())).findFirst()
//
//
//                        .forEach(part -> {
//                     allParts.stream().filter(part ->
//                            part.getId() == partId
//                    ).findFirst().get();
//
//                });
            }
        }));

        return btn;

    }


    private Button renderAddButton(boolean isProducts, String btnLabel){
        Button btn = new Button();
        btn.setText(btnLabel);

        btn.setOnAction((new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent actionEvent) {
                Stage stage = new Stage();
                String label;
                Scene scene;

                // ADD PRODUCT PAGES
                if(isProducts == false) {
                    label = "Modify Part";
                    scene = new Scene(new PartMenu().renderAddMenu());

                }
                else {
                    label = "Modify Product";
                    scene = new Scene(new PartMenu().renderAddMenu());

                }

                stage.setTitle(label);

                stage.setScene(scene);
                stage.show();
            }
        }));

        return btn;
    }


    }








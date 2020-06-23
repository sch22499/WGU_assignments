package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        BorderPane borderPain = new BorderPane();

        HBox mainContainer = new HBox();

        mainContainer.setBorder( new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        Inventory inventory = new Inventory();

        MainContainer partsContainer = new MainContainer("Parts", false,  inventory);

        MainContainer productsContainer = new MainContainer("Products", true, inventory);

        mainContainer.getChildren().addAll(partsContainer.renderContainer(), productsContainer.renderContainer());


        borderPain.setPadding(new Insets(10));
        borderPain.setCenter(mainContainer);

        borderPain.setTop(renderHeader());
        borderPain.setBottom(renderFooter(primaryStage));

        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(borderPain);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public HBox renderFooter(Stage stage){
        HBox bottomBox = new HBox();
        bottomBox.setPadding(new Insets(10));
        bottomBox.setAlignment(Pos.CENTER_RIGHT);

        Button exitBtn = new Button();
        exitBtn.setText("Exit");
        exitBtn.setOnAction((new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent actionEvent) {
                stage.close();
            }
        }));

        bottomBox.getChildren().addAll(exitBtn);

        return bottomBox;
    }



    public HBox renderHeader(){
        HBox topBox = new HBox();

        Label label = new Label();
        label.setFont(new Font("Arial", 30));
        label.setText("thing");

        topBox.setPadding(new Insets(10));
        topBox.setAlignment(Pos.CENTER_LEFT);
        topBox.getChildren().addAll(label);

        return topBox;
    }

}

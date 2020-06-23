package sample;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PartMenu {

   private Part part;

    private boolean inHouse;
    private boolean outsourced;
   //private String label;


   public PartMenu(){
//       this.part = part;
   }

   public BorderPane renderAddMenu(){

        BorderPane container = new BorderPane();

       //container.getChildren().addAll(renderHeaderBox(), renderOptions());

       container.setPadding(new Insets(10));

       container.setTop(renderHeaderBox());

       container.setCenter(renderOptions(inHouse, outsourced));

       return container;
   }

   public HBox renderHeaderBox(){


       HBox headerContainer = new HBox();
       headerContainer.setPadding(new Insets(10));

       String label = " ";


       Label headerLabel = new Label(label);


        RadioButton inHouseBtn = renderRadioButtons("In House", true);


        RadioButton outsourcedBtn = renderRadioButtons("Outsourced", false);


       inHouseBtn.setOnAction((new javafx.event.EventHandler<javafx.event.ActionEvent>() {
           @Override
           public void handle(javafx.event.ActionEvent actionEvent) {
                outsourcedBtn.setSelected(false);
                outsourced = false;
                inHouse = true;

                System.out.println("sarah is doing a thing" + outsourced + inHouse);
           }
       }));


       outsourcedBtn.setOnAction((new javafx.event.EventHandler<javafx.event.ActionEvent>() {
           @Override
           public void handle(javafx.event.ActionEvent actionEvent) {
               inHouseBtn.setSelected(false);
               inHouse = false;
               outsourced = false;
           }
       }));



       // add individual methods for what each button doesssss

       headerContainer.getChildren().addAll(headerLabel, inHouseBtn, outsourcedBtn);



       return headerContainer;
   }

   private RadioButton renderRadioButtons(String label, boolean isSelected){
       RadioButton btn = new RadioButton();
       btn.setText(label);

       btn.setSelected(isSelected);
       btn.setPadding(new Insets(10));
       return btn;
   }

   private HBox renderTextbox(boolean isModified, String labelText){

       HBox textboxContainer = new HBox();
       textboxContainer.setPadding(new Insets(10));

       Label label = new Label(labelText);

       TextField textField = new TextField();

       textField.setPadding(new Insets(10));

       label.setPadding(new Insets(10));

       textboxContainer.getChildren().addAll(label, textField);


        return textboxContainer;


   }

   private VBox renderOptions(boolean inHouse, boolean outsourced){

       String sourceLabel = "";


       VBox optionsContainer = new VBox();
       optionsContainer.setPadding(new Insets(10));
       HBox one = renderTextbox(false, "test");
       HBox two = renderTextbox(false, "fdasf");
       HBox three = renderTextbox(false, "asdfdadsdf");

       if(inHouse = true) sourceLabel = "MachineId";
       if(outsourced = true) sourceLabel = "Company Name";

       HBox four = renderTextbox(false, sourceLabel);



       optionsContainer.getChildren().addAll(one, two, three, four);

       return optionsContainer;
   }


}

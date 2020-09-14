package sample.Controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
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
import sample.Exceptions.AddModifyExceptions;
import sample.Main;
import sample.Model.InHouse;
import  sample.Model.Inventory;
import sample.Model.Outsourced;
import sample.Model.Part;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static sample.Controller.MainController.getModifiedPart;

public class PartsMenuController implements Initializable {

    private static Part modifiedPart;

    private boolean isInHouse;



    @FXML
    private Label titleLabel;

    @FXML
    private ToggleGroup partTypeToggle;

    @FXML
    private RadioButton inHouseBtn;

    @FXML
    private  RadioButton outsourcedBtn;

    @FXML
    private TextField idTxtField;


    @FXML
    private TextField nameTxtField;

    @FXML
    private TextField invTxtField;

    @FXML
    private TextField priceTxtField;

    @FXML
    private TextField maxTxtField;



    @FXML
    private TextField minTxtField;

    @FXML
    private TextField dynamicTxtField;

    @FXML
    private Label dynamicTxtLabel;


    public PartsMenuController(){

        this.modifiedPart = getModifiedPart();
    }

    @FXML
    private void onInHouseBtn(Event event){
        isInHouse = true;
        outsourcedBtn.setSelected(false);
        dynamicTxtLabel.setText("Machine Id");
    }

    @FXML
    private void onOutsourceBtn(Event event){
        isInHouse = false;
        inHouseBtn.setSelected(false);
        dynamicTxtLabel.setText("Company Name");
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
    private void onSave(ActionEvent event) throws Exception{
        Part newPart;

        try {
            double partPrice = Double.parseDouble(priceTxtField.getText());
            int partInvLvl = Integer.parseInt(invTxtField.getText());
            int maxInvLvl = Integer.parseInt(maxTxtField.getText());
            int minInvLvl = Integer.parseInt(minTxtField.getText());
            if (isInHouse) {
                int machineId = Integer.parseInt(dynamicTxtField.getText());

                newPart = new InHouse(
                        nameTxtField.getText(),
                        partPrice,
                        partInvLvl,
                        minInvLvl,
                        maxInvLvl,
                        machineId
                );

                newPart.isValid();

                if(modifiedPart == null){
                    newPart.setId(Inventory.getPartIDCount());
                    Inventory.addPart(newPart);
                }else{
                    newPart.setId(modifiedPart.getId());
                    Inventory.updatePart(newPart);
                }

            } else {
                newPart = new Outsourced(
                        nameTxtField.getText(),
                        partPrice,
                        partInvLvl,
                        minInvLvl,
                        maxInvLvl,
                        dynamicTxtField.getText()
                );
                newPart.isValid();

                if(modifiedPart == null){
                    newPart.setId(Inventory.getPartIDCount());
                    Inventory.addPart(newPart);
                }else{
                    int partId = modifiedPart.getId();
                    newPart.setId(partId);
                    Inventory.updatePart(newPart);

                }
            }
            Parent loader = FXMLLoader.load(getClass().getResource("mainController.fxml"));
            Scene scene = new Scene(loader);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
            modifiedPart = null;

        } catch (AddModifyExceptions e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ValidationError");
            alert.setHeaderText("Part not valid");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if(modifiedPart == null){
            inHouseBtn.setSelected(true);
            titleLabel.setText("Add Part");
            isInHouse = true;
            idTxtField.setText("AutoGen - Disabled");
        }else{

            isInHouse = modifiedPart instanceof InHouse;
            inHouseBtn.setSelected(isInHouse);
            outsourcedBtn.setSelected(!isInHouse);
            inHouseBtn.setDisable(true);
            outsourcedBtn.setDisable(true);

            titleLabel.setText("Modify Part");
            idTxtField.setText("AutoGen - Disabled");
            nameTxtField.setText(modifiedPart.getName());
            invTxtField.setText(Integer.toString(modifiedPart.getStock()));
            maxTxtField.setText(Integer.toString(modifiedPart.getMax()));
            minTxtField.setText(Integer.toString(modifiedPart.getMin()));
            priceTxtField.setText(Double.toString(modifiedPart.getPrice()));

            if(modifiedPart instanceof InHouse){
                dynamicTxtField.setText(Integer.toString(((InHouse) modifiedPart).getMachineId()));
                dynamicTxtLabel.setText("Machine Id");
            }else{
                dynamicTxtField.setText(((Outsourced) modifiedPart).getCompanyName());
                dynamicTxtLabel.setText("Company Name");
            }

        }

    }

}

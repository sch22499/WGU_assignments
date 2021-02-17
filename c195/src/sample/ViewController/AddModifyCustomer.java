package sample.ViewController;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.Model.Customer;

import java.net.URL;
import java.util.ResourceBundle;

public class AddModifyCustomer implements Initializable {


    private Label titleLabel;
    private Button cancelButton;
    private Button addModifyButton;

    private TextField customerID;
    private TextField customerNameField;
    private TextField addressField;
    private TextField cityField;
    private TextField countryField;
    private TextField zipcodeField;
    private TextField phoneNumberField;

    private Customer modifiedCustomer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if (modifiedCustomer == null) {
            titleLabel.setText("Add Customer");
            customerID.setText("AutoGen - Disabled");
        } else {

            titleLabel.setText("Modify Customer");
            customerID.setText("AutoGen - Disabled");
            customerNameField.setText(modifiedCustomer.getCustomerName());
            addressField.setText(Integer.toString(modifiedCustomer.getAddressId()));//Translate with thingy
            cityField.setText(Integer.toString(modifiedCustomer.getAddressId()));//add thingy
            countryField.setText(Integer.toString(modifiedCustomer.getAddressId()));//thinghy
            zipcodeField.setText(Double.toString(modifiedCustomer.getAddressId()));
            phoneNumberField.setText(modifiedCustomer.getCustomerName());//ADD PHONENUYMBER


        }
    }
}


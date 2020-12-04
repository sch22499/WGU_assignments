package sample.ViewController;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.DAO.CustomerDAOImpl;
import sample.Model.Customer;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerView implements Initializable {


    @FXML
    private TableView<Customer> table;

    @FXML
    private TableColumn<Customer, String> customerNameCol;

    @FXML
    private TableColumn<Customer, Integer> customerIdCol;

    @FXML
    private TableColumn<Customer, Integer> addressColumn;

    @FXML
    private TableColumn<Customer, String> createDate;

    @FXML
    private TableColumn<Customer, String> createdBy;

    @FXML
    private TableColumn<Customer, String> lastUpdatedCol;

    @FXML
    private TableColumn<Customer, String> lastUpdateByCol;
            //TODO convert address from address id;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button modifyButton;

    @FXML
    private Button homeButton;

    private static Customer selectedCustomer;


    public CustomerView(){}




    private void populateTable() throws Exception {



        customerIdCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCustomerId()).asObject());
        customerNameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCustomerName()));
        addressColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getAddressId()).asObject());


        table.setItems(CustomerDAOImpl.getAllCustomers());


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectedCustomer = null;
        try {
            populateTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

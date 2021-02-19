package sample.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.Customer;
import sample.Model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import static sample.util.TimeFiles.stringToCalendar;

public class CustomerDAOImpl {

    static boolean isActive;

    public static void  createCustomer(int customerId, String customerName, int addressId, boolean isActive, Calendar createDate, User createdBy, Calendar lastUpdate, User lastUpdatedBy){
       String comma = ", ";
       String sqlStatement = "INSERT INTO customer(customerId, customerName, addressId, active, createDate, createdBy, lastUpdate, lastUpdateBy) " +
                "VALUE(" +
                customerId + comma + customerName + comma + addressId +
                comma + isActive + comma + createDate + comma + createdBy +
                comma + lastUpdate + lastUpdatedBy + ")";

        Query.makeQuery(sqlStatement);
    }

    public static void updateCustomer(int customerId, String customerName, int addressId, boolean active, Calendar createDate, User createdBy, Calendar lastUpdate, User lastUpdatedBy){
          String sqlStatement =
                "UPDATE CUSTOMERS" +
                "SET "
                + "customerId = " + customerId
                + ", customerName = " + customerName
                + ", addressId = " + addressId
                + ", active = " + active
                + ", createDate = " + createDate
                + ", createdBy = " + createdBy
                + ", lastUpdate = " + lastUpdate
                + ", lastUpdateBy = " + lastUpdatedBy;

            Query.makeQuery(sqlStatement);
    }




    public static Customer getCustomer(int customerId) throws SQLException, Exception{
        String sqlStatement="select * FROM customer WHERE customerId  = '" + customerId + "'";
        Query.makeQuery(sqlStatement);
        Customer customerResult;
        ResultSet result=Query.getResult();
        while(result.next()){
            String customerNameG=result.getString("customerName");
            int addressId = result.getInt("addressId");
            int active = result.getInt("active");
            if(active == 1) isActive = true;


            String createDate=result.getString("createDate");
            String createdBy=result.getString("createdBy");
            String lastUpdate=result.getString("lastUpdate");
            String lastUpdateBy=result.getString("lastUpdateBy");
            Calendar createDateCalendar=stringToCalendar(createDate);
            Calendar lastUpdateCalendar=stringToCalendar(lastUpdate);

            User createdByUser = UserDAOImpl.getUser(createdBy);
            User lastUpdatedByUser = UserDAOImpl.getUser(lastUpdateBy);
            customerResult = new Customer(customerId, customerNameG, addressId, isActive, createDateCalendar, createdByUser, lastUpdateCalendar, lastUpdatedByUser);
            return customerResult;
        }
        return null;
    }


    public static ObservableList<Customer> getAllCustomers() throws SQLException, Exception{
        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
        String sqlStatement="select * FROM customer";
        Query.makeQuery(sqlStatement);
        Customer customerResult;
        ResultSet result=Query.getResult();
        while(result.next()){
            int customerId =result.getInt("customerId");
            String customerNameG=result.getString("customerName");
            int addressId = result.getInt("addressId");
            int active = result.getInt("active");
            if(active == 1) isActive = true;


            String createDate=result.getString("createDate");
            String createdBy=result.getString("createdBy");
            String lastUpdate=result.getString("lastUpdate");
            String lastUpdateby=result.getString("lastUpdateBy");
            Calendar createDateCalendar=stringToCalendar(createDate);
            Calendar lastUpdateCalendar=stringToCalendar(lastUpdate);

            User createdByUser = UserDAOImpl.getUser(createdBy);
            User lastUpdatedByUser = UserDAOImpl.getUser(lastUpdateby);

            customerResult = new Customer(customerId, customerNameG, addressId, isActive, createDateCalendar, createdByUser, lastUpdateCalendar, lastUpdatedByUser);

            allCustomers.add(customerResult);
        }
        return allCustomers;
    }


    public static void updateCustomer(){

    }


    public static void deleteCustomer(int customerId){
        String sqlStatement = "DELETE FROM user WHERE userId='" + customerId +"'";
        Query.makeQuery(sqlStatement);
    }






}

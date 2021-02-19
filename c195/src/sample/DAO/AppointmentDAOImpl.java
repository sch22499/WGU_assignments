package sample.DAO;

import sample.Model.Appointment;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.User;

import static sample.util.TimeFiles.stringToCalendar;


public class AppointmentDAOImpl {


    public static ObservableList<Appointment> getAppointmentsForUser(int userId) throws Exception {

        String sqlStatement = "select * FROM appointment WHERE userId = '" + userId + "'";
        Query.makeQuery(sqlStatement);

        Appointment appointmentResult;
        ObservableList<Appointment> allAppointments=FXCollections.observableArrayList();
        ResultSet result = Query.getResult();
        while (result.next()) {
            int appointmentId = result.getInt("appointmentId");
            int customerId = result.getInt("customerId");
            int userIdQ = result.getInt("userId");
            String title = result.getString("title");
            String description = result.getString("description");
            String location = result.getString("location");
            String contact = result.getString("contact");
            String type = result.getString("type");
            String url = result.getString("url");
            String start = result.getString("start");
            String end = result.getString("end");
            String createdBy = result.getString("createdBy");
            String createDate = result.getString("createDate");
            String lastUpdate = result.getString("lastUpdate");
            String lastUpdatedBy = result.getString("lastUpdatedBy");

            Calendar lastUpdateCalender = stringToCalendar(lastUpdate);
            Calendar createDateCalender = stringToCalendar(createDate);
            Calendar startCalender = stringToCalendar(start);
            Calendar endCalender = stringToCalendar(end);

            User createdByUser = UserDAOImpl.getUser(createdBy);
            User lastUpdatedBytUser = UserDAOImpl.getUser(lastUpdatedBy);

            appointmentResult = new Appointment(appointmentId, customerId, userIdQ, title,
                    description, location, contact, type, url, startCalender, endCalender,
                    createDateCalender, createdByUser, lastUpdateCalender, lastUpdatedBytUser);
            allAppointments.add(appointmentResult);
        }

        return allAppointments;
    }

    public static ObservableList<Appointment> getAllAppointments() throws Exception {
        ObservableList<Appointment> allAppointments=FXCollections.observableArrayList();
        String sqlStatement = "select * FROM appointment";
        Query.makeQuery(sqlStatement);

        Appointment appointmentResult;

        ResultSet result = Query.getResult();
        while (result.next()) {
            int appointmentId = result.getInt("appointmentId");
            int customerId = result.getInt("customerId");
            int userId = result.getInt("userId");
            String title = result.getString("title");
            String description = result.getString("description");
            String location = result.getString("location");
            String contact = result.getString("contact");
            String type = result.getString("type");
            String url = result.getString("url");
            String start = result.getString("start");
            String end = result.getString("end");
            String createdBy = result.getString("createdBy");
            String createDate = result.getString("createDate");
            String lastUpdate = result.getString("lastUpdate");
            String lastUpdatedBy = result.getString("lastUpdateBy");

            Calendar lastUpdateCalender = stringToCalendar(lastUpdate);
            Calendar createDateCalender = stringToCalendar(createDate);
            Calendar startCalender = stringToCalendar(start);
            Calendar endCalender = stringToCalendar(end);

            User createdByUser = UserDAOImpl.getUser(createdBy);
            User lastUpdatedBytUser = UserDAOImpl.getUser(lastUpdatedBy);

            appointmentResult = new Appointment(appointmentId, customerId, userId, title,
                    description, location, contact, type, url, startCalender, endCalender,
                    createDateCalender, createdByUser, lastUpdateCalender, lastUpdatedBytUser);
            allAppointments.add(appointmentResult);
        }

        return allAppointments;
    }

    public static String getApointmentTypesByMonth() throws SQLException{


    }

    public static String parseAppointmentTypes() throws SQLException {
        String sqlStatement = "select type, count(*) FROM appointment group by appointment.type";

        Query.makeQuery(sqlStatement);
        String typeResult = new String();

        ResultSet result = Query.getResult();
        while (result.next()){
            String type = result.getString("type");
            int count = result.getInt("count(*)");

            typeResult += type+ ": " + count + "\n";
        }
        return typeResult;
    }




}

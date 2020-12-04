package sample.DAO;



        import sample.Model.User;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.util.Calendar;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;

        import static sample.util.TimeFiles.stringToCalendar;
//  import static sample.utilities.TimeFiles.stringToCalendar;


public class UserDAOImpl {
    static boolean isActive;
    public static User getUser(String userName) throws SQLException, Exception{
        // type is name or phone, value is the name or the phone #
        String sqlStatement="select * FROM user WHERE userName  = '" + userName+ "'";
        //  String sqlStatement="select FROM address";
        Query.makeQuery(sqlStatement);
        User userResult;
        ResultSet result=Query.getResult();
        while(result.next()){
            int userid=result.getInt("userid");
            String userNameG=result.getString("userName");
            String password=result.getString("password");
            int active=result.getInt("active");
            if(active==1) isActive=true;
            String createDate=result.getString("createDate");
            String createdBy=result.getString("createdBy");
            String lastUpdate=result.getString("lastUpdate");
            String lastUpdateby=result.getString("lastUpdateBy");
            Calendar createDateCalendar=stringToCalendar(createDate);
            Calendar lastUpdateCalendar=stringToCalendar(lastUpdate);
            //   s(int addressId, String address, String address2, int cityId, String postalCode, String phone, Calendar createDate, String createdBy, Calendar lastUpdate, String lastUpdateBy)
            userResult= new User(userid, userName, password, isActive, createDateCalendar, createdBy, lastUpdateCalendar, lastUpdateby);
            return userResult;
        }
        return null;
    }
    public static ObservableList<User> getAllUsers() throws SQLException, Exception{
        ObservableList<User> allUsers=FXCollections.observableArrayList();
        String sqlStatement="select * from user";
        Query.makeQuery(sqlStatement);
        ResultSet result=Query.getResult();
        while(result.next()){
            int userid=result.getInt("userid");
            String userNameG=result.getString("userName");
            String password=result.getString("password");
            int active=result.getInt("active");
            if(active==1) isActive=true;
            String createDate=result.getString("createDate");
            String createdBy=result.getString("createdBy");
            String lastUpdate=result.getString("lastUpdate");
            String lastUpdateby=result.getString("lastUpdateBy");
            Calendar createDateCalendar=stringToCalendar(createDate);
            Calendar lastUpdateCalendar=stringToCalendar(lastUpdate);
            //   s(int addressId, String address, String address2, int cityId, String postalCode, String phone, Calendar createDate, String createdBy, Calendar lastUpdate, String lastUpdateBy)
            User userResult= new User(userid, userNameG, password, isActive, createDateCalendar, createdBy, lastUpdateCalendar, lastUpdateby);
            allUsers.add(userResult);

        }
        return allUsers;
    }

    public static String parseUserSchedule() throws SQLException {
         //for all users
        String sqlResult = new String();

        String sqlStatement = "SELECT\n" +
                "  user.userName,\n" +
                "  customer.customerName,\n" +
                "  appointment.start,\n" +
                "  appointment.end,\n" +
                "  appointment.type\n" +
                "FROM user\n" +
                "JOIN appointment\n" +
                "  ON user.userId = appointment.userId\n" +
                "JOIN customer\n" +
                "  ON appointment.customerId = customer.customerId";

        Query.makeQuery(sqlStatement);
        ResultSet result=Query.getResult();
        while(result.next()) {
            String userNameG = result.getString("userName");
            String customerName = result.getString("customerName");
            String start = result.getString("start");
            String end = result.getString("end");
            String type = result.getString("type");
            sqlResult += userNameG +" Has a/n " + type + " appointment with " + customerName + " from " + start + " to " + end + "\n";
        }
        return sqlResult;
    }
}

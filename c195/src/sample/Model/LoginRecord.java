package sample.Model;

import javafx.beans.Observable;
import javafx.collections.ObservableList;

import java.util.Calendar;

public class LoginRecord {

    private User loggedInUser;

    //TODO I think we want to send the user data when we log in. We will have the appointment authentication method

    private Calendar timeStamp;

    private ObservableList<Appointment> allAppointments;

    private ObservableList<Appointment> usersAppointments;


    public LoginRecord(User loggedInUser, Calendar calendar){

    }

}

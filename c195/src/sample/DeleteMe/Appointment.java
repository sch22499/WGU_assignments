package sample.DeleteMe;

public class Appointment
{
    @ViewableData(permittedUserRoles = { UserRole.ADMIN, UserRole.TEST_ROLE })
    private String dateTime;

    @ViewableData()
    private String doctorName;

    public Appointment(
        String dateTime,
        String doctorName)
    {
        this.dateTime = dateTime;
        this.doctorName = doctorName;
    }
}

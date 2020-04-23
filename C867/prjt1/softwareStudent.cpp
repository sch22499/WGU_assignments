#include"softwareStudent.h"


SoftwareStudent::SoftwareStudent(Degree degree, string studentId, string firstName, string lastName,  string emailAddress, int age, int daysToCompleteCourse[])
    : Student(studentId, firstName, lastName, emailAddress, age, daysToCompleteCourse)
{
}

Degree SoftwareStudent::getDegreeProgram(){
    return degree;
}

void SoftwareStudent::print(){
    cout << getStudentId();
	cout << "\tFirst Name: " << getFirstName();
	cout << "\t Last Name: " << getLastName();
	cout << "\t Age: " << getAge();
	cout << "\t" << "daysInCourse: {"; cout << getDaysToCompleteCourse()[0] << ", " << getDaysToCompleteCourse()[1] << ", " << getDaysToCompleteCourse()[2] << "}";
	cout << "\t Degree Program: SECURITY";
	cout << endl;
}
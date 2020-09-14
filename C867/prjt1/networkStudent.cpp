#include"networkStudent.h"
#include"student.h"


NetworkStudent::NetworkStudent(Degree degreeType, string studentId, string firstName, string lastName,  string emailAddress, int age, int daysToCompleteCourse[])
    : Student(studentId, firstName, lastName, emailAddress, age, daysToCompleteCourse)
{
    degree = degreeType;
}

Degree NetworkStudent::getDegreeProgram(){
    cout << "sarah this is a test " <<  this->degree << endl;
    return degree;
}

void NetworkStudent::print(){

	cout << getStudentId();
	cout << "\tFirst Name: " << getFirstName();
	cout << "\t Last Name: " << getLastName();
	cout << "\t Age: " << getAge();
	cout << "\t" << "daysInCourse: {"; cout << getDaysToCompleteCourse()[0] << ", " << getDaysToCompleteCourse()[1] << ", " << getDaysToCompleteCourse()[2] << "}";
	cout << "\t Degree Program: SECURITY";
	cout << endl;
}
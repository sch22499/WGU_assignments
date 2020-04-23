#include"securityStudent.h"
#include"string"
using namespace std;


SecurityStudent::SecurityStudent() {}


SecurityStudent::SecurityStudent(Degree degreeProgram, string studentId, string firstName, string lastName,  string emailAddress, int age, int daysToCompleteCourse[])
    : Student(studentId, firstName, lastName, emailAddress, age, daysToCompleteCourse)
{
    degree = degreeProgram;
}

Degree SecurityStudent::getDegreeProgram(){
    return degree;
}

void SecurityStudent::print(){

	cout << getStudentId();
	cout << "\tFirst Name: " << getFirstName();
	cout << "\t Last Name: " << getLastName();
	cout << "\t Age: " << getAge();
	cout << "\t" << "daysInCourse: {"; cout << getDaysToCompleteCourse()[0] << ", " << getDaysToCompleteCourse()[1] << ", " << getDaysToCompleteCourse()[2] << "}";
	cout << "\t Degree Program: SECURITY";
	cout << endl;
}

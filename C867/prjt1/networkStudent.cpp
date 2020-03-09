#include"NetworkStudent.h"


NetworkStudent::NetworkStudent(Degree degree, string studentId, string firstName, string lastName,  string emailAddress, int age, int daysToCompleteCourse[])
    : Student{studentId, firstName, lastName, emailAddress, age, daysToCompleteCourse, degree}
{
    this->degree = degree;
}

Degree NetworkStudent::getDegreeProgram(){
    return this->degree;
}
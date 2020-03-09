#include"SoftwareStudent.h"


SoftwareStudent::SoftwareStudent(Degree degree, string studentId, string firstName, string lastName,  string emailAddress, int age, int daysToCompleteCourse[])
    : Student{studentId, firstName, lastName, emailAddress, age, daysToCompleteCourse, degree} 
{
    this->degree = degree;
}

Degree SoftwareStudent::getDegreeProgram(){
    return this->degree;
}
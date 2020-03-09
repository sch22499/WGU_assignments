#include"securityStudent.h"
#include"string"
using namespace std;




SecurityStudent::SecurityStudent(Degree degree, string studentId, string firstName, string lastName,  string emailAddress, int age, int daysToCompleteCourse[])
    : Student{studentId, firstName, lastName, emailAddress, age, daysToCompleteCourse, degree}
{
    this->degree = degree;
}

Degree SecurityStudent::getDegreeProgram(){
    return this->degree;
}
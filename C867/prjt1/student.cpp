#include "string"
#include "student.h"
#include<iostream>
using namespace std;



Student::Student(string studentId, string firstName, string lastName,  string emailAddress, int age, int daysToCompleteCourse[], Degree degreeType)
    : daysToCompleteCourse(this -> daysToCompleteCourse)
    {
        this->studentId = studentId;
        this->firstName = firstName;
        this->lastName = lastName;
        this->emailAddress = emailAddress;
        this->age = age;
        this->degreeType = degreeType;
    }
        
    void Student::print() {
        cout << studentId << endl;
        cout << firstName << endl;
        cout << lastName << endl;
        cout << emailAddress << endl;
        cout << age  << endl;
        cout << daysToCompleteCourse << endl;
        cout << degreeType << endl;
    }

    string Student::getStudentId(){
        return studentId;
    }

    void Student::setStudentId(string studentId){
        this -> studentId = studentId;
    }

    int * Student::getDaysToCompleteCourse(){
        return daysToCompleteCourse;
    }

    string Student::getFirstName(){
         return firstName;
    }

    void Student::setFirstname(string firstName){
        this -> firstName = firstName;
    }

    string Student::getLastName(){
        return lastName;
    }
    
    void Student::setLastName(string lastName){
        this -> lastName = lastName;
    }

    string Student::getEmailAddress(){
        return emailAddress;
    }

    void Student::setEmailAddress(string emailAddress){
        this -> emailAddress = emailAddress;
    }

    int Student::getAge(){
        return age;
    }

    void Student::setAge(int age){
        this -> age = age;
    }


#include "string"
#include "student.h"
#include<iostream>
using namespace std;

Student::Student(){}
Student::Student(string studentId, string firstName, string lastName,  string emailAddress, int age, int daysToCompleteCourse[])
    {
        this->studentId = studentId;
        this->firstName = firstName;
        this->lastName = lastName;
        this->emailAddress = emailAddress;
        this->age = age;

        for(int i = 0; i<3; i++){
            this->daysToCompleteCourse[i] = daysToCompleteCourse[i];
        }
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

    void Student::setDaysToCompleteCourse(int daysToCompleteCourse[]){
        for(int i = 0; i<3; i++){
            this->daysToCompleteCourse[i] = daysToCompleteCourse[i];
        }
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

    void Student::print(){}

    Student::~Student() {}


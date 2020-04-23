#include "string"
#include "degree.h"
#include<iostream>
using namespace std;

#ifndef STUDENT_H
#define STUDENT_H


class Student { 

    private: 

        string studentId;
        string firstName;
        string lastName;
        string emailAddress;

        int age; 

        int daysToCompleteCourse[3];


    public: 
        Student();//constructor
        Student(string studentId, string firstName, string lastName,  string emailAddress, int age, int daysToCompleteCourse[]);
        
        
        virtual void print();

        Degree virtual getDegreeProgram();

        string  getStudentId();


        int * getDaysToCompleteCourse();

        void setStudentId(string studentId);

        void setDaysToCompleteCourse(int daysToCompleteCourse[]);
        

        string getFirstName();

        void setFirstname(string firstName);

        string getLastName();
        
        void setLastName(string lastName);

        string getEmailAddress();

        void setEmailAddress(string emailAddress);

        int getAge();
        void setAge(int age);
        
        ~Student();


};

#endif 

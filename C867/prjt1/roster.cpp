#include <vector>
#include <sstream>
#include <iostream> 
#include <string>
#include<stdlib.h>

#include"degree.h"
#include"student.h"
#include"securityStudent.h"
#include"networkStudent.h"
#include"softwareStudent.h"

using namespace std;



class Roster{

    public:

    Roster(){};
    int index = 0;

    Student* classRosterArray[20] = {};
    

    void add(string studentId, string firstName, string lastName, string emailAddress, int age, int daysInCourse1, int daysInCourse2, int daysInCourse3, Degree degreeType){

        int daysToCompleteCourse[3] = {daysInCourse1, daysInCourse2, daysInCourse3};
            switch(degreeType){
                case SECURITY:{
                    classRosterArray[index] = new SecurityStudent(degreeType, studentId, firstName, lastName, emailAddress, age, daysToCompleteCourse);
                }case NETWORK:{
                    classRosterArray[index] =  new NetworkStudent(degreeType, studentId, firstName, lastName, emailAddress, age, daysToCompleteCourse);
                }case SOFTWARE:{
                    SoftwareStudent *softwareStudent = new SoftwareStudent(degreeType, studentId, firstName, lastName, emailAddress, age, daysToCompleteCourse);
                }
            }        
        index++;  
    }

    Degree convertDegree(string degree){
            if(degree == "SECURITY") return SECURITY;
            if (degree == "NETWORK") return NETWORK;
            else  return SOFTWARE;
        }    

    void printAverageDaysInCourse(string studentId){
        int totalDays = 0;
        for(int i = 0; i < sizeof(classRosterArray); i++){
            if(classRosterArray[i] != nullptr && classRosterArray[i]->getStudentId() == studentId){
                int *days = classRosterArray[i]->getDaysToCompleteCourse();
                for(int j = 0; j < 3; j++){
                    totalDays += days[j];
                }

                float avg = totalDays / 3;

                cout << avg << endl;
            } 
            cout << "Student with that id not found" << endl;
        }

    }

    void printByDegreeProgram(Degree degreeProgram){
        for (int i = 0; i < sizeof(classRosterArray); i++){
            if(classRosterArray[i]->getDegreeProgram() == degreeProgram){
                classRosterArray[i]->print();
            }else{  
                cout << "No student with that Degree found" << endl;
            }    
        }
    }

    void printInvalidEmails(){
        for (int i = 0; i < sizeof(classRosterArray); i++){
            string email = classRosterArray[i]->getEmailAddress();
            size_t foundSpace = email.find(' ');
            size_t foundAt = email.find('@');
            size_t foundPeriod = email.find('.');

            if(foundPeriod == string::npos || foundAt == string::npos || !(foundSpace == string::npos)){
                cout << email << endl;
            }
        }
    }

    void printAll(){
       for(int i = 0; i < sizeof(classRosterArray); i++){
          (*classRosterArray[i]).print();
        }
    }

    void remove(string studentId){
        for(int i = 0; i < sizeof(classRosterArray); i++){
            if(classRosterArray[i] != nullptr && (*classRosterArray[i]).getStudentId() == studentId){
                delete classRosterArray[i];
            }else{
                cout << "No Student with this id found";
            }

        }
    }

    ~Roster(){
        *classRosterArray = 0;
    }

};



int main(){

    Roster classRoster;

    const string studentData[5] =
        {
            "A1,John,Smith,John1989@gm ail.com,20,30,35,40,SECURITY",
            "A2,Suzan,Erickson,Erickson_1990@gmailcom,19,50,30,40,NETWORK",
            "A3,Jack,Napoli,The_lawyer99yahoo.com,19,20,40,33,SOFTWARE",
            "A4,Erin,Black,Erin.black@comcast.net,22,50,58,40,SECURITY",
            "A5,Sarah,Hall,shal244@wgu.edu,21,30,15,60,SOFTWARE"
        };

    for(int i = 0; i < 5; i++) {
        vector<string> result = {};
        stringstream s_stream(studentData[i]);
        while(s_stream.good()){
            string substr;
            getline(s_stream, substr , ',');
            result.push_back(substr);
        }   
        string studentId = result[0];
        string firstName = result[1];
        string lastName = result[2];
        string emailAddress = result[3];
        int age = stoi(result[4]);
        Degree degreeType = classRoster.convertDegree(result[8]);

        classRoster.add(studentId, firstName, lastName, emailAddress, age, stoi(result[5]), stoi(result[6]), stoi(result[7]), degreeType); 
    }
    

    //classRoster.printInvalidEmails();
    
    cout << "Scripting and Programming \nC++ \nStudentId: #001152220 \nSarah Hall" << endl;


    //classRoster.printAll();


    system("pause");  
}






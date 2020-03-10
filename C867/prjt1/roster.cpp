#include"securityStudent.h"
#include"networkStudent.h"
#include"softwareStudent.h"
#include"student.h"
#include <string>
#include "string"
#include "degree.h"
#include <vector>
#include <sstream>
#include <iostream> 
using namespace std;

#include<stdlib.h>

class Roster{

    public:

    Roster(){};

    Student* classRosterArray[20] = {};
    

    const string studentData[5] =
        {
            "A1,John,Smith,John1989@gm ail.com,20,30,35,40,SECURITY",
            "A2,Suzan,Erickson,Erickson_1990@gmailcom,19,50,30,40,NETWORK",
            "A3,Jack,Napoli,The_lawyer99yahoo.com,19,20,40,33,SOFTWARE",
            "A4,Erin,Black,Erin.black@comcast.net,22,50,58,40,SECURITY",
            "A5,Sarah,Hall,shal244@wgu.edu,21,30,15,60,SOFTWARE"
        };

    void add(string studentId, string firstName, string lastName, string emailAddress, int age, int daysInCourse1, int daysInCourse2, int daysInCourse3, Degree degreeType){

        int daysToCompleteCourse[3] = {daysInCourse1, daysInCourse2, daysInCourse3};

        for(int i = 0; i < sizeof(classRosterArray); i++){
            if(classRosterArray[i] == nullptr){
                switch(degreeType){
                    case SECURITY:{
                        SecurityStudent *securityStudent = new SecurityStudent(degreeType, studentId, firstName, lastName, emailAddress, age, daysToCompleteCourse);
                        classRosterArray[i] = securityStudent;
                    }case NETWORK:{
                        NetworkStudent *networkStudent =  new NetworkStudent(degreeType, studentId, firstName, lastName, emailAddress, age, daysToCompleteCourse);
                        classRosterArray[i] = networkStudent;
                    }case SOFTWARE:{
                        SoftwareStudent *softwareStudent = new SoftwareStudent(degreeType, studentId, firstName, lastName, emailAddress, age, daysToCompleteCourse);
                        classRosterArray[i] = softwareStudent;}
                    }        
            }
        }       
               
    }

    Degree convertDegree(int degree){
            if(degree == 1) return SECURITY;
            if (degree == 3) return NETWORK;
            else  return SOFTWARE;

        }    

    void printAverageDaysInCourse(string studentId){
        for(int i = 0; i < sizeof(classRosterArray); i++){
            while(classRosterArray[i]->getStudentId() == studentId){
                int num1 = classRosterArray[i]->getDaysToCompleteCourse()[0];
                int num2 = classRosterArray[i]->getDaysToCompleteCourse()[1];
                int num3 = classRosterArray[i]->getDaysToCompleteCourse()[2];
                int total = num1 + num2 + num3;
                float avg = total / 3;

                cout << avg << endl;
            } 
            cout << "Student with that id not found" << endl;
        }

    }

    void printByDegreeProgram(int degreeProgram){

        Degree degree = convertDegree(degree);

        for (int i = 0; i < sizeof(classRosterArray); i++){
            while(classRosterArray[i]->getDegreeProgram() == degree){
                classRosterArray[i]->print();
            }   
                cout << "No student with that Degree found" << endl;    
            }
    }

    void printInvalidEmails(){
        for (int i = 0; i < sizeof(classRosterArray); i++){
            bool hasSpace;
            bool hasRequiredCharacters;
            string email = classRosterArray[i]->getEmailAddress();
            for(int ch = 0; ch < email.npos; ch++){
                if(email.at(ch) == ' '){
                    hasSpace = true;
                }else if(email.at(i) == '@' || email.at(i) == '.' ){
                    hasRequiredCharacters = true;
                }
            }

            if(hasSpace == true || hasRequiredCharacters == false){
                cout << email << endl;
            }
        }
    }

    void printAll(){
       for(int i = 0; i < sizeof(classRosterArray); i++){
           classRosterArray[i]->print();
        }
    }

    void parseData(){

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
            
            Degree degreeType = convertDegree(stoi(result[8]));

            for(int i = 0; i < result.size(); i++){
                add(studentId, firstName, lastName, emailAddress, age, stoi(result[5]), stoi(result[6]), stoi(result[7]), degreeType);
            }

        }
    }

    void remove(string studentId){
        for(int i = 0; i < sizeof(classRosterArray); i++){
            if(classRosterArray[i]->getStudentId() == studentId){
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

      Roster* classRoster = nullptr;
      classRoster = new Roster();
      classRoster->parseData();
    
     cout << "Scripting and Programming \n C++ \n StudentId: #001152220 \n Sarah Hall";

     classRoster->printAll();
    // classRoster.printInvalidEmails();



    // for(int i = 0; i < sizeof(classRoster.classRosterArray); i++){
    //     classRoster.printAverageDaysInCourse(classRoster.classRosterArray[i]->getStudentId());
    //     classRoster.printByDegreeProgram(SOFTWARE);
    //     classRoster.remove("A3");
    //     classRoster.remove("A3");
    // }

    return 0;   
}






#include"student.h" 
#include"degree.h"



class SecurityStudent : public Student{
   
    private:
        Degree degree = SECURITY;

    public:

        Degree getDegreeProgram() override;


        void print();
        SecurityStudent();

        SecurityStudent(Degree degree, string studentId, string firstName, string lastName,  string emailAddress, int age, int daysToCompleteCourse[]);

        
};
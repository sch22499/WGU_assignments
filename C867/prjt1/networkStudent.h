#include"student.h" 
#include"degree.h"

class NetworkStudent : public Student{
   
    private:
        Degree degree = NETWORK;

    public:

    NetworkStudent(Degree degree, string studentId, string firstName, string lastName,  string emailAddress, int age, int daysToCompleteCourse[]);

    Degree getDegreeProgram() override;
    
    void print();
};
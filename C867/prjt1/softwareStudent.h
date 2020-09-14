#include"student.h" 
#include"degree.h"

class SoftwareStudent : public Student{
   
    private:
        Degree degree = SOFTWARE;

    public:

    SoftwareStudent(Degree degree, string studentId, string firstName, string lastName,  string emailAddress, int age, int daysToCompleteCourse[]);

    Degree getDegreeProgram() override;

    void print();
        
};
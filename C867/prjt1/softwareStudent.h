#include"student.h" 

class SoftwareStudent : public Student{
   
    private:
        Degree degree;

    public:

    SoftwareStudent(Degree degree, string studentId, string firstName, string lastName,  string emailAddress, int age, int daysToCompleteCourse[]);

    Degree getDegreeProgram() override;
        
};
#include"student.h" 

class NetworkStudent : public Student{
   
    private:
        Degree degree;

    public:

    NetworkStudent(Degree degree, string studentId, string firstName, string lastName,  string emailAddress, int age, int daysToCompleteCourse[]);

    Degree getDegreeProgram() override;
        
};
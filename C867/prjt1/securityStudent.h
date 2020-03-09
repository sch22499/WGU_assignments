#include"student.h" 

class SecurityStudent : public Student{
   
    private:
        Degree degree;

    public:

   // SecurityStudent();

    SecurityStudent(Degree degree, string studentId, string firstName, string lastName,  string emailAddress, int age, int daysToCompleteCourse[]);

    Degree getDegreeProgram() override;
        
};
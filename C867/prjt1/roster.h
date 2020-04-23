#pragma once
#include"degree.h"
#include"student.h"
#include"securityStudent.h"
#include"networkStudent.h"
#include"softwareStudent.h"

class Roster {

    private: 
        Student *classRosterArray[5] = {};

    public: 
        Roster();
        ~Roster();



};
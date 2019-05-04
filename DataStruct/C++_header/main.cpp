#include <iostream>
using namespace std;
#include "test.h"

int main ()
{
    circle donut;
    double area = donut.getarea();
    cout<<"donut 면적은 "<<area<<"입니다 "<<endl;

    circle pizza(30);
    area = pizza.getarea();
    cout<<"pizza면적은 "<<area <<"입니다 "<<endl;
}
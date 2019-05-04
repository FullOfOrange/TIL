#include <iostream>
using namespace std;

#include "test.h"
circle :: circle()
{
    radius = 1;
    cout<<"반지름 "<<radius;
    cout<<" 원 생성"<<endl;
}

circle :: circle (int r)
{
    radius = r;
    cout<<"반지름 "<<radius<<" 원 생성"<<endl;
}

double circle :: getarea()
{
    return 3.14 * radius * radius;
}
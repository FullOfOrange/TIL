//
//  Polynomial.hpp
//  data_struct2
//
//  Created by 육진혁 on 05/04/2019.
//  Copyright © 2019 육진혁. All rights reserved.
//

#ifndef Polynomial_hpp
#define Polynomial_hpp

#include <stdio.h>
class Term;

class Polynomial{
private:
    Term *termArray; //term class 배열
    int capacity; 
    int terms;
public:
    Polynomial(int d);
    Polynomial Add();
    void NewTerm(const float theCoef,const int theExp);
};

class Term{
    friend class Polynomial;
private:
    float coef;
    int exp;
};
#endif /* Polynomial_hpp */

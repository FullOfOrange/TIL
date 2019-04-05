//
//  Term.hpp
//  data_struct2
//
//  Created by 육진혁 on 05/04/2019.
//  Copyright © 2019 육진혁. All rights reserved.
//

#ifndef Term_hpp
#define Term_hpp

#include <stdio.h>
class Polynomial;
class Term{
    friend class Polynomial;
private:
    float coef;
    int exp;
};
#endif /* Term_hpp */

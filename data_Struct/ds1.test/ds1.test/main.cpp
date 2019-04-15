//
//  main.cpp
//  ds1.test
//
//  Created by 육진혁 on 08/04/2019.
//  Copyright © 2019 육진혁. All rights reserved.
//

#include <iostream>
#include <algorithm>
#include <numeric>

using namespace std;

void Permutations(char*, const int);

int main(int argc, const char * argv[]) {
    char a[] = {'a','b','c'};
    Permutations(a, 3);
    
    return 0;
}

void Permutations(char *a, const int n){
    do{
        copy(a,a+n,ostream_iterator<char>(cout," "));
        cout<<endl;
    }while(next_permutation(a, a+n));
}

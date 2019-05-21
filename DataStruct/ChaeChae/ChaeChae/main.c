//
//  main.c
//  ChaeChae
//
//  Created by 채채 on 08/05/2019.
//  Copyright © 2019 채채. All rights reserved.
//

#include <stdio.h>
int add(int* , int*);//선언문

void sum(int a[],int size){
    for(int i=0; i<size; i++){
        a[i]*=2;
    }
}

int pow2(int a, int b){
    int s = 1;
    for (int i=1; i <= b; i++){
        s= s*a;
    }
    return s;
}

int main(int argc, const char * argv[]) {
    int b[10]={1,2,3,4,5,6,7,8,9,10};
    sum(b,10);
    for(int i=0; i<10; i++){
        printf("%d\n",b[i]);
    }
    return 0;
}

int add(int* a, int* b){//정의문
    *a = *a+*b;
    return *a+*b;
}

#include <iostream>
#include <cstring>
/*
class A{
private:
    int a;
    char *name;
public:
    A(int a,char* name);
    virtual void showA(int a){
        std::cout<<a<<std::endl;
    }
};
A::A(int a,char* name){
    this->a=a;
    this->name = new char[strlen(name)+1];
    strcpy(this->name,name);
    std::cout<<"A constructor: "<<this->a<<" name : "<<name<<std::endl;
}

class B : public A{
private:
    int b;
public:
    B(int a,char const* name);
};
B::B(int a, char const* name):A(a,name){
    a=b;
    std::cout<<"B constructor : "<<this->b<<std::endl;
}

class C : public B{
private:
    int c;
    char* name;
public:
    C(int a, char const* name);
    void showA(int c);
};
C::C(int a,char const* name):B(a,name){
    a=c;
}
void C::showA(int c){
    std::cout<<"C constructor: "<<c<<std::endl;
}
/*
void C::showA(){
    B::showA();
    std::cout<<"B : "<<c<<std::endl;
}
 */
int main(int argc, const char * argv[]) {
    char array[8]={'a','b','c','d','e','f','g','h'};
    std::copy(array+4,array+7,array+3);
    for(int i = 0; i<8; i++){
        printf("%c",array[i]);
    }
    return 0;
}

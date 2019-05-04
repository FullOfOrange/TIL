#include <iostream>
#include <cstring>

class A{
private:
    int a;
    char *name;
public:
    A(int a,char const* name);
    virtual void showA(int a){
        std::cout<<a<<std::endl;
    }
};
A::A(int a,char const* name){
    this->a=a;
    this->name = new char[strlen(name)+1];
    strcpy(this->name,name);
    std::cout<<"A constructor: "<<this->a<<" name : "<<name<<std::endl;
}

class B : private A{
private:
    int b;
public:
    B(int a,const char*name);
};
B::B(int a, const char* name):A(a,name),b(a){
    std::cout<<"B constructor : "<<this->b<<std::endl;
}

int main(int argc, const char * argv[]) {
    B b(3,"name");
    const char* a = "anemw";
    a = "1234";
    printf("%s",a);
    return 0;
}

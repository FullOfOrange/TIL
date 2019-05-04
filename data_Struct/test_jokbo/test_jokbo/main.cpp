#include <iostream>
using namespace std;
class XXX{
private:
    int a;
public:
    XXX():a(10){};
    XXX(int num):a(num){
    };
    ~XXX(){
        cout<<"destroy"<<endl;
    };
    void show(const XXX o){
        cout<<o.a<<endl;
    };
};
void swap(int &a,int &b){
    int temp = b;
    b = a;
    a =temp;
}
int main(void){
    int *p = new int a[10];
    printf("%d",*(p+1));
    return 0;
}

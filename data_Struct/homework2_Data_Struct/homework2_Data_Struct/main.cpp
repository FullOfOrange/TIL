#include <iostream>
#include <cstdlib>
#include <string>
#include <ctime>

#define MAX_NAME_LEN 10
#define MAX_PHONE_LEN 20

using namespace std;
/*array 사이즈 변경 함수*/
template <class T>
void ChangeSize1D(T*&a, const int oldSize, const int newSize){
    if (newSize < 0) throw "New length must be >= 0";
    
    T* temp = new T[newSize];                                  // new array
    int number = min(oldSize, newSize);  // number to copy
    copy(a, a + number, temp);
    delete [] a;                             // deallocate old memory
    a = temp;
}
/*Bizcard Class 부분*/
class Bizcard{
private:
    char* name;
    char* phone;
public:
    Bizcard(const char* name = "", const char* phone = "");
    Bizcard(const Bizcard& copy);
    Bizcard& operator=(const Bizcard& ref);
    ~Bizcard();
    void ShowInfo();
};
Bizcard::Bizcard(const char* _name, const char* _phone){
    name = new char[100];
    strcpy(name,_name);
    phone = new char[100];
    strcpy(phone,_phone);
}
Bizcard::Bizcard(const Bizcard& copy){//복사연산자
    strcpy(name,copy.name);
    strcpy(phone,copy.phone);
}
Bizcard& Bizcard::operator=(const Bizcard& ref){//대입연산자
    strcpy(name,ref.name);
    strcpy(phone,ref.phone);
    return *this;
}
Bizcard::~Bizcard(){//소멸자
    delete [] name;
    delete [] phone;
}
void Bizcard::ShowInfo(){
    cout<<name<<endl<<phone<<endl;
}
/*Bizcard 끝*/
/*Bag Class 정의부*/
template <typename T>
class Bag{
private:
    T *array;
    int capacity;
    int top;
public:
    Bag (int bagCapacity = 3);
    ~Bag();
    int Size() const;
    bool IsEmpty() const;
    T& Element() const;
    void Push(const T&);
    void Pop();
};
template <typename T>
Bag<T>::Bag (int bagCapacity): capacity ( bagCapacity ) { //구현완료
    if (capacity < 1) throw "Capacity must be > 0";
    array = new T[capacity];
    top = -1;
}
template <typename T>
Bag<T>::~Bag() { delete[ ] array; } //구현완료

template <typename T>
int Bag<T>::Size() const{ return capacity; } //구현완료

template <typename T>
bool Bag<T>::IsEmpty() const{ //구현완료
    if(top!=-1) return false;
    else return true;
}
template <typename T>
T& Bag<T>::Element() const{ //구현완료
    srand((unsigned)time(NULL));
    return array[rand()%(top+1)];
}
template <typename T>
void Bag<T>::Push(const T& x) {//구현완료
    if (capacity==top+1){
        ChangeSize1D(array, capacity, 2 * capacity);
        capacity*=2;
    }
    array[++top] = x;
}
template <typename T>
void Bag<T>::Pop() { //구현완료
    if (IsEmpty()) throw "Bag is empty, cannot delete";
    srand((unsigned)time(NULL));
    int deletePos = rand()%top+1;
    copy(array+deletePos+1,array+top+1,array+deletePos);
    // compact array
     array[top--].~T();// destructor for T
}
/*Bag 끝*/
/* main code*/
int main(int argc, const char * argv[]) {
    
    char *name = new char[MAX_NAME_LEN];
    char *phone = new char[MAX_PHONE_LEN];
    
    cin>>name>>phone;
    
    Bizcard card1(name,phone);
    Bizcard card2(strcat(name,"2"),strcat(phone,"2"));
    //1234card2.ShowInfo();
    /*
    Bag<Bizcard> *b;
    try{
        b = new Bag<Bizcard>(1);
        
        cout<<b->Size()<<endl;
        //b->Element().ShowInfo();//만약 b와 연관된 것들을 try 밖으로 빼버리면 이 문장들은 실행되니, 문제가 발생한다.
        cout<<b->IsEmpty()<<endl;
    }catch(const char* e){
        cout<<e;
    }
    */
    Bag<Bizcard> *b5;
    try{
        b5 = new Bag<Bizcard>();
        b5->Push(card1);
        b5->Push(card2);
        b5->Push(card2);
        b5->Push(card1);
        b5->Element().ShowInfo();
        cout<<b5->Size()<<b5->IsEmpty()<<endl;
        b5->Pop();
        b5->Pop();
        
    }catch(const char* e){
        cout<<e<<endl;
    }
    return 0;
}

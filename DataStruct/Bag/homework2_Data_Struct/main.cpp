#include <iostream>
#include <cstdlib>
#include <cstring>
#include <ctime>

#define MAX_NAME_LEN 10
#define MAX_PHONE_LEN 20

using namespace std;
/*array 사이즈 변경 함수*/
template <class T>
void ChangeSize1D(T*&a, const int oldSize, const int newSize){
    if (newSize < 0) throw "New length must be >= 0";
    T* temp = new T[newSize];            // new array
    int number = min(oldSize, newSize);  // number to copy
    copy(a, a + number, temp);
    delete [] a;                         // deallocate old memory
    a = temp;
}
/*Bizcard Class 부분*/
class Bizcard{
private:
    char* name;
    char* phone;
public:
    Bizcard();
    Bizcard(const char* name, const char* phone);
    Bizcard(const Bizcard& copy);
    Bizcard& operator=(const Bizcard& ref);
    ~Bizcard();
    void ShowInfo();
};
Bizcard::Bizcard(){
    
}
Bizcard::Bizcard(const char* _name, const char* _phone){
    name = new char(strlen(_name)+1);
    strcpy(name,_name);
    phone = new char(strlen(_phone)+1);
    strcpy(phone,_phone);
}
Bizcard::Bizcard(const Bizcard& copy){//복사생성자
    name = new char(strlen(copy.name)+1);
    strcpy(name,copy.name);
    phone = new char(strlen(copy.phone)+1);
    strcpy(phone,copy.phone);
}
Bizcard& Bizcard::operator=(const Bizcard& ref){//대입연산자
    delete name;
    delete phone;
    name = new char(strlen(ref.name)+1);
    strcpy(name,ref.name);
    phone = new char(strlen(ref.phone)+1);
    strcpy(phone,ref.phone);
    return *this;
}
//복사 생성자는 기존의 것을 받아서 새롭게 넣는거임. 대입 연산자는 기존에 있는 것에 기존에 있던 것을 넣는 것.
/*
 A *a_old = new A;
 A *a_new(*a_old); // 이런식으로 하면 복사생성자가 나옴.
 
 A *a1 = new A;
 A *a2 = new A;
 *a1=*a2; // 이런식으로 하면 대입 연산자가 호출됨.
 
 위 두개는 포인터고, 받는 것은 참조자 형태임(변수) 따라서 변수 형태로 넣어주기 위하여 *a_old 형식으로 넣어줌.
 만약에 그냥 포인터 연산 했으면, 이건 대입 연산자/ 복사 연산자 호출이 아니라 주소 대입이 될 듯. 따라서 그러면 안됨.
 */
Bizcard::~Bizcard(){//소멸자
    delete [] name;
    delete [] phone;
}
void Bizcard::ShowInfo(){
    cout<<"이름 : "<<name<<endl<<"전화번호"<<phone<<endl;
}
template <typename T>
class Bag{
private:
    T *array;
    int capacity;
    int top;
public:
    Bag (int bagCapacity=3);
    ~Bag();
    int Size() const; //사이즈 리턴
    bool IsEmpty() const; //비어있는지에 대한 확인
    T& Element() const; //rand함수로 top보다 작은위치에 아무거나 리턴함.
    void Push(const T&); //맨 뒤에 밀어 넣음. 단 자리가 없으면 사이즈를 2배 늘림.
    void Pop(); //
};
template <typename T>
Bag<T>::Bag (int bagCapacity):capacity ( bagCapacity ) { //구현완료
    if (capacity < 1) throw "Capacity must be > 0";
    array = new T[capacity];
    top = -1;
}
template <typename T>
Bag<T>::~Bag() { delete[ ] array; } //구현완료

template <typename T>
int Bag<T>::Size() const{ return top+1; } //구현완료

template <typename T>
bool Bag<T>::IsEmpty() const{ //구현완료
    if(top==-1) return true;
    else return false;
}
template <typename T>
T& Bag<T>::Element() const{ //구현완료
    if (IsEmpty()) throw "Bag is empty, cannot show anything";
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
    int deletePos = rand()%(top+1);
    copy(array+deletePos+1,array+top+1,array+deletePos);
    // compact array
    array[top--].~T();// destructor for T
}
int main(int argc, const char * argv[]) {
    Bag<Bizcard> bag;
    cout<<"bag Size: "<<bag.Size()<<endl<<(bag.IsEmpty()?"bag is empty":"bag is not empty")<<endl;
    try {
        bag.Element().ShowInfo();
    } catch (char const* e) {
        cout<<e<<endl;
    }
    cout<<"//push 3번 수행함"<<endl;
    bag.Push(*(new Bizcard("A","111-1111")));
    bag.Push(*(new Bizcard("B","222-2222")));
    bag.Push(*(new Bizcard("C","333-3333")));
    cout<<"bag Size: "<<bag.Size()<<endl<<(bag.IsEmpty()?"bag is empty":"bag is not empty")<<endl;
    try {
        bag.Element().ShowInfo();
    } catch (char const* e) {
        cout<<e<<endl;
    }
    cout<<"//push 4번 수행함"<<endl;
    bag.Push(*(new Bizcard("D","444-4444")));
    bag.Push(*(new Bizcard("E","555-5555")));
    bag.Push(*(new Bizcard("F","666-6666")));
    bag.Push(*(new Bizcard("G","777-7777")));
    cout<<"bag Size: "<<bag.Size()<<endl<<(bag.IsEmpty()?"bag is empty":"bag is not empty")<<endl;
    try {
        bag.Element().ShowInfo();
    } catch (char const* e) {
        cout<<e<<endl;
    }
    cout<<"//pop 2번 수행함"<<endl;
    try {
        bag.Pop();
        bag.Pop();
    } catch (char const* e) {
        cout<<e<<endl;
    }
    cout<<"bag Size: "<<bag.Size()<<endl<<(bag.IsEmpty()?"bag is empty":"bag is not empty")<<endl;
    try {
        bag.Element().ShowInfo();
    } catch (char const* e) {
        cout<<e<<endl;
    }
    bag.~Bag();
    return 0;
}

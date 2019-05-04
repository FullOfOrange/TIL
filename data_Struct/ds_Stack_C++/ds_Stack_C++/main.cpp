//
//  main.cpp
//  ds_Stack_C++
//
//  Created by 육진혁 on 25/04/2019.
//  Copyright © 2019 육진혁. All rights reserved.
//

#include <iostream>

template <typename T>
class Bag{
private:
    T *array;
    int capacity;
    int top;
public:
    Bag (int bagCapacity = 3);
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
template <typename T>
class Stack : public Bag<T>{
    
}
int main(int argc, const char * argv[]) {
    // insert code here...
    std::cout << "Hello, World!\n";
    return 0;
}

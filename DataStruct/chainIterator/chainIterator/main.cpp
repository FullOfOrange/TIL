//
//  main.cpp
//  chainIterator
//
//  Created by 육진혁 on 09/05/2019.
//  Copyright © 2019 육진혁. All rights reserved.
//

#include <iostream>

int main(int argc, const char * argv[]) {
    // insert code here...
    std::cout << "Hello, World!\n";
    return 0;
}
class ChainIterator {// 클래스 중첩 Iterator
private:
    ChainNode* current;
public:
    // typedefs required by C++ for a forward iterator omitted
    ChainIterator(ChainNode* startNode = 0) {current = startNode;}       // constructor
    // dereferencing operators
    T& operator*() const {return current->data;}//data의 값을 넘겨줌(레퍼런스 형태로)
    T* operator->() const {return &current->data;}//data의 주소를 넘겨줌
    // increment
    ChainIterator& operator++() {current = current->link; return *this;}  // preincrement
    ChainIterator operator++(int){ // postincrement 후위연산이다.
        ChainIterator old = *this;// 나랑 똑같은걸 하나 만들고,
        current = current->link;//현재 위치를 다음것으로 만들되,
        return old;//일단 복사된 내것을 리턴함.
    }
    // equality testing 그 외 다른것들을 대부분 정의 해주는게 좋음, 여기있는 비교 연산들은, 전부 주소값이 동일한지를 비교하는 것.
    bool operator!=(const ChainIterator right) const {return current != right.current;}
    bool operator==(const ChainIterator right) const {return current == right.current;}
    };

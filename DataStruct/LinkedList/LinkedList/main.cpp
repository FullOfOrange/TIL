//
//  main.cpp
//  LinkedList
//
//  Created by 육진혁 on 04/05/2019.
//  Copyright © 2019 육진혁. All rights reserved.
//
#include <iostream>

using namespace std;
/*Chain*/
//ChainNode를 Chain 클래스 내부에 정의, T에 대한 내부 클래스의 사용 방법은 아래와 같음. 동일한 형 사용할경우는 이런식으로 사용한다.
template <typename T>
class Chain {
private://이거 나중에 private 으로 바꾸기
    class ChainNode {//내부에서만 사용 가능한 ChainNode Class임
    public:
        T data;//여기서 T는 Chain 의 T임.
        ChainNode *link;
        ChainNode(const T& a,ChainNode* cn):data(a),link(cn){}
    };
    ChainNode *first;
    ChainNode *last;
public:
    Chain();
    ~Chain();
    
    T& Front() const;
    T& Back() const;
    T& Get(int i) const;
    
    void insertFront(const T& a);//맨 앞에 삽입.
    void insertBack(const T& a);
    void deleteFront();
    void deleteBack();
    void insertElement(const T& a,int i);
    void deleteElement(int i);
    
    class Iterator{
    private:
        ChainNode *current;
    public:
        Iterator(ChainNode* init = NULL);
        
        Iterator& operator++();//전위연산 정의, 이건 this를 리턴해서 여러번 쓸 수 있게 함.
        Iterator operator++(int);//후위연산 정의
        
        T& operator*() const;//값 넘겨줌
        T* operator->() const;//주소 넘겨줌
        
        bool operator==(const Iterator& right) const;//같은지비교
        bool operator!=(const Iterator& right) const;//같은지 비교
    };
    
    Iterator begin() {return Iterator(first);}
    Iterator end() {return Iterator();}
};
/*Chain 정의 영역*/
template <typename T>
Chain<T>::Chain() : first(NULL), last(NULL){ }
template <typename T>
Chain<T>::~Chain(){//완료
    if(first){
        ChainNode* current = first;
        while(current->link){
            current = first->link;
            first->~ChainNode();
            first = current;
        }
        current->~ChainNode();
    }
}
template <typename T>
T& Chain<T>::Front() const{//완료
    if(!first) throw "empty";
    return first->data;
}
template <typename T>
T& Chain<T>::Back() const{//완료
    if(!first) throw "empty";
    return last->data;
}
template <typename T>
T& Chain<T>::Get(int i) const{//완료
    if(!first) throw "empty";
    if(i<0) throw "wrong";
    ChainNode* current = first;
    while(i--){
        if(!current->link) break;
        current = current->link;
    }
    if(i)
        return current->data;
    else throw "too big number";
}
template <typename T>
void Chain<T>::insertFront(const T& a){//완료
    if(!first)
        first = last = new ChainNode(a,NULL);
    else
        first = new ChainNode(a,first);
}
template <typename T>
void Chain<T>::insertBack(const T& a) {//완료
    if(!first)
        first = last = new ChainNode(a,NULL);
    else{
        last->link = new ChainNode(a,NULL);
        last = last->link;
    }
}
template <typename T>
void Chain<T>::deleteFront(){//완료
    if(!first) throw "empty";
    if(first==last) last = NULL;
    ChainNode *temp = first;
    first = first->link;
    temp->~ChainNode();
}
template <typename T>
void Chain<T>::deleteBack(){//완료
    if(!first) throw "empty";
    ChainNode *current = first, *prev = NULL;
    while(current->link){
        prev = current;
        current = current->link;
    }
    if(prev){
        last = prev;
        last->link = NULL;
    }else first = NULL;
    current->~ChainNode();
}
template <typename T>
void Chain<T>::insertElement(const T& a,int i){//완료
    if(i<0) throw "wrong";
    ChainNode *current = first;
    if(first){
        while(i--){//i를 줄이되, 0이되면 종료된다.
            if(current==last) throw "too small";
            current = current->link;
        }
        current->link = new ChainNode(a,current->link);
    }else{
        first = last = new ChainNode(a,NULL);
    }
}
template <typename T>
void Chain<T>::deleteElement(int i){
    if(!first) throw "empty";
    if(i<0) throw "wrong";
    ChainNode *current = first, *prev = NULL;
    while(i--){
        if(!current->link) break;
        prev = current;
        current = current->link;
    }
    if(i>0) throw "too big number";
    if(first==last)
        first = last = NULL;
    else
        prev->link = current->link;
    current->~ChainNode();
}
/*Iterator(반복자) 정의영역.*/
template<typename T>
Chain<T>::Iterator::Iterator(ChainNode* init):current(init){}

template<typename T>
typename Chain<T>::Iterator& Chain<T>::Iterator::operator++(){//전위연산 정의, 이건 this를 리턴해서 여러번 쓸 수 있게 함.
    current = current->link;
    return *this;
}
template<typename T>
typename Chain<T>::Iterator Chain<T>::Iterator::operator++(int){//후위연산 정의
    Chain<T>::Iterator *temp = this;
    current = current->link;
    return *temp;
}
template<typename T>
T& Chain<T>::Iterator::operator*() const{//값 넘겨줌
    return current->data;
}
template<typename T>
T* Chain<T>::Iterator::operator->() const{//주소 넘겨줌
    return &current->data;
}
template <typename T>
bool Chain<T>::Iterator::operator==(const Chain<T>::Iterator& right) const{//같은지 비교 (주소에 대해서 비교하는 것이다.)
    return current == right.current;
}
template <typename T>
bool Chain<T>::Iterator::operator!=(const Chain<T>::Iterator& right) const{//같은지 비교
    return current != right.current;
}
class Number{//각 리스트에 추가할 Number객체임.
private:
    int num;
public:
    Number(int n=0): num(n){ cout<<"Number(int n="<<num<<")"<<endl; }
    Number& operator=(const Number& ref){
        cout<<"operator=()"<<endl;
        num=ref.num;
        return *this;
    }
    operator int (){ return num; }
    void ShowNumber() { cout<<num<<endl; }
};
//이런식으로 연산자를 오버로딩 해주면 일반적인 Std 템플릿을 가져다 그대로사용할. 수 있ㅇ므
/*Main*/
int main(int argc, const char * argv[]) {
    Chain<Number>* chain = new Chain<Number>;
    Chain<Number>::Iterator iter;
    try {
        printf("1. 1부터 9까지 출력\n");
        chain->insertBack(*(new Number(1)));
        chain->insertBack(*(new Number(2)));
        chain->insertBack(*(new Number(3)));
        chain->insertBack(*(new Number(4)));
        chain->insertBack(*(new Number(5)));
        chain->insertBack(*(new Number(6)));
        chain->insertBack(*(new Number(7)));
        chain->insertBack(*(new Number(8)));
        chain->insertBack(*(new Number(9)));
        printf("\n2.노드 출력\n");
        int sum = 0;
        for(iter = chain->begin(); iter!=chain->end(); iter++){
            printf("%d ",(int)*iter);
        }
        printf("\n\n3. 맨 앞에 0 삽입\n");
        chain->insertFront(*(new Number(0)));
        printf("\n4. 노드, 합 출력\n");
        for(iter = chain->begin(); iter!=chain->end(); iter++){
            printf("%d, ",(int)*iter);
            sum+=*iter;
        }
        printf("총합 : %d\n",sum);
        printf("\n5. 맨 뒤에 10 삽입\n");
        chain->insertBack(*(new Number(10)));
        printf("\n6. 노드, 합 출력\n");
        for(iter = chain->begin(), sum = 0; iter!=chain->end(); iter++){
            printf("%d, ",(int)*iter);
            sum+=*iter;
        }
        printf("총합 : %d\n",sum);
        printf("\n7. Front 호출\n");
        chain->Front().ShowNumber();
        printf("\n8. Back 호출\n");
        chain->Back().ShowNumber();
        printf("\n9. Get(2) 호출\n");
        chain->Get(2).ShowNumber();
        printf("\n10. 맨 앞 원소 삭제\n");
        chain->deleteFront();
        printf("\n11. 노드, 합 출력\n");
        for(iter = chain->begin(), sum = 0; iter!=chain->end(); iter++){
            printf("%d, ",(int)*iter);
            sum+=*iter;
        }
        printf("총합 : %d\n",sum);
        printf("\n12. 맨 뒤 원소 삭제\n");
        chain->deleteBack();
        printf("\n13. 노드, 합 출력\n");
        for(iter = chain->begin(), sum = 0; iter!=chain->end(); iter++){
            printf("%d, ",(int)*iter);
            sum+=*iter;
        }
        printf("총합 : %d\n",sum);
        printf("\n14. 2번째 노드 다음에 100 삽입\n");
        chain->insertElement(*(new Number(100)), 2);
        printf("\n15. 노드, 합 출력\n");
        for(iter = chain->begin(), sum = 0; iter!=chain->end(); iter++){
            printf("%d, ",(int)*iter);
            sum+=*iter;
        }
        printf("총합 : %d\n",sum);
        printf("\n16. 6번째 원소 삭제\n");
        chain->deleteElement(6);
        printf("\n17. 노드, 합 출력\n");
        for(iter = chain->begin(), sum = 0; iter!=chain->end(); iter++){
            printf("%d, ",(int)*iter);
            sum+=*iter;
        }
        printf("총합 : %d\n",sum);
    } catch (char const* e) {
        cout<<e<<endl;
    }
    chain->~Chain();
    return 0;
}

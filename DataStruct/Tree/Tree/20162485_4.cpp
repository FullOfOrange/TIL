#include <iostream>
#include <utility>
using namespace std;

template <typename K, typename E>
class Tree {
    class TreeNode{
    public:
        pair<K,E> data;
        TreeNode* leftChild;
        TreeNode* rightChild;
        TreeNode(const pair<K, E> a):data(a), leftChild(NULL), rightChild(NULL){}
    };
    TreeNode* root;
    void Print(TreeNode* a) const{ cout<<a->data.second; }
public:
    Tree():root(NULL){}
    ~Tree();
    TreeNode* getRoot() const{ return root; }
    void Insert(const pair<K,E>& a);//삽입
    void Inorder(TreeNode *Node);
    pair<K,E>* Get(const K& a);//특정 Key 출력
    bool Delete(const K& a);
};
template <typename K, typename E>
pair<K,E>* Tree<K,E>::Get(const K& a){
    TreeNode *current = root;
    while(current){
        if(current->data.first>a){
            current = current->leftChild;
        }else if(current->data.first<a){
            current = current->rightChild;
        }else return &(current->data);
    }
    return NULL;
}
template <typename K, typename E>
void Tree<K,E>::Insert(const pair<K,E>& a){
    if(!root){ root = new TreeNode(a); return; }
    TreeNode *current = root, *prev = NULL;
    while(current){
        prev = current;
        if(current->data.first>a.first){ current = current->leftChild;
        }else if(current->data.first<a.first){ current = current->rightChild;
        }else{
            current->data.second = a.second;
            return;
        }
    }
    current = new TreeNode(a);
    if(prev->data.first > a.first) prev->leftChild = current;
    else prev->rightChild = current;
}
template <typename K, typename E>
void Tree<K,E>::Inorder(TreeNode* currentNode){
    if (currentNode) {
        Inorder(currentNode->leftChild);
        Print(currentNode);
        Inorder(currentNode->rightChild);
    }
}
template <typename K, typename E>
bool Tree<K,E>::Delete(const K& a){
    TreeNode *current = root, *prev = NULL;
    while(current){
        if(current->data.first>a){
            prev = current; current = current->leftChild;
        }else if(current->data.first<a){
            prev = current; current = current->rightChild;
        }else break;
    }
    if(!current) return false;
    if(!current->leftChild&&!current->rightChild){
        if(prev->leftChild->data.first==current->data.first) prev->leftChild = NULL;
        else prev->rightChild = NULL;
        current->~TreeNode();
    }
    else if(current->leftChild&&current->rightChild){
        TreeNode *current_ = current->leftChild, *prev_ = NULL;
        while(current_->rightChild){
            prev_ = current_;
            current_ = current_->rightChild;
        }
        prev->leftChild = current_;
        prev_->rightChild = current_->leftChild;
        current_->rightChild = current->rightChild;
        current_->leftChild = current->leftChild;
        current->~TreeNode();
    }
    else {
        if(prev->leftChild->data.first==current->data.first) prev->leftChild = current->leftChild?current->leftChild:current->rightChild;
        else prev->rightChild = current->leftChild?current->leftChild:current->rightChild;
        current->~TreeNode();
    }
    return true;
}
int main(int argc, const char * argv[]) {
    Tree<int,int>* tree = new Tree<int, int>;
    
    tree->Insert(pair<int,int>(8,88));
    tree->Insert(pair<int,int>(4,44));
    tree->Insert(pair<int,int>(9,99));
    tree->Insert(pair<int,int>(2,22));
    tree->Insert(pair<int,int>(1,11));
    tree->Insert(pair<int,int>(6,66));
    tree->Insert(pair<int,int>(3,33));
    tree->Insert(pair<int,int>(5,55));
    tree->Insert(pair<int,int>(7,77));
    tree->Inorder(tree->getRoot());
    cout<<" //1. 원소 추가 & 2. 중위 순회 출력"<<endl;
    tree->Delete(4);
    tree->Inorder(tree->getRoot());
    cout<<" //3. pair(4,44) 삭제 & 4. 중위 순회 출력"<<endl;
    tree->Delete(5);
    tree->Inorder(tree->getRoot());
    cout<<" //5. pair(5,55) 삭제 & 6. 중위 순회 출력"<<endl;
    tree->Delete(2);
    tree->Inorder(tree->getRoot());
    cout<<" //7. pair(2,22) 삭제 & 8. 중위 순회 출력"<<endl;
    tree->Get(2)?cout<<tree->Get(2)->second:cout<<"no";
    cout<<" //9. pair(2,22) 있는지 확인 후 출력"<<endl;
    tree->Get(9)?cout<<tree->Get(9)->second:cout<<"no";
    cout<<" //10. pair(9,99) 있는지 확인 후 출력"<<endl;
    return 0;
}

#include <iostream>
#include <algorithm>

int main(int argc, const char * argv[]) {
    int a,temp1=0,temp2=-1,temp3=-2, big=0;
    scanf("%d",&a);
    int *tree = new int[a];
    for(int x=0; x<a; x++)
        tree[x]=0;
    tree[0]=-1;
    for(int i=0; i<a; i++){
        scanf("%d",&temp3);
        if(big<temp3)
            big=temp3;
        if(temp3==temp1){
            tree[temp2]=temp1;
        }
        temp1=temp2;
        temp2=temp3;
    }
    int *temp = new int[++big];
    std::copy(tree,tree+big,temp);
    printf("%d\n",big);
    for(int j=0; j<big; j++){
        printf("%d ",temp[j]);
    }
    return 0;
}

#include <iostream>
#include <string>

using namespace std;

int main(void){
	ios::sync_with_stdio(false);
    int n,m;
    scanf("%d %d",&n,&m);
    string *q = new string[n];
    for(int i = 0; i < n; i++){
        cin>>q[i];
    }
    string *a = new string[m];
    for(int j = 0; j < m; j++){
        cin>>a[j];
    }
    int check;
    for(int k = 0; k < m; k++){
        if(check = atoi(a[k].c_str())){
            cout<<q[check-1]<<endl;
        }else{
            int l = 0;
            while(a[k].compare(q[l])){
                l++;
            }
            cout<<l+1<<endl;
        }
    }
}

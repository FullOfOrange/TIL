#include <stdio.h>
#include <stdlib.h>

int e = 2147483647;

void A(int a, int n){
	if(a==1){
		if(e>n)
			e=n;	
	}else{
		if(a%2==0){
			A(a/2,n+1);
		}
		if(a%3==0){
			A(a/3,n+1);
		}
		if(a){
			A(a-1,n+1);
		}
	}
}

int main(void){
	int a;
	scanf("%d",&a);
	A(a,0);
	printf("%d\n",e);
	return 0;
}
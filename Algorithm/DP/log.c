#include <stdio.h>
int main(void){
	int n;
	scanf("%d",&n);
	int sum=0,a=0,i=0;
	while(n>sum){
		sum+=(++i);
	}
	int j=0;
	while(!(a>sum-n)){
		a+=(++j);
	}
	printf("%d %d",i,a-(sum-n));
	return 0;
}
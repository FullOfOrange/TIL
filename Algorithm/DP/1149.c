//1149번 문제

#include <stdio.h>
#include <stdlib.h>

int min(int *arr,int a){
	if(a==0)
		return (arr[1]>=arr[2]?arr[2]:arr[1]);
	else if(a==1)
		return (arr[0]>=arr[2]?arr[2]:arr[0]);
	else
		return (arr[0]>=arr[1]?arr[1]:arr[0]);
}
int min_three(int *arr){
	int f=0, s=1;
	if(arr[0]>arr[1])
		++f;
	if(arr[1]>arr[2])
		++s;
	if(f==s)
		return arr[f];
	else
		return (arr[0]>arr[2]?arr[(2)]:arr[(0)]);
}
int main(void){
	int n;
	scanf("%d", &n);
	int **arr,**answer;
	arr = (int**)malloc(sizeof(int*) * ++n);//여기서 n늘어남.
	answer = (int**)malloc(sizeof(int*) * n);
	int i,j;
	for(i = 0; i < n; i++){
		arr[i] = (int*)malloc(sizeof(int)*3);
		answer[i] = (int*)malloc(sizeof(int)*3);
	}
	for(i = 0; i < 3; i++){
		arr[0][i]=0;
		answer[0][i]=0; 
	}
	for(i = 1; i < n; i++){
		scanf("%d %d %d",&arr[i][0],&arr[i][1],&arr[i][2]);
	}
	for(i = 1; i < n; i++){
		for(j = 0; j < 3; j++){
			answer[i][j]=arr[i][j]+min(answer[(i-1)],j);
		}
	}
	printf("%d",min_three(answer[n-1]));
	return 0;
}
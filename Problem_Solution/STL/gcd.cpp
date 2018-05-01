#include<stdio.h>
#include<iostream>

using namespace std;
int GCD(int x, int y);
int main(){
  int x,y;
  scanf("%d%d",&x,&y);
  printf("%d\n",GCD(x,y));
}

int GCD(int x, int y){
  if(y==0){
    return x;
  }
  return GCD(y,(x%y));
}

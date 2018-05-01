#include<stdio.h>

int main(){
  int x,y;
  scanf("%d%d",&x,&y);
  for(;;){
    if(x==y) break;
    else{
      x = x - y;
    }
  }
  printf("%d\n",x);
  return 0;
}

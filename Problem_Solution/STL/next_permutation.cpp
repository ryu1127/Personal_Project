#include<iostream>
#include<algorithm>

using namespace std;

int main(){
  int A[] = {0,1,2,3};
  do {
    for(int i=0;i<4;i++) cout << A[i] << " ";
    cout << endl;
  }while(next_permutation(A,A+4));
  cout << endl;
  A[0] = 2; A[1] = 1; A[2] = 0;
  do{
    for(int i =0; i<3;i++) cout << A[i] << " ";
    cout << endl;
  }while(prev_permutation(A,A+4));
  return 0;
}

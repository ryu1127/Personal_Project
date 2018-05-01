#include<iostream>

using namespace std;

template <typename T> const T& mas(const T& x,const T& y)
{
  return (y < x) ? x : y;
}

int main(){
  cout << mas(3,7) << endl;   //max로 함수 이름을 정하면 g++에서
  cout << mas(3.0,7.5) << endl; //함수 이름이 ambiguous하다고 한다.
  cout << mas<double>(3,7.5) << endl;
  return 0;
}

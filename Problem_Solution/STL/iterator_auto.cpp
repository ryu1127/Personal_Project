#include<iostream>
#include<list>

using namespace std;

int main(){
  list<int> A;
  A.push_back(1); A.push_back(2); A.push_back(3);
  for(auto a: A) cout << a << endl; // A에서 한개씩 꺼내서 출력
  return 0;
}

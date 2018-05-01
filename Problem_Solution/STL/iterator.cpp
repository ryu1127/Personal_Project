#include<iostream>
#include<list>

using namespace std;

int main(){
  list<int> A;
  list<int>::iterator it;
  A.push_back(1); A.push_back(2); A.push_back(3);
  for(it = A.begin(); it != A.end();it++){
    cout << *it << endl; //it는 포인터와 같으므로 *로 값을 표기
  }
  it = A.end(); it--;
  while(it != A.begin()){
    cout << *it << endl; // 뒤부터 앞으로 출력
    it--;
  }
  cout << *it << endl; //it = A.begin()일때 한번 더 출력
  return 0;
}

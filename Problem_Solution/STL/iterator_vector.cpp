#include<iostream>
#include<vector>

using namespace std;

int main(){
  vector<int> A;
  vector<int>::iterator r,s,it;
  A.push_back(1); A.push_back(2); A.push_back(3);
  // r = A.begin(); s = A.end();
  // cout << *r << endl;         //A.begin()이 가리키는 것 출력
  // cout << *(r+2) << endl;     //r[2]에 있는 값 출력
  // cout << *(--s) << endl;     //A.end() 한칸 앞 출력
  // cout << (r > s) << endl;    // r이 작으니까 false 출력 예상
  // // 실제로는 0을 출력함

  for(it = A.begin(); it != A.end(); it++)
    cout << *it << endl;
  for(int i=0;i<A.size();i++)
    cout << A[i] <<endl;      //두개 모두 같은 결과 출력

  return 0;
}

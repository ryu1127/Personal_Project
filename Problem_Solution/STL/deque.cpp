#include<iostream>
#include<deque>

using namespace std;

int main(){
  deque<double> dq;
  dq.push_back(1.1); dq.push_front(2.2); dq.push_front(3.3);

  //3.3 2.2 1.1
  for(auto i=0;i<dq.size(); i++)
    cout << dq[i] << endl;

    dq.pop_front();
    cout << dq[0] << endl;
    return 0;
}

#include<iostream>
#include<list>

using namespace std;

int main(){
  list<int> lst;
  list<int>::iterator it;
  lst.push_back(1); lst.push_back(2); lst.push_back(3);
  for(it = lst.begin();it!=lst.end();it++) // 1 2 3
  cout<<*it<< endl;
  it = lst.begin(); it++; lst.erase(it);
  for(it = lst.begin();it != lst.end();it++) // 1 3
  cout << *it << endl;
  it = lst.begin(); it++; lst.insert(it,10);
  for(it = lst.begin(); it != lst.end(); it++) // 1 10 3
  cout << *it << endl;
  return 0;
}

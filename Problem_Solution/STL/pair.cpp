#include<iostream>

  using namespace std;

int main(){
  pair<int, int> foo;
  foo = make_pair(10,20);
  cout << "foo : " << foo.first << ", " << foo.second << endl;
  return 0;
}

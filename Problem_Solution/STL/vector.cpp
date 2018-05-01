#include<iostream>
#include<vector>

using namespace std;

int main(){
  vector<int> v1;
  vector<int> v2(5);
  vector<int> v3(5,-1); //int v3[5] = {-1,-1,-1,-1,-1};

  cout << v1.size() << endl;
  cout << v2.size() << endl;
  cout << v3.size() << endl;
  for(auto i=0;i<v2.size();i++)
    cout << v2[i] << endl;
  for(auto i=0;i<v3.size();i++)
    cout << v3[i] << endl;
  return 0;
}

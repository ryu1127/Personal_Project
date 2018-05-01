#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;

int main(){
  int myints[] = {1,2,3,4,4,4,4,10,11,15};
  vector<int> v;
  vector<int>::iterator low, hi;
  for(auto temp : myints) v.push_back(temp);
  sort(v.begin(),v.end());
  low = lower_bound(v.begin(), v.end(), 3);
  hi = upper_bound(v.begin(), v.end(), 4);
  cout << (low - v.begin()) << endl;//3
  cout << (hi - v.begin()) << endl; //7
  return 0;

}

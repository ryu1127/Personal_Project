#include<iostream>
#include<set>

using namespace std;

class Student{
public:
  double height, weight;

  bool operator<(const Student &s) const {
    if(height != s.height) return height < s.height;
    return weight < s.weight;
  }
};

int main(){
  // char str[] = {'a', 'c', 'e', 'b', 'd', 'f', 'g'};
  // set<char> S; set<char>::iterator it;
  // for(int i=0;i<sizeof(str)/sizeof(char);i++)
  //   S.insert(str[i]);
  //
  // for(it = S.begin();it!=S.end(); it++) // abcdefg
  //   cout << *it << endl;
  // it = S.find('d'); S.erase(it);
  // S.erase('a');
  // for(it = S.begin(); it != S.end(); it++) // bcefg
  //   cout << *it << endl;

  set<Student> S;
  set<Student>::iterator it;
  Student e;

  e.height = 150; e.weight = 50; S.insert(e);
  e.height = 180; e.weight = 80; S.insert(e);
  e.height = 150; e.weight = 45; S.insert(e);

  for(it = S.begin(); it != S.end(); it++) // (150,45), (150, 50), (180,80)
    cout << "(" << it->height << ", "<< it->weight<<")" << endl;

  return 0;
}

#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;

typedef struct { int x, y;} point;

class less_point{
public:
  bool operator() (const point &p1, const point &p2){
    if(p1.x != p2.x) return p1.x < p2.x;
    return p1.y < p2.y;
  }
};

int main(){
  vector<point> PV;
  point p;
  p.x = 5; p.y = 0; PV.push_back(p);
  p.x = 2; p.y = 1; PV.push_back(p);
  p.x = 3; p.y = 2; PV.push_back(p);
  p.x = 1; p.y = 3; PV.push_back(p);
  p.x = 4; p.y = 4; PV.push_back(p);

  sort(PV.begin(), PV.end(), less_point());

  //(1,3),(2,1), (3,2), (4,4), (5,0)
  for(auto tmp: PV)
  cout << "(" << tmp.x << ","<<tmp.y << ")\n";
  return 0;

}

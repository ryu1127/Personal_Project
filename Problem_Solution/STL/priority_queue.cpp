#include<iostream>
#include<queue>

using namespace std;

class point{
public:
  int x,y;

  bool  operator<(const point &p) const {
    if(x != p.x) return x < p.x;
    return y < p.y;
  }
};

int main(){
  priority_queue<point> PQ;
  point p;

  p.x = 0; p.y = 0; PQ.push(p);
  p.x = 1; p.y = 2; PQ.push(p);
  p.x = 1; p.y = 3; PQ.push(p);

  while(!PQ.empty()){
    p = PQ.top();
    cout << "(" << p.x << ", " << p.y << ")" << endl;
    PQ.pop();
  }
  return 0;
}

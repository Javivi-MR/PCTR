#include <algorithm>
#include <chrono>
#include <iostream>
#include<vector>
using namespace std;
using namespace std::chrono;

vector<int> valores(10000000);
  
int main(){
  
    auto f = []() -> int{return rand() % 10000;};
    generate(valores.begin(), valores.end(), f);
  
    auto iniciar = high_resolution_clock::now();
    sort(valores.begin(), valores.end());
    auto acabar = high_resolution_clock::now();
    auto lapso  = duration_cast<microseconds>(acabar - iniciar);
  
    cout << "Ejecucion en: "  << lapso.count() << " microsegundos" << endl;
  
    return(0);
}
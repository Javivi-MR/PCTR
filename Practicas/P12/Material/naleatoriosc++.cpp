#include <iostream>
#include <cstdlib>  //tiene la definicion de la funcion rand
using std::cout;
using std::endl;
using std::cin;

int main (){
  int cantidad;
  cout << "Cuantos numeros generamos?" << endl;
  cin >> cantidad;
  for (int contador = 1 ; contador <= cantidad ; contador++){
      cout << (1 + rand() % 20);  
      cout << endl;                     
  }
  return 0 ;
} 
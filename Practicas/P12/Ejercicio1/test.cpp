#include <thread>
#include <iostream>

using namespace std;

int main(){
    cout << "Numero de hilos: " << thread::hardware_concurrency() << "\n";
    return(0);
}
//hebras en C++ que procesan funciones con carga parametrica...
//17/01/2017
//A.T.

#include<iostream>
#include <thread>
#include <mutex>

using namespace std;

int totalHits = 0;
mutex m;

void hola(int nPuntos) //codigo a ejecutar por la hebras con parametros
{
    int hits = 0;
    for(int i = 0 ; i < nPuntos ; i++){
        hits++;
    }
    m.lock();
    totalHits += hits;
    m.unlock();
}
int main()
{ 
    int nHilos = 4;
    int nPuntos = 1000000;
    thread hilos[nHilos];

    for(int i=0; i<nHilos; i++)hilos[i]=thread (hola,nPuntos); //las hebras se crean y ejecutan de esta forma
    for(int i=0; i<nHilos; i++)hilos[i].join();

    cout << "Se ha obtendido: " << totalHits << "\n";
    return(0);
}
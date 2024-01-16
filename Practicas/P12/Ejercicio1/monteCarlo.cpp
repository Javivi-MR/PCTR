#include<iostream>
#include <thread>
#include <mutex>
#include <random>
#include <math.h>

using namespace std;

int totalHits = 0;
mutex m;

//la integral que se desea calcular es la siguiente: x^3/(x^4 + 2)
void IntegrarPtos(int nPuntos) //codigo a ejecutar por la hebras con parametros
{
    random_device rd;
    mt19937 gen(rd());
    uniform_real_distribution<> dis(0, 1); //generador de ptos de 0 a 1
    int hits = 0;
    double x, y;
    for(int i = 0 ; i < nPuntos ; i++){
        x = dis(gen);
        y = dis(gen);
        if(y <= (pow(x,3))/(pow(x,4) + 2))
            hits++;
    }
    m.lock();
    totalHits += hits;
    m.unlock();
}

int main()
{ 
    int nHilos;
    cout << "Introduce el numero de hilos (0 para auto): ";
    cin >> nHilos;
    if(nHilos == 0)
        nHilos = thread::hardware_concurrency(); //numero de hilos del sistema
    int nPuntos;
    cout << "Introduce el numero de puntos: ";
    cin >> nPuntos;
    thread hilos[nHilos];

    for(int i=0; i<nHilos; i++)hilos[i]=thread (IntegrarPtos,nPuntos);
    for(int i=0; i<nHilos; i++)hilos[i].join();

    cout << "Se ha obtendido: " << (double) totalHits/(nPuntos*nHilos) << "\n";
    return(0);
}
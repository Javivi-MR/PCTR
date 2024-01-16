#include <iostream>
#include <thread>
#include <mutex>
#include <chrono>
#define dim 40000000

float   a[dim];
float   b[dim];
double  p[4]       = {0, 0, 0, 0}; 
double  pEscalar   =           0;
 
void f(long ini, long fin){
  for(long i=ini; i<=fin;i++){
    pEscalar=pEscalar+(a[i]*b[i]);
  }
}

void g(int id, long ini, long fin){
  for(long i=ini; i<=fin;i++){
    p[id]=p[id]+(a[i]*b[i]);
  }
}
  
int main(){

   for (long i=0; i<dim; i++){a[i] = 1; b[i] = 1;} 

   //system_clock o high_resolution_clock para mas precision si se desea
   auto iniciar=std::chrono::system_clock::now();
   std::thread t(f, 0, dim-1);
   t.join();
   auto acabar=std::chrono::system_clock::now();
   auto lapso1 = std::chrono::duration_cast<std::chrono::milliseconds>(acabar - iniciar);
   std::cout << "Secuencial en " << lapso1.count() << " milisegundos, con resultado "<< pEscalar << "\n";

   pEscalar = 0;

   iniciar=std::chrono::system_clock::now();
   std::thread t0(g, 0,        0,  9999999);
   std::thread t1(g, 1, 10000000, 19999999);
   std::thread t2(g, 2, 20000000, 29999999);
   std::thread t3(g, 3, 30000000, 39999999);
   t0.join(); t1.join(); t2.join(); t3.join();
   pEscalar = p[0]+p[1]+p[2]+p[3];
   acabar=std::chrono::system_clock::now();
   auto lapso2 = std::chrono::duration_cast<std::chrono::milliseconds>(acabar - iniciar);
   std::cout << "Paralelo en   " << lapso2.count() << " milisegundos, con resultado " << pEscalar << "\n";

   return(0);
}
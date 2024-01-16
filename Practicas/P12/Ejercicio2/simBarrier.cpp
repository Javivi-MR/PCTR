//hay que implementar un monitor 
#include <iostream>
#include <string>
#include <thread>
#include <mutex>
#include <condition_variable>



class simBarrier {
    private:
        int nThreads;
        int count;
        std::mutex m;
        std::condition_variable cv;

    public:
        simBarrier(int nThreads) : nThreads(nThreads), count(0) {}
        void toWaitOnBarrier();
        void resetBarrier();
};

void simBarrier::toWaitOnBarrier(){
    std::unique_lock<std::mutex> lck(m);
    count++;
    while(count < nThreads){
        cv.wait(lck);
    }
    cv.notify_all();
}

void simBarrier::resetBarrier(){
    std::unique_lock<std::mutex> lck(m);
    count = 0;
}


simBarrier b(3);
std::mutex salida; //para que no se mezclen las salidas de los hilos

void hacerAlgo(){
    std::unique_lock<std::mutex> lck(salida);
    std::cout<< std::this_thread::get_id() << " llegando a barrera...\n";
    b.toWaitOnBarrier();
    std::cout<< std::this_thread::get_id() << " saliendo de barrera...\n";
}

int main(){
    std::cout << "main creando barrera para tres hebras...\n";
    std::thread th1 = std::thread(hacerAlgo);
    std::thread th2 = std::thread(hacerAlgo);
    std::thread th3 = std::thread(hacerAlgo);
    th1.join(); th2.join(); th3.join();
    std::cout << "main reseteando barrera para tres nuevas hebras...\n";
    b.resetBarrier();
    std::thread th4 = std::thread(hacerAlgo);
    std::thread th5 = std::thread(hacerAlgo);
    std::thread th6 = std::thread(hacerAlgo);

    return(0);
}
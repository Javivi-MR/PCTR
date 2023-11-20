/* Copyright (C) 2006 M. Ben-Ari. See copyright.txt */
class PCMonitor {
	final int N = 5; // Tamaño del buffer
	int Oldest = 0, Newest = 0; // indices del buffer
  volatile int Count = 0; // contador de elementos en el buffer
	int Buffer[] = new int[N]; // buffer

	public synchronized void Append(int V) { // Producir un elemento
    while (Count == N) 
      try {
         wait();
      } catch (InterruptedException e) {}
		Buffer[Newest] = V;
		Newest = (Newest + 1) % N;
		Count = Count + 1;
		notifyAll();
		System.out.println("Productor ha producido: " + V + " Estado Buffer: " + Count);
	}

	public synchronized int Take() { // Consumir un elemento
		int temp;
    while (Count == 0)
      try {
         wait();
      } catch (InterruptedException e) {}
	  	System.out.println("Consumidor ha consumido: " + Buffer[Oldest]);
		temp = Buffer[Oldest];
		Oldest = (Oldest + 1) % N;
		Count = Count - 1;
		notifyAll();
		return temp;
	}
}

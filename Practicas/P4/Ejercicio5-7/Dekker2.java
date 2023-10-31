/* Copyright (C) 2006 M. Ben-Ari. See copyright.txt */
/* Programmed by Panu Pitkämäki */

/* Dekker's algorithm */

import java.util.concurrent.ExecutionException;

class Dekker2 {
    /* Number of processes currently in critical section */
    static volatile int inCS = 0;

    static volatile int n = 10000;

    public static int v = 0;

    /* Process p wants to enter critical section */
    static volatile boolean wantp = false;
    /* Process q wants to enter critical section */    
    static volatile boolean wantq = false;
    /* Which processes turn is it */
    static volatile int turn = 1;

    class P extends Thread {
        public void run() {
            for(int i = 0 ; i < 10000 ; i++) {
                /* Non-critical section */
                wantp = true;
                while (wantq) {
                    if (turn == 2) {
                        wantp = false;
                        while (turn == 2)
                            Thread.yield();
                        wantp = true;
                    }
                }
                inCS++;
                Thread.yield();
                /* Critical section */
                System.out.println("Number of processes in critical section: "
                        + inCS);
                inCS--;
                v++;
                turn = 2;
                wantp = false;
            }
        }
    }
    
    class Q extends Thread {
        public void run() {
            for(int i = 0 ; i < 10000 ; i++) {
                /* Non-critical section */
                wantq = true;
                while (wantp) {
                    if (turn == 1) {
                        wantq = false;
                        while (turn == 1)
                            Thread.yield();
                        wantq = true;
                    }
                }
                inCS++;
                Thread.yield();
                /* Critical section */
                System.out.println("Number of processes in critical section: "
                        + inCS);
                inCS--;
                v++;
                turn = 1;
                wantq = false;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Dekker2 dekker = new Dekker2();
        P p = dekker.new P();
        Q q = dekker.new Q();

        p.start();
        q.start();
        p.join();
        q.join();

        System.out.println("Valor final de v: " + v);
    }
} //Probar con start /Affinity 1 java Dekker2
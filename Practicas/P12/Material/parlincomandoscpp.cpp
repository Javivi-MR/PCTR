#include <stdio.h>

int main(int argc, char *argv[])
{
   int edad;

   if (argc==1)
   {
       printf("Debes ingresar mas parametros...\n");
       return 1;
   }

   printf("Hola %s, cual es tu edad: ", argv[1]);
   scanf("%d", &edad);

   printf("Muy bien %s, es un gusto saber que tienes %d de edad...\n", argv[1], edad); 

   printf("Fin del programa %s...\n\n", argv[0]);

   return 0;   
} 

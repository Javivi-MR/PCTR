Procedimiento Monte-Carlo (n)
contador_exitos <- 0
Para i <- 0 Hasta n Con Paso 1 Hacer
  coordenada_x <- aleatorio (0,1)
  coordenada_y <- aleatorio (0,1)
Si coordenada_y <= f(coordenada_x)
  contador_exitos <- contador_exitos + 1
Fin Si
Fin Para
Escribir “Integral aproximada: “, (contador_exitos/n)
Fin Procedimiento
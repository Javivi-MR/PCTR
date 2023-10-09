Procedimiento Newton-Raphson (x0, iteraciones)
xN <- x0
Para i <- 0 Hasta iteraciones Con Paso 1 Hacer
  Si f’(xN) <> 0
    xN1 = xN – f(xN) / f’(xN)
    Escribir “Iteración: “, i, “ Aproximación: “, xN1
  xN <- xN1
 Fin Si
Fin Para
Escribir “Resultado: “, xN
Fin Procedimiento
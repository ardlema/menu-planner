# Planificador de menus

Este proyecto ha sido creado para facilitar la creación de menus semanales. A partir de dos ficheros de texto
que deben contener platos para las comidas y las cenas, la aplicación hace una selección aleatoria de los platos
y envia un mensaje de correo electrónico con dicha selección.

Las entradas del fichero de texto deben cumplir el siguiente formato:

Descripción del plato|Ingredientes|Tipo del plato

Donde tipo del plato debe ser uno de los siguientes valores:

  * L => Legumbres
  * CE => Cereales
  * V => Verduras
  * C => Carne
  * P => Pescado
  * D => Detox
  * H => Huevos
  * O => Otros

Con el proyecto se incluyen un par de ficheros de texto con ejemplos de platos para comidas y cenas.  

## Probando y ejecutando la aplicación
  
Para ejecutar los tests automáticos ejecutar:
  
  ```
  sbt test
  ```

Para compilar y generar un fat jar:
  
  ```
  sbt assembly
  ```

Si se quiere programar la aplicación para ser ejecutada de forma periódica, se recomienda crear un cron job 
para realizar dicha ejecución. Con el proyecto se incluye un sencillo script (mailscheduler.sh) que puede
ser llamado a través de un cron job. A continuación se incluye un ejemplo de cron job que ejecutará la
aplicación todos los jueves a las 17:15:

```
15 17 * * 4 bash /home/arodriguez/dev/menu-planner/mailscheduler.sh >> /home/arodriguez/output.log 2>&1
``` 
  
## TODO

* Permitir definir la localización de los ficheros de texto via parámetro de linea de comandos
* Permitir definir correo electrónico y password de envio y correos electrónicos de recepción a través de
 parámetros de linea de comandos
* Evitar incluir platos seleccionados la semana previa 


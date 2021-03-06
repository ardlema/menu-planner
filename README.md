# Planificador de menus

[![Build Status](https://travis-ci.org/ardlema/menu-planner.svg?branch=master)](https://travis-ci.org/ardlema/menu-planner)[![Coverage Status](https://coveralls.io/repos/github/ardlema/menu-planner/badge.svg?branch=master)](https://coveralls.io/github/ardlema/menu-planner?branch=master)

Este proyecto ha sido creado para facilitar la creación de menus semanales. A partir de dos ficheros de texto
que deben contener platos para las comidas y las cenas, la aplicación hace una selección aleatoria de los platos (opcionalmente
descarta los platos seleccionados la semana previa) y envia un mensaje de correo electrónico con dicha selección. 

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
  
Para ejecutar la aplicación:
  

```
java -jar ./target/scala-2.11/menu-planner-assembly-0.0.1.jar sender=user1@gmail.com password=12345 recipients=user2@gmail.com,user3@gmail.com rootpath=/home/user/menu-planner/ previousrootpath=/home/arodriguez/dev/menu-planner/src/main/resources/previous/ org.ardlema.executor.Executor  
```

Donde:

* sender: dirección de correo electrónico de la persona que enviara el mail (es necesario una cuenta de gmail)
* password: contraseña del correo electrónico de la persona que enviará el mail
* recipients: direcciones de correo electrónico de las personas que recibiran la planificación de menús
* roopath: ruta donde se guardaran los ficheros con las comidas y cenas a seleccionar, dichos ficheros se deben llamar: dinners.txt y lunches.txt
* previousrootpath: ruta donde se guardaran los ficheros con las comidas y cenas de la semana previa

Si se quiere programar la aplicación para ser ejecutada de forma periódica, se recomienda crear un cron job 
para realizar dicha ejecución. Con el proyecto se incluye un sencillo script (mailscheduler.sh) que puede
ser llamado a través de un cron job. A continuación se incluye un ejemplo de cron job que ejecutará la
aplicación todos los jueves a las 17:15:

```
15 17 * * 4 bash /home/user/dev/menu-planner/mailscheduler.sh >> /home/user/menu-planner-output.log 2>&1
``` 


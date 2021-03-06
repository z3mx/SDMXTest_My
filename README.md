# SDMX Test
Este proyecto es una prueba tecnica para aspirantes a trabajar en SinDelantal.Mx

## Prerequisitos para ejecutar el proyecto
+ [Git](http://git-scm.com/) 
+ [Java](https://www.oracle.com/java/index.html) >= 1.8
+ [Maven](https://maven.apache.org/) >= 3.3.9
+ [MySql](https://www.mysql.com/)

## Preparar base de datos

Modificar las configuraciones de BD en application.properties para poder establecer la conexion.

Las tablas e información necesaria seran creadas en el Command Line Runner.

## Limpiar, compilar y ejecutar

`mvn clean`

`mvn compile`

`mvn spring-boot:run`


# Prueba

La prueba esta hecha para ser tomada de forma remota. El aspirante debe de resolver los 
siquientes problemas dentro de el proyecto:

1. Existe un bug en el endpoint /api/restaurant/search, determinar cual es el problema y solucionarlo.

2. Crear un nuevo endpoint o funcionalidad para listar Restaurantes ordenados por su rating, los de mas alto rating en posiciones superiores.

3. Crear un nuevo endpoint o funcionalidad para listar Restaurantes ordenados por su rating y porcentaje de ordenes canceladas, el factor mas fuerte es el rating sin embargo
el porcentaje de pedidos cancelados modifica la posicion del restaurante.

Para ver un ejemplo de una llamada a un endpoint se puede navegar a:

http://localhost:8080/restaurant/list

## Criterios a evaluar

El codigo creado por el aspirante sera evaluado con tres criterios:

- Simplicidad.

- Elegancia.

- Funcionalidad.

## Entrega de la evaluación.

El flujo para realizar la evaluación es:

1. Hacer un fork del proyecto.

2. Crear un nuevo branch en el cual se implementaran las soluciones.

3. Una vez listo para revisión enviar a el contacto el remote/branch para que se haga la revisión.

__Importante:__ No crear pull request para los cambios.

# Justificacion y desarrollo de entrega evaluacion 1 

## Resumen

La entrega consta de una api sencilla. Esta fue desarrollada bajo la estrategia de desarollo trunk-based principalmente debido a la simplicidad del proyecto, sin embargo hay otros factores que se consideraron a la hora de la eleccion, por ejemplo la alineacion con la cultura de integracion y entrega continua (CI/CD) que se requeria para esta entrega. De esta manera los avances fueron pequeños, los cuales despues de ser subidos (pusheados) a su branch correspondiente se mergearon a la rama principal. 
Ademas esta metodologia de trabajo disminuye los conflictos de fusion masivos (merges).

**Tecnologías:**
* Springboot 4.1.1
* java 21
* Maven

**dependencias:**
* Sporing web
* Spring data jpa
* H2 database
* Validation

## Sobre las branches

Se mantuvo el orden requerido para la entrega desarrollando las branches en 'funcionlidades'. Todas tienen el nombre *feature* seguido de lo que se desarolló por ejemplo *feature/modelo-producto* 


## Sobre automatizacion (CI/CD)

El rol de la automatizacion dentro de un proceso de CI/CD a la hora de desarrollar software es actuar como el motor y el orquestador invisible que se encarga de ejecutar las tareas repetitivas de forma autonoma, es decir sin intervencion humana directa. En este caso el proceso que se automatizo es super sencillo:

1. Verifica el contenido del repositorio
2. Verificar la version de Node
3. Configura Java JDK
4. Ejecuta comandos de prueba
5. Compila e instala dependencias con Maven


# API REST Spring Boot - Proyecto Evaluación DevOps

Este repositorio contiene una API REST en Spring Boot (Java 21)[cite: 2] con persistencia en MySQL 8, configurada e integrada bajo buenas prácticas de DevOps que incluyen containerización con Docker[cite: 2], orquestación local con Docker Compose (orden de arranque, healthcheck y volúmenes), y automatización de integración continua (CI) mediante GitHub Actions.

---

## Requisitos Previos

Para ejecutar y probar este proyecto de forma local necesitarás:

- [Git](https://git-scm.com/)
- [Docker Engine](https://docs.docker.com/get-docker/) y [Docker Compose](https://docs.docker.com/compose/) (v2+)
- [Java OpenJDK 21](https://adoptium.net/) y [Apache Maven 3.9+](https://maven.apache.org/) (opcional si deseas ejecutar el proyecto sin contenedores)

---

## Configuración de Variables de Entorno

El proyecto utiliza un archivo de variables de entorno `.env` para evitar exponer credenciales en el código fuente.

1. Crea un archivo `.env` en la raíz del proyecto basándote en la siguiente estructura[cite: 3]:

```env
# Variables MySQL
MYSQL_ROOT_PASSWORD=root_password
MYSQL_DATABASE=mydatabase
MYSQL_USER=myuser
MYSQL_PASSWORD=mypassword

# Puerto backend
BACKEND_PORT=8080
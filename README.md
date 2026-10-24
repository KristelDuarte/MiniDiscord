# Mini-Discord

Proyecto de práctica de Programación III (EIF206), Universidad Nacional. Es un chat hecho con JavaFX y Maven, y los mensajes se guardan en una base de datos MySQL usando JDBC.

No es el Proyecto 1 ni el Proyecto 2 del curso: es el código con el que practicamos en clase, y va cambiando de una semana a otra.

## Qué necesita

- JDK 21 o superior (se probó con JDK 25).
- MySQL Server y MySQL Workbench.
- IntelliJ IDEA, o simplemente una terminal: el proyecto trae `mvnw` (Maven Wrapper), no hace falta instalar Maven.

## Preparar la base de datos

La base se crea en MySQL Workbench, no desde el código. Los scripts (esquema, datos de prueba y usuario de la aplicación) y la guía paso a paso están en el aula virtual.

Cuando la base exista, copie `config.example.properties` como `config.properties` en la raíz del proyecto y escriba ahí el usuario y la clave. Ese archivo no se sube a Git.

## Cómo correrlo

Desde IntelliJ, abra el proyecto como proyecto Maven y ejecute `App.java`.

Desde una terminal, en la raíz del proyecto:

```bash
# Windows
.\mvnw.cmd javafx:run

# Linux o Mac
./mvnw javafx:run
```

## Estructura

```
src/main/java/cr/ac/una/est/minidiscord/
├── App.java            arranque de la aplicación
├── business/           model (Message, User), service (ChatService) y dto
├── data/dao/           MessageRepository, JdbcMessageRepository, DatabaseConfig y las clases de la semana 8
└── presentation/       controller (ChatViewController) y component (MessageCell, Avatars)
```

El diseño de la ventana está en `src/main/resources/.../presentation/view/`: `chat-view.fxml` (la estructura) y `discord.css` (los colores y el aspecto).

## Para trabajar con el proyecto

1. Haga un fork del repositorio con el botón Fork de GitHub. Deje marcada la casilla de copiar solo la rama `main`.
2. Clone su fork (la URL con su usuario, no la mía).
3. Para recibir cambios nuevos: en la página de su fork, Sync fork y Update branch; después `git pull` en su computadora.

También puede descargar el proyecto con Code, Download ZIP.

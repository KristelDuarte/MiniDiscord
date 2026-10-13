# Mini-Discord — Programación III

Proyecto de práctica que construimos clase a clase en el laboratorio, aplicando en código real
cada tema del curso (persistencia, hilos, bases de datos, etc.). **No es el Proyecto 1** que se
entrega como evaluación — es el código guía que evoluciona semana a semana.

Aplicación de chat local hecha con **JavaFX + Maven**, empaquetada como módulo Java (JPMS).

## Estado actual: Semana 13 — MySQL, interfaz tipo Discord

- Persistencia en **MySQL** por JDBC (`data/dao/JdbcMessageRepository.java`) detrás de la interfaz `MessageRepository`.
- Credenciales fuera del código: se leen de `config.properties` (no se sube a Git) mediante `DatabaseConfig`.
- Interfaz con estilo Discord: hoja de estilos `discord.css`, celdas de mensaje con avatar, autor y hora, y diálogo para elegir el nombre de usuario.
- Persistencia binaria y XML (Semana 8) se conservan como referencia del patrón DAO.

### Preparar la base de datos

La base de datos se prepara **en MySQL Workbench**, no desde el código Java. La profesora entrega los scripts SQL en un documento aparte.
Al terminar, copie `config.example.properties` como `config.properties` (en la raíz del proyecto) y escriba ahí el usuario y la clave de la base.

## Semana 8 — Persistencia completada

- **Parte A** — Persistencia binaria (`data/dao/MessageDAO.java`) con `ObjectOutputStream`/`ObjectInputStream` sobre `mensajes.dat`.
- **Parte B** — Exportación a XML con DTO (`business/dto/MessageDTO.java` + `data/dao/MessageXmlExporter.java`) usando `XMLEncoder`.
- **Parte C** — Patrón Singleton (`data/dao/PersistenceManager.java`) que envuelve el acceso al DAO.

`mensajes.dat` y `mensajes.xml` se generan en tiempo de ejecución y **no se versionan** (cada
quien tiene los suyos localmente).

## Requisitos

- JDK 21 o superior (se probó con JDK 25).
- No hace falta tener Maven instalado: el proyecto trae `mvnw` / `mvnw.cmd` (Maven Wrapper).

## Cómo correrlo

Desde la raíz del proyecto:

```bash
# Windows
.\mvnw.cmd javafx:run

# Linux/Mac
./mvnw javafx:run
```

También se puede abrir el proyecto en IntelliJ IDEA (como módulo Maven) y correr `App.java`
directamente.

## Estructura del código

```
src/main/java/cr/ac/una/est/minidiscord/
├── App.java                          # Arranque de la aplicación JavaFX
├── business/
│   ├── model/                        # Message, User (entidades del dominio)
│   ├── dto/                          # MessageDTO (para exportar a XML)
│   └── service/                      # ChatService (reglas de negocio)
├── data/dao/                         # MessageRepository, JdbcMessageRepository, DatabaseConfig, MessageDAO...
└── presentation/                     # controller/ (ChatViewController) y component/ (MessageCell, Avatars)
```

## Flujo de trabajo semanal (ramas)

Ver [`GUIA_RAMAS.md`](./GUIA_RAMAS.md) para cómo está organizado el repositorio semana a semana
y cómo deben trabajar los estudiantes sobre él.

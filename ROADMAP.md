# Roadmap — Mini-Discord (Programación III, EIF206, 2do ciclo 2026)

Plan semana a semana para que, al cierre del curso, Mini-Discord sea una aplicación de chat
**completamente funcional**, con persistencia real en base de datos y comunicación en red entre
computadoras distintas usando hilos y sockets — la meta original del proyecto.

> Mini-Discord es el **código guía de laboratorio**, no el Proyecto #1 ni el Proyecto #2 que se
> evalúan oficialmente en el curso (ver Programa de Curso, sección 5). Sirve para que cada
> estudiante practique, en código real, el tema que se ve esa semana.

Basado en el cronograma oficial del programa de curso (EIF206, 2do ciclo 2026).

| Sesión | Fechas | Tema del programa | Rama | Qué se agrega a Mini-Discord |
|---|---|---|---|---|
| 8 | 7–13 sep | Arquitectura: persistencia y serialización | `semana-08` ✅ | DAO binario (`ObjectOutputStream`), export XML (`XMLEncoder`+DTO), Singleton `PersistenceManager`. |
| 9 | 14–20 sep | Evaluación Parcial | — | Sin avance de código (semana de examen / feriado según calendario del grupo). |
| 10 | 21–27 sep | Arquitectura (cont.) | — | Exposiciones de grupos. |
| **11** | **28 sep–4 oct** | **6. Persistencia por SABD — fundamentos, normalización, conexión MySQL** | `semana-11` | Modelar tablas `usuarios` y `mensajes` en MySQL; configurar la conexión con IntelliJ Database Tools; introducir una interfaz `MessageRepository` para poder alternar entre el DAO binario actual y uno nuevo por JDBC (mismo patrón DAO, otra implementación). |
| 12 | 5–11 oct | 6. SABD — CRUD (DLM/IMEC) y mapeo de datos | `semana-12` | Implementar `JdbcMessageRepository`: `INSERT`/`SELECT` reales por JDBC, mapeo manual `ResultSet` → `Message`. |
| 13 | 12–18 oct | 6. SABD — patrones de acceso a datos | `semana-13` | Terminar el CRUD (editar/borrar mensajes o usuarios), consolidar MySQL como persistencia principal; la binaria/XML quedan como referencia histórica del patrón DAO. |
| 14 | 19–25 oct | 7. Clases activas — hilos (multithreading) | `semana-14` | Mover las operaciones de red/BD a un hilo aparte para no bloquear el hilo de UI de JavaFX (`Platform.runLater`, `Task`/`Thread`). |
| 15 | 26 oct–1 nov | 7. Comunicación remota — sockets | `semana-15` | Servidor con `ServerSocket` (un hilo por cliente) + cliente `Socket` en el `App` de JavaFX: dos o más computadoras podrán chatear en tiempo real. **Aquí se cumple la meta original del proyecto.** |
| 16 | 2–8 nov | Evaluación Final | `semana-16` (opcional) | Repaso/consolidación: release final "Mini-Discord v1.0" con las tres formas de persistencia + red. |

## Cómo se actualiza este roadmap

Las fechas y temas exactos pueden variar según el avance real del grupo (tal como aclara el
programa de curso). Si una semana se corre o el contenido cambia, actualizar esta tabla y avisar
a los estudiantes por el canal que uses (aula virtual, GitHub Discussions, etc.) — el roadmap debe
reflejar siempre lo que realmente se va a laboratorio, no el plan ideal.

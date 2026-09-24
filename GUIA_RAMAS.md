# Guía de ramas — Mini-Discord

Este repositorio avanza **una semana a la vez**, siguiendo el plan de estudios del curso. Así es
como está organizado:

## Para la profesora (mantenimiento del repo)

- **`main`** siempre contiene el código guía más reciente y funcional — el punto de partida
  "oficial" de la semana en curso.
- Cada semana, al preparar el laboratorio nuevo, se crea una rama `semana-XX` **desde `main`**
  con el tema de esa semana ya resuelto o con el esqueleto que los estudiantes deben completar
  (según convenga pedagógicamente).
- Cuando el contenido de una semana se da por cerrado, se hace merge de esa rama hacia `main`
  para que quede como base de la siguiente.
- Ramas existentes/planeadas:
  - `semana-08` — Persistencia (binaria, XML, Singleton). ✅ completada.
  - `semana-11` — Bases de datos (a definir según el plan de estudios).
  - ... (una por cada semana restante del curso)

## Para los estudiantes

1. Hacer **fork** del repositorio a su propia cuenta de GitHub (botón "Fork" arriba a la derecha).
2. Clonar su fork:
   ```bash
   git clone https://github.com/<su-usuario>/MiniDiscord.git
   cd MiniDiscord
   ```
3. Cada semana, traer la rama nueva desde el repositorio original (`upstream`):
   ```bash
   git remote add upstream https://github.com/<profesora>/MiniDiscord.git   # solo la primera vez
   git fetch upstream
   git checkout -b semana-11 upstream/semana-11
   ```
4. Trabajar el laboratorio en esa rama, hacer commits normalmente.
5. Subir su avance a su propio fork (`git push origin semana-11`) y, si el laboratorio lo pide,
   abrir un Pull Request hacia el repositorio original para que la profesora revise.

## ¿Por qué esta estructura y no una rama por estudiante?

Con 20-30+ estudiantes, mantener una rama por persona en el repositorio principal implica que la
profesora tenga que hacer merge manual de `main` hacia cada una cada semana — no escala. Con
**fork + rama por semana**, cada estudiante controla su propio historial (su fork), y traer el
material nuevo cada semana es un solo `git fetch upstream` + `checkout`. Además, así todos
comparan su solución contra la misma base semanal, y un Pull Request opcional permite dar
feedback sin que un estudiante pise el trabajo de otro.

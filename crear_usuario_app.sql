-- ============================================================
-- crear_usuario_app.sql  -  usuario con privilegios minimos para la aplicacion
-- ============================================================
-- Idea: root es la "llave maestra" del servidor. La aplicacion NO debe usarla.
-- Se crea un usuario que solo puede MANIPULAR DATOS (DML) dentro de minidiscord.
-- La estructura (CREATE/DROP TABLE) la sigue administrando quien usa root, desde Workbench.
--
-- Ejecutar conectado como root, DESPUES de schema.sql.
-- Cambie la clave antes de ejecutar; esa es la que va en config.properties (no en el codigo).

CREATE USER IF NOT EXISTS 'minidiscord_app'@'localhost' IDENTIFIED BY 'CAMBIAR_CLAVE_APP';

GRANT SELECT, INSERT, UPDATE, DELETE ON minidiscord.* TO 'minidiscord_app'@'localhost';

-- Verificar que privilegios quedaron
SHOW GRANTS FOR 'minidiscord_app'@'localhost';

-- ------------------------------------------------------------
-- DEMO EN CLASE
-- ------------------------------------------------------------
-- 1) En Workbench cree una conexion nueva con usuario minidiscord_app y la clave de arriba.
-- 2) Conectado como minidiscord_app, ejecute cada linea por separado:
--
--      USE minidiscord;
--      SELECT * FROM mensajes;          -- funciona
--      INSERT INTO usuarios (username) VALUES ('prueba');   -- funciona
--      DROP TABLE mensajes;             -- ERROR 1142: DROP command denied
--      CREATE TABLE intento (id INT);   -- ERROR 1142: CREATE command denied
--      SELECT * FROM mysql.user;        -- ERROR 1142: SELECT command denied
--
-- Conclusion: aunque alguien obtenga las credenciales de la aplicacion, no puede borrar
-- tablas ni tocar otras bases del servidor. Sigue pudiendo leer y modificar los datos de
-- minidiscord, por eso la clave tampoco debe subirse a GitHub.
--
-- Para quitar el usuario al terminar (conectado como root):
--      DROP USER 'minidiscord_app'@'localhost';

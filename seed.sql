-- ============================================================
-- seed.sql  -  "datos semilla" de Mini-Discord
-- ============================================================
-- Un seed es el conjunto de datos INICIALES con los que arranca una base de datos.
-- Sirve para probar consultas sin escribir cada INSERT a mano, y para que todos
-- tengamos exactamente los mismos datos.
-- Solo se usa en desarrollo y pruebas: nunca con datos ni contrasenas reales.
--
-- Requisito: haber ejecutado antes schema.sql (crea la base y las tablas).
-- Se puede ejecutar varias veces: cada vez deja la base en el mismo estado inicial.

USE minidiscord;

-- 1) Limpiar. TRUNCATE vacia la tabla y reinicia el contador AUTO_INCREMENT (los ids vuelven a 1).
--    Se desactivan un momento las llaves foraneas porque mensajes depende de usuarios.
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE mensajes;
TRUNCATE TABLE usuarios;
SET FOREIGN_KEY_CHECKS = 1;

-- 2) Usuarios. No ponemos el id: lo genera AUTO_INCREMENT (ana=1, luis=2, maria=3, pedro=4).
INSERT INTO usuarios (username) VALUES ('ana'), ('luis'), ('maria'), ('pedro');

-- 3) Mensajes. usuario_id es la llave foranea: apunta al id de la tabla usuarios.
--    pedro (id 4) queda SIN mensajes a proposito: sirve para probar LEFT JOIN.
INSERT INTO mensajes (usuario_id, contenido, fecha_envio) VALUES
 (1, 'hola a todos!',                 '2026-10-13 13:05:00'),
 (2, 'hola ana, como estas?',         '2026-10-13 13:06:10'),
 (1, 'bien, y ustedes?',              '2026-10-13 13:06:45'),
 (3, 'alguien entendio el JOIN?',     '2026-10-13 13:08:00'),
 (2, 'es juntar dos tablas por la FK','2026-10-13 13:08:30'),
 (3, 'ahh ya, gracias',               '2026-10-13 13:09:00'),
 (1, 'ya hicieron el fork?',          '2026-10-13 13:10:15'),
 (2, 'todavia no',                    '2026-10-13 13:10:40');

-- 4) Verificacion: debe mostrar 4 usuarios y 8 mensajes.
SELECT COUNT(*) AS total_usuarios FROM usuarios;
SELECT COUNT(*) AS total_mensajes FROM mensajes;

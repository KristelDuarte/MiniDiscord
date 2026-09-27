-- Semana 11: esquema de la base de datos minidiscord (MySQL)
-- Ejecutar una sola vez, antes de correr la aplicacion.

CREATE DATABASE IF NOT EXISTS minidiscord;
USE minidiscord;

CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS mensajes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    contenido VARCHAR(500) NOT NULL,
    fecha_envio DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

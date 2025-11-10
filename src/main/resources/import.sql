-- ==========================================
--  ROLES BASE
-- ==========================================
INSERT INTO roles (id_rol, nombre) VALUES (1, 'ADMIN');
INSERT INTO roles (id_rol, nombre) VALUES (2, 'MOZO');
INSERT INTO roles (id_rol, nombre) VALUES (3, 'COCINERO');
INSERT INTO roles (id_rol, nombre) VALUES (4, 'CAJERO');

-- ==========================================
--  USUARIOS BASE
-- ==========================================
-- Contraseñas cifradas con BCrypt (admin123, mozo123, cocinero123, cajero123)

INSERT INTO usuarios (id_usuario, username, password, enabled) VALUES
(1, 'admin',     '$2a$10$Zb0RbDHdpHC7x4Uq3OqG1e.1wJr8UaaWbYl0ghfX/6qf8S9Kykj2y', true),
(2, 'mozo',      '$2a$10$dGXkhPj3eM3v7Ibgj6JkaOcm1E3rjQBYLHTTy0bILh8SGcFpi/Er.', true),
(3, 'cocinero',  '$2a$10$qXz3rHd4o6POhMBVcpbVneAFkArsyowqv/Nm4vFVG8HTRRqCEJ4v2', true),
(4, 'cajero',    '$2a$10$fQ4IsmOtiZkQtYKoM8HqMu9xO3t/J4xG0tBC2P1V8qQ6.ZmMfLz8e', true);

-- ==========================================
--  RELACIONES USUARIO-ROL
-- ==========================================
INSERT INTO usuarios_roles (usuario_id, rol_id) VALUES (1, 1); -- ADMIN
INSERT INTO usuarios_roles (usuario_id, rol_id) VALUES (2, 2); -- MOZO
INSERT INTO usuarios_roles (usuario_id, rol_id) VALUES (3, 3); -- COCINERO
INSERT INTO usuarios_roles (usuario_id, rol_id) VALUES (4, 4); -- CAJERO;

-- =====================================================
-- Base de datos: db_libreria
-- Proyecto: T1 - Librería
-- Motor: MySQL
-- =====================================================

CREATE DATABASE IF NOT EXISTS db_libreria;
USE db_libreria;

-- =====================================================
-- Tabla: personas (clase padre - herencia JOINED)
-- =====================================================
CREATE TABLE personas (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    numero_documento VARCHAR(8) NOT NULL UNIQUE,
    telefono VARCHAR(9)
);

-- =====================================================
-- Tabla: clientes (hereda de personas - JOINED)
-- =====================================================
CREATE TABLE clientes (
    id BIGINT UNSIGNED PRIMARY KEY,
    email VARCHAR(150) UNIQUE,
    direccion VARCHAR(250),
    FOREIGN KEY (id) REFERENCES personas(id)
);

-- =====================================================
-- Tabla: empleados (hereda de personas - JOINED)
-- =====================================================
CREATE TABLE empleados (
    id BIGINT UNSIGNED PRIMARY KEY,
    cargo VARCHAR(50) NOT NULL,
    fecha_ingreso DATE,
    FOREIGN KEY (id) REFERENCES personas(id)
);

-- =====================================================
-- Tabla: libros
-- =====================================================
CREATE TABLE libros (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    isbn VARCHAR(20) NOT NULL UNIQUE,
    titulo VARCHAR(200) NOT NULL,
    autor VARCHAR(150) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    stock INT DEFAULT 0,
    CHECK (precio >= 0),
    CHECK (stock >= 0)
);

-- =====================================================
-- Tabla: pedidos
-- =====================================================
CREATE TABLE pedidos (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    cliente_id BIGINT UNSIGNED NOT NULL,
    empleado_id BIGINT UNSIGNED NOT NULL,
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(10,2) DEFAULT 0.00,
    estado VARCHAR(20) DEFAULT 'REGISTRADO',
    FOREIGN KEY (cliente_id) REFERENCES clientes(id),
    FOREIGN KEY (empleado_id) REFERENCES empleados(id),
    CHECK (total >= 0),
    CHECK (estado IN ('REGISTRADO','PAGADO','ANULADO'))
);

-- =====================================================
-- Tabla: detalle_pedido
-- =====================================================
CREATE TABLE detalle_pedido (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    pedido_id BIGINT UNSIGNED NOT NULL,
    libro_id BIGINT UNSIGNED NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10,2) NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (pedido_id) REFERENCES pedidos(id) ON DELETE CASCADE,
    FOREIGN KEY (libro_id) REFERENCES libros(id),
    UNIQUE (pedido_id, libro_id),
    CHECK (cantidad > 0),
    CHECK (precio_unitario >= 0)
);

-- =====================================================
-- Datos de prueba
-- =====================================================
INSERT INTO personas (nombres, apellidos, numero_documento, telefono) VALUES
('Ana','Torres Mendoza','74859621','987654321'),
('Carlos','Ramirez Soto','70654321','986123456'),
('Luis','Quispe Vera','71122334','999888777');

INSERT INTO clientes (id, email, direccion) VALUES
(1,'ana.torres@example.com','Av. Los Alamos 123'),
(2,'carlos.ramirez@example.com','Jr. Las Flores 456');

INSERT INTO empleados (id, cargo, fecha_ingreso) VALUES
(3,'Vendedor','2024-01-15');

INSERT INTO libros (isbn, titulo, autor, precio, stock) VALUES
('978-1','Cien Anios de Soledad','Gabriel Garcia Marquez',65.00,15),
('978-2','El Principito','Antoine de Saint-Exupery',35.00,25),
('978-3','1984','George Orwell',45.00,10);

INSERT INTO pedidos (cliente_id, empleado_id, total, estado) VALUES
(1,3,100.00,'PAGADO');

INSERT INTO detalle_pedido (pedido_id, libro_id, cantidad, precio_unitario, subtotal) VALUES
(1,1,1,65.00,65.00),
(1,2,1,35.00,35.00);
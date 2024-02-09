CREATE TABLE personas (
	id numeric(38,0) IDENTITY(0,1) NOT NULL,
	nombre varchar(100) NOT NULL,
	genero varchar(2) NULL,
	edad int NULL,
	identificacion varchar(30) NOT NULL,
	direccion varchar(1000) NULL,
	telefono varchar(20) NULL,
	CONSTRAINT personas_PK PRIMARY KEY (id)
);

CREATE TABLE clientes (
	id numeric(38,0) IDENTITY(0,1) NOT NULL,
	estado bit NULL,
	password varchar(20) NULL,
	id_persona numeric(38,0) NOT NULL
);
ALTER TABLE clientes ADD CONSTRAINT clientes_FK FOREIGN KEY (id) REFERENCES personas(id);

CREATE TABLE cuentas (
	id_cuenta numeric(38,0) IDENTITY(0,1) NOT NULL,
	id_cliente numeric(38,0) NULL,
	numero varchar(50) NOT NULL,
	tipo varchar(20) NULL,
	saldo_inicial numeric(38,0) NULL,
	estado bit NULL,
	CONSTRAINT cuentas_PK PRIMARY KEY (id_cuenta)
);

CREATE TABLE movimientos (
	id_movimiento numeric(38,0) IDENTITY(0,1) NOT NULL,
	fecha datetime NULL,
	tipo varchar(20) NULL,
	saldo_inicial numeric(38,0) NULL,
	saldo numeric(38,0) NULL,
	valor numeric(38,0) NULL,
	id_cuenta numeric(38,0) NULL
);
ALTER TABLE movimientos ADD CONSTRAINT movimientos_FK FOREIGN KEY (id_cuenta) REFERENCES cuentas(id_cuenta);


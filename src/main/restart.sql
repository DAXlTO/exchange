DROP TABLE Usuario;

CREATE TABLE Usuario(
	id		VARCHAR(9),
	balance		INTEGER,
	card		INTEGER NOT NULL,
	age		INTEGER NOT NULL CHECK (age > 17),
	email		VARCHAR(50) NOT NULL,
	nombre		VARCHAR(50) NOT NULL,
	password	VARCHAR(50) NOT NULL,
	phone		INTEGER NOT NULL,

CONSTRAINT cp_user PRIMARY KEY(id)
);

CREATE TABLE Wallet(
    idWallet        VARCHAR(40),
    quantity        INTEGER,
    idUsuario       VARCHAR(9),

CONSTRAINT cp_waller PRIMARY KEY(idWallet),
CONSTRAINT ca_usuario FOREIGN KEY(idUsuario) REFERENCES Usuario(id) ON DELETE RESTRICT ON UPDATE CASCADE

);

CREATE TABLE Offers(
    walletSeller    VARCHAR(40),
    quantity        INTEGER,
    dateOffer       DATE NOT NULL,
    idUsuario       VARCHAR(9),

CONSTRAINT cp_offer PRIMARY KEY(walletSeller),
CONSTRAINT ca_usuario FOREIGN KEY(idUsuario) REFERENCES Usuario(id) ON DELETE RESTRICT ON UPDATE CASCADE

);
INSERT INTO "User" VALUES('12345678N',0,123456789,18,'admin@admin.com','Admin','/rPjddRcrpc7IPtD8oN+QoD/8KrIM7CD',623457698);
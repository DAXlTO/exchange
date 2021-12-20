DROP TABLE offers;
DROP TABLE transactions;
DROP TABLE wallet;
DROP TABLE Users;


CREATE TABLE Users(
	id		VARCHAR(9),
	balance		INTEGER,
	credit_card		BIGINT NOT NULL,
	age		INTEGER NOT NULL CHECK (age > 17),
	email		VARCHAR(50) NOT NULL,
	name		VARCHAR(50) NOT NULL,
	password	VARCHAR(50) NOT NULL,
	phone		INTEGER NOT NULL,

CONSTRAINT cp_users PRIMARY KEY(id)
);

CREATE TABLE Wallet(
    idWallet        VARCHAR(40),
    quantity        FLOAT,
    idUser          VARCHAR(9),

CONSTRAINT cp_wallet PRIMARY KEY(idWallet),
CONSTRAINT ca_users FOREIGN KEY(idUser) REFERENCES Users(id) ON DELETE RESTRICT ON UPDATE CASCADE

);

CREATE TABLE Offers(
    idOffer         INTEGER,
    quantity        FLOAT,
    price           FLOAT,
    total           FLOAT,
    dateOffer       DATE NOT NULL,
    idUser          VARCHAR(9),

CONSTRAINT cp_offer PRIMARY KEY(idOffer),
CONSTRAINT ca_users FOREIGN KEY(idUser) REFERENCES Users(id) ON DELETE RESTRICT ON UPDATE CASCADE

);


CREATE TABLE Transactions(
    idTransactions  INTEGER,
    walletBuyer     VARCHAR(40) NOT NULL,
    walletSeller    VARCHAR (40) NOT NULL,
    quantity        INTEGER NOT NULL,
    trans_date      Date,

CONSTRAINT cp_Transactions PRIMARY KEY (idTransactions)
);



INSERT INTO Users VALUES('12345678N',0,123456789,18,'admin@admin.com','Admin','/rPjddRcrpc7IPtD8oN+QoD/8KrIM7CD',623457698);
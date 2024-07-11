create database conce;
use conce;

create table vehiculos(
placa varchar(25) primary key not null,
marca varchar(25),
cilindraje float,
tipo_combustible varchar(25),
color varchar(25),
propietario varchar(25)
);

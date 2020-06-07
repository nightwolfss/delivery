create table cliente (id int(10) primary key auto_increment, nome varchar(50) not null, endereco varchar(250) not null, telefone varchar(15), observacao text, idDono int(10) not null);

create table prato (id int(10) primary key auto_increment, nome varchar(50) not null, descricao varchar(250), categoria varchar(20), idDono int(10) not null);
 
create table pedido (id int(10) primary key auto_increment, cliente varchar(50) not null, clienteId int(5) not null, endereco varchar(250) not null, prato text not null, observacao text, hora timestamp default current_timestamp, preco varchar(6), idDono int(10) not null);

create table lembrete (id int(5) primary key auto_increment, titulo varchar(50) unique not null, texto text, ano varchar(4), mes varchar(10), dia varchar(2), hora varchar(2), minuto varchar(2) not null, idDono int(10) not null);

create table login (id int(10) primary key auto_increment, nome varchar(50) not null unique, senha varchar(20) not null, admin char(1));
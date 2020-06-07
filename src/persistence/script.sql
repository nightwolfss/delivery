create table cliente (id int(10) primary key auto_increment, nome varchar(50) not null, endereco varchar(250) not null, telefone varchar(15), observacao text);

create table prato (id int(10) primary key auto_increment, nome varchar(50) not null, descricao varchar(250), categoria varchar(20));
 
create table pedido (id int(10) primary key auto_increment, cliente varchar(50) not null, clienteId int(5) not null, endereco varchar(250) not null, prato text not null, observacao text, hora timestamp default current_timestamp, preco varchar(6));

create table lembrete (id int(5) primary key auto_increment, titulo varchar(50) unique not null, texto text);
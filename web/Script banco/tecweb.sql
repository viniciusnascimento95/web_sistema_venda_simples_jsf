create table cliente (
	id serial not null,
	nome varchar unique not null,
	email varchar unique not null,
	telefone varchar
	
);

select * from cliente;

create table fornecedor (
	id serial not null,
	nome varchar not null,
	cnpj varchar not null,
	email varchar not null,
	telefone varchar not null,
	endereco varchar not null
	
);

select * from fornecedor;


create table produto (
	id serial not null,
	nome varchar not null,
	descricao  varchar unique not null,
	valor double precision not null,
	modelo varchar not null,
	quantidade integer not null,
	fornecedor_id integer	
);

SELECT * FROM produto;

drop table produto;

create table venda (
	id serial not null,
	cliente_id integer not null,
	data_venda varchar not null,
	valor_unitario decimal not null,
	quantidade_unitaria integer
);

select * from venda;

create table detalhe_venda (
	id serial not null,
	venda_id integer not null,
	produto_id integer not null,
	valor_total double precision not null,
	quantidade_total integer not null
);

select * from detalhe_venda;

--selec estoque 
select p.id, p.descricao, (p.quantidade - dv.quantidade_total) as quantidade 
                 from public.detalhe_venda as dv inner join public.produto p on p.id = dv.produto_id
                 where p.id = dv.produto_id;


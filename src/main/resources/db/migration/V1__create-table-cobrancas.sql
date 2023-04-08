create table cobrancas(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    linha_digitavel varchar(100) not null,
    vencimento DATE not null,
    valor decimal(10, 2) not null,

    primary key(id)

);
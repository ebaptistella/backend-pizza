create table tb_cliente (id bigint not null, tx_clientenome varchar(200) not null, nr_documento varchar(200) not null, primary key (id));
create table tb_pedido (id bigint not null, dt_pedido timestamp not null, dt_entrega timestamp, nr_tempopedido bigint not null, vl_pedido decimal(19,2) not null, cd_cliente bigint not null, primary key (id));
create table tb_pedidoitem (id bigint not null, vl_preco decimal(19,2) not null, nr_tempopreparo bigint not null, cd_pizzasabor bigint, cd_pizzatamanho bigint, cd_pedido bigint, primary key (id));
create table tb_pedidoadicional (id bigint not null, cd_pedido bigint not null, vl_precoadicional decimal(19,2) not null, nr_tempopreparoadicional bigint, cd_pizzaadicional bigint, primary key (id));
create table tb_pizzaadicionalpreco (cd_pizzaadicional bigint not null, vl_precoadicional decimal(19,2) not null, primary key (cd_pizzaadicional));
create table tb_pizzapreco (cd_pizzatamanho bigint not null, vl_preco decimal(19,2) not null, primary key (cd_pizzatamanho));
create table tb_pizzasabor (id bigint not null, tx_sabornome varchar(200) not null, nr_tempoadicional bigint, primary key (id));
create table tb_pizzatamanho (id bigint not null, tx_tamanhonome varchar(200) not null, primary key (id));
create table tb_pizzatempopreparo (cd_pizzatamanho bigint not null, nr_tempopreparo bigint not null, primary key (cd_pizzatamanho));
create table tb_pizzaadicional(id bigint not null, tx_adicionalnome varchar(200) not null, nr_tempoadicional bigint, primary key (id));

create sequence gen_cliente start with 1 increment by 1;
create sequence gen_pedido start with 1 increment by 1;
create sequence gen_pedidoitem start with 1 increment by 1;
create sequence gen_pedidoadicional start with 1 increment by 1;
create sequence gen_pizzaadicional start with 1 increment by 1;
create sequence gen_pizzasabor start with 1 increment by 1;
create sequence gen_pizzatamanho start with 1 increment by 1;

alter table tb_pedido add constraint pedido_cliente_fk foreign key (cd_cliente) references tb_cliente(id);
alter table tb_pedidoitem add constraint pedidoitem_pizzasabor_fk foreign key (cd_pizzasabor) references tb_pizzasabor(id);
alter table tb_pedidoitem add constraint pedidoitem_pizzatamanho_fk foreign key (cd_pizzatamanho) references tb_pizzatamanho(id);
alter table tb_pedidoitem add constraint pedidoitem_pedido_fk foreign key (cd_pedido) references tb_pedido(id);
alter table tb_pedidoadicional add constraint pedidoadicional_pedido_fk foreign key (cd_pedido) references tb_pedido(id);
alter table tb_pedidoadicional add constraint pedidoadicional_pizzaadicional_fk foreign key (cd_pizzaadicional) references tb_pizzaadicional(id);
alter table tb_pizzaadicionalpreco add constraint pizzaadicionalpreco_pizzaadicional_fk foreign key (cd_pizzaadicional) references tb_pizzaadicional(id);
alter table tb_pizzapreco add constraint pizzapreco_pizzatamanho_fk foreign key (cd_pizzatamanho) references tb_pizzatamanho(id);
alter table tb_pizzatempopreparo add constraint pizzatempopreparo_pizzatamanho_fk foreign key (cd_pizzatamanho) references tb_pizzatamanho(id);

commit;
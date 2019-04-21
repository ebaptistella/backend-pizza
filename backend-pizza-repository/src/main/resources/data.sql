insert into tb_pizzatamanho(id, tx_tamanhonome) values (1, 'pequena'), (2, 'média'), (3, 'grande');

insert into tb_pizzasabor(id, tx_sabornome, nr_tempoadicional) values (1, 'calabresa', null), (2, 'marguerita', null), (3, 'portuguesa', 5);

insert into tb_pizzapreco(cd_pizzatamanho, vl_preco) values (1, 20), (2, 30), (3, 40);

insert into tb_pizzatempopreparo(cd_pizzatamanho, nr_tempopreparo) values (1, 15), (2, 20), (3, 25);
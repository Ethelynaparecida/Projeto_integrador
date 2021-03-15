CREATE TABLE `doadores` (
	`id_doadores` INT NOT NULL AUTO_INCREMENT,
	`nome_doadores` varchar(60) NOT NULL,
	`nome_doadores` varchar(60) NOT NULL,
	`email_doadores` varchar(60) NOT NULL,
	`senha_doadores` varchar(15) NOT NULL,
	`tipo_doadores` varchar(60) NOT NULL,
	`cnpj_doadores` INT(60) NOT NULL,
	`cidade_doadores` varchar(60) NOT NULL,
	`bairro_doadores` varchar(60) NOT NULL,
	`fk_doadores_comunidade` varchar(60) NOT NULL,
	PRIMARY KEY (`id_doadores`)
);

CREATE TABLE `comunidade` (
	`id_comunidade` INT(60) NOT NULL AUTO_INCREMENT,
	`nome_comunidade` varchar(60) NOT NULL,
	`descricao_comunidade` varchar(60) NOT NULL,
	`bairro_comunidade` varchar(60) NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (`id_comunidade`)
);

CREATE TABLE `receber` (
	`id_recebedor` INT NOT NULL AUTO_INCREMENT,
	`nome_recebedor` varchar(60) NOT NULL,
	`email_recebedor` varchar(60) NOT NULL,
	`senha_recebedor` varchar(15) NOT NULL,
	`endereco_recebedor` varchar(60) NOT NULL,
	`telefone_recebedor` varchar(60) NOT NULL,
	PRIMARY KEY (`id_recebedor`)
);

CREATE TABLE `publicacao` (
	`id_publicacao` INT NOT NULL AUTO_INCREMENT,
	`quantidade_publicacao` INT NOT NULL,
	`categoria_publicacao` varchar(60) NOT NULL,
	`descricao_publicacao` varchar(255) NOT NULL,
	`inscricao_publicacao` varchar(60) NOT NULL,
	`fk_publicacao_comunidade` varchar(255) NOT NULL,
	PRIMARY KEY (`id_publicacao`)
);

CREATE TABLE `recebedor_na_comunidade` (
	`fk_comunidade_recebedor` INT NOT NULL,
	`fk_recebedor_comunidade` INT NOT NULL
);

ALTER TABLE `doadores` ADD CONSTRAINT `doadores_fk0` FOREIGN KEY (`fk_doadores_comunidade`) REFERENCES `comunidade`(`id_comunidade`);

ALTER TABLE `publicacao` ADD CONSTRAINT `publicacao_fk0` FOREIGN KEY (`fk_publicacao_comunidade`) REFERENCES `comunidade`(`id_comunidade`);

ALTER TABLE `recebedor_na_comunidade` ADD CONSTRAINT `recebedor_na_comunidade_fk0` FOREIGN KEY (`fk_comunidade_recebedor`) REFERENCES `comunidade`(`id_comunidade`);

ALTER TABLE `recebedor_na_comunidade` ADD CONSTRAINT `recebedor_na_comunidade_fk1` FOREIGN KEY (`fk_recebedor_comunidade`) REFERENCES `receber`(`id_recebedor`);


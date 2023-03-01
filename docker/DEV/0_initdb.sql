CREATE DATABASE IF NOT EXISTS `tickets_database`;
USE `tickets_database`;

CREATE TABLE IF NOT EXISTS `events` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `amount` int NOT NULL,
  `start_date` datetime NOT NULL,
  `end_date` datetime NOT NULL,
  `tickets_number` int NOT NULL,
  `address` varchar(45) NOT NULL,
  `sold_tickets` int DEFAULT '0',
  `changed_tickets` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `tickets` (
  `id` int NOT NULL AUTO_INCREMENT,
  `event_id` int NOT NULL,
  `ticket_date` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `changed` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `event_id_idx` (`event_id`),
  CONSTRAINT `event_id` FOREIGN KEY (`event_id`) REFERENCES `events` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



INSERT INTO `events` VALUES (1,'Mexican party','Concierto',5000,'2020-11-26 18:05:00','2020-12-26 18:05:00',100,'Fco. montejo',0,0),(2,'El baile de medianoche','Obra de teatro',500,'2023-10-01 00:00:00','2023-10-15 00:00:00',150,'Carta blanca merida',1,0);

INSERT INTO `tickets` VALUES (1,2,'23-02-23 12:00:00','user@gmail.com',0);

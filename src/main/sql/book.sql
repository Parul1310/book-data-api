CREATE TABLE `book` (
  `id` int(11) NOT NULL,
  `name` varchar(500) DEFAULT NULL,
  `author` varchar(200) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `file` mediumblob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

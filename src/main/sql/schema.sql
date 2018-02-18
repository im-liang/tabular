CREATE TABLE `restaurant` (
  `restaurant_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `phone` bigint(20) NOT NULL,
  `street` varchar(100) NOT NULL,
  `city` varchar(30) NOT NULL,
  `state` varchar(100) NOT NULL,
  `zip` varchar(30) NOT NULL,
  PRIMARY KEY (`restaurant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE `table` (
  `table_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `number_limit` int NOT NULL,
  `restaurant_id` bigint(20) not null,
  PRIMARY KEY (`table_id`),
  FOREIGN KEY (`restaurant_id`) REFERENCES restaurant(restaurant_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` INT NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE `customer` (
  `customer_id` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`customer_id`),
  FOREIGN KEY (`customer_id`) REFERENCES user(user_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE `owner` (
  `owner_id` bigint(20) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `restaurant_id` bigint(20)  NOT NULL,
  PRIMARY KEY (`owner_id`),
  FOREIGN KEY (`owner_id`) REFERENCES user(user_id),
  FOREIGN KEY (`restaurant_id`) REFERENCES restaurant(restaurant_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE `waiter` (
  `waiter_id` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `owner_id` bigint(20) NOT NULL,
  PRIMARY KEY (`waiter_id`),
  FOREIGN KEY (`waiter_id`) REFERENCES user(user_id),
  FOREIGN KEY (`owner_id`) REFERENCES owner(owner_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE `appointment` (
  `appointment_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `start_time` DATETIME NOT NULL,
  `status` varchar(30) NOT NULL,
  `table_id` bigint(20) NOT NULL,
  PRIMARY KEY (`appointment_id`),
  FOREIGN KEY (`table_id`) REFERENCES `table` (table_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE `appointment2customer` (
  `appointment2customer_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `appointment_id` bigint(20) NOT NULL,
  `customer_id` bigint(20) NOT NULL,
  `time` DATETIME NOT NULL,
  PRIMARY KEY (`appointment2customer_id`),
  FOREIGN KEY (`appointment_id`) REFERENCES appointment(`appointment_id`),
  FOREIGN KEY (`customer_id`) REFERENCES customer(`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE `appointment2waiter` (
  `appointment2waiter_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `appointment_id` bigint(20) NOT NULL,
  `waiter_id` bigint(20) NOT NULL,
  `time` DATETIME NOT NULL,
  PRIMARY KEY (`appointment2waiter_id`),
  FOREIGN KEY (`appointment_id`) REFERENCES appointment(`appointment_id`),
  FOREIGN KEY (`waiter_id`) REFERENCES waiter(`waiter_id`)
)
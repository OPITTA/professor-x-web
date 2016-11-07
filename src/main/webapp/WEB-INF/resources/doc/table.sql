CREATE DATABASE IF NOT EXISTS professor_x DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
USE professor_x;
CREATE TABLE IF NOT EXISTS `user` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `account` varchar(255) NOT NULL,
    `passwd` varchar(255) NOT NULL,
    `email` varchar(255) NOT NULL,
    `user_role` int(11) NOT NULL,
    `valied` int(11) NOT NULL,
    `create_time` timestamp NOT NULL DEFAULT NOW(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `user` (`account`, `passwd`, `email`, `user_role`, `valied`)VALUES("admin", "21232f297a57a5a743894a0e4a801fc3", "510655387@qq.com", 3, 1);

CREATE TABLE IF NOT EXISTS `computer` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `user_id` int(11) NOT NULL,
    `cpu_id` int(11) NOT NULL,
    `mem_id` int(11) NOT NULL,
    `dist_id` int(11) NOT NULL,
    `solid_dist_id` int(11) NOT NULL,
    `create_time` timestamp NOT NULL DEFAULT NOW(),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `cpu` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `model` varchar(255) NOT NULL,
    `frequency` float(11),
    `core_num` int(11) NOT NULL,
    `describe` varchar(255) NOT NULL,
    `create_time` timestamp NOT NULL DEFAULT NOW(),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `mem` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `model` varchar(255) NOT NULL,
    `capacity` int(11) NOT NULL,
    `describe` varchar(255) NOT NULL,
    `create_time` timestamp NOT NULL DEFAULT NOW(),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `dist` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `model` varchar(255) NOT NULL,
    `capacity` int(11) NOT NULL,
    `speed` int(11) NOT NULL,
    `describe` varchar(255) NOT NULL,
    `create_time` timestamp NOT NULL DEFAULT NOW(),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `solid_dist` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `model` varchar(255) NOT NULL,
    `capacity` int(11) NOT NULL,
    `describe` varchar(255) NOT NULL,
    `create_time` timestamp NOT NULL DEFAULT NOW(),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `report` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `user_id` int(11) NOT NULL,
    `computer_id` int(11) NOT NULL,
    `title` varchar(255) NOT NULL,
    `describe` text NOT NULL,
    `content` text NOT NULL,
    `summary` text NOT NULL,
    `status` int(11) NOT NULL,
    `create_time` timestamp NOT NULL DEFAULT NOW(),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `data` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `report_id` int(11) NOT NULL,
    `average_rt` int(11) NOT NULL,
    `min_rt` int(11) NOT NULL,
    `max_rt` int(11) NOT NULL,
    `tps` int(11) NOT NULL,
    `concurrency` int(11) NOT NULL,
    `error_rate` int(11) NOT NULL,
    `create_time` timestamp NOT NULL DEFAULT NOW(),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
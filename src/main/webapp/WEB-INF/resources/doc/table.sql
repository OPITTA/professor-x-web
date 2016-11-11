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
    `user_id` int(11) NOT NULL DEFAULT -1,
    `cpu_id` int(11) NOT NULL DEFAULT -1,
    `mem_id` int(11) NOT NULL DEFAULT -1,
    `dist_id` int(11) NOT NULL DEFAULT -1,
    `solid_dist_id` int(11) NOT NULL DEFAULT -1,
    `network_card_id` int(11) NOT NULL DEFAULT -1,
    `describe` varchar(255) NOT NULL,
    `create_time` timestamp NOT NULL DEFAULT NOW(),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `cpu` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `model` varchar(255) NOT NULL,
    `frequency` float(11),
    `core_num` int(11) NOT NULL DEFAULT -1,
    `describe` varchar(255) NOT NULL,
    `create_time` timestamp NOT NULL DEFAULT NOW(),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `mem` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `model` varchar(255) NOT NULL,
    `capacity` int(11) NOT NULL DEFAULT -1,
    `describe` varchar(255) NOT NULL,
    `create_time` timestamp NOT NULL DEFAULT NOW(),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `dist` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `model` varchar(255) NOT NULL,
    `capacity` int(11) NOT NULL DEFAULT -1,
    `speed` int(11) NOT NULL DEFAULT -1,
    `describe` varchar(255) NOT NULL,
    `create_time` timestamp NOT NULL DEFAULT NOW(),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `solid_dist` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `model` varchar(255) NOT NULL,
    `capacity` int(11) NOT NULL DEFAULT -1,
    `describe` varchar(255) NOT NULL,
    `create_time` timestamp NOT NULL DEFAULT NOW(),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `network_card` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `model` varchar(255) NOT NULL,
    `speed` int(11) NOT NULL DEFAULT -1,
    `describe` varchar(255) NOT NULL,
    `create_time` timestamp NOT NULL DEFAULT NOW(),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `report` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `user_id` int(11) NOT NULL DEFAULT -1,
    `computer_ids` varchar(255) NOT NULL DEFAULT '[]',
    `title` varchar(255) NOT NULL,
    `describe` text NOT NULL,
    `content` text NOT NULL,
    `summary` text NOT NULL,
    `status` int(11) NOT NULL DEFAULT -1,
    `create_time` timestamp NOT NULL DEFAULT NOW(),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `data` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `report_id` int(11) NOT NULL DEFAULT -1,
    `title` varchar(255) NOT NULL,
    `concurrency` int(11) NOT NULL DEFAULT -1,
    `sample_size` int(11) NOT NULL DEFAULT -1,
    `message_size` int(11) NOT NULL DEFAULT -1,
    `average_rt` float(11) NOT NULL DEFAULT 0.0,
    `min_rt` int(11) NOT NULL DEFAULT -1,
    `max_rt` int(11) NOT NULL DEFAULT -1,
    `tps` float(11) NOT NULL DEFAULT 0.0,
    `error_rate` float(11) NOT NULL DEFAULT 0.0,
    `create_time` timestamp NOT NULL DEFAULT NOW(),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
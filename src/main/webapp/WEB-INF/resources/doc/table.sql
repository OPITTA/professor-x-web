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
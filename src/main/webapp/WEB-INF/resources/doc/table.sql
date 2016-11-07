DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `account` varchar(255) NOT NULL,
    `passwd` varchar(255) NOT NULL,
    `email` varchar(255) NOT NULL,
    `user_role` int(11) NOT NULL,
    `platform_id` int(11) DEFAULT NULL,
    `valied` int(11) NOT NULL,
    `create_time` timestamp NOT NULL DEFAULT NOW(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `user` (`account`, `passwd`, `email`, `user_role`, `platform_id`, `valied`)VALUES("super_admin", "ed49c3fed75a513a79cb8bd1d4715d57", "xin.cao@100credit.com ", 5, -1, 1);
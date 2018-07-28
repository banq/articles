
CREATE TABLE  `user` (
  `id` int(11) NOT NULL ,
  `username` varchar(45) NOT NULL default '',
  `password` varchar(45) NOT NULL default '',
  `age` int(11) ,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
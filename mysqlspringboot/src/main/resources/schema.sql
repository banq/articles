CREATE TABLE `resource_table` (
  `File_id` int(10) NOT NULL AUTO_INCREMENT,
  `Text_path` varchar(100) DEFAULT NULL,
  `Images_path` varchar(100) DEFAULT NULL,
  UNIQUE KEY `File_id` (`File_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `resource_table` */

insert  into `resource_table`(`Text_path`,`Images_path`) values ('e:/text.txt','e:/image.jpg');

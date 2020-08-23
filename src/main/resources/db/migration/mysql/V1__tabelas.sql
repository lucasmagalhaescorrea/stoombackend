CREATE TABLE `endereco` (
`id` bigint(20) NOT NULL,
`streetname` varchar(255) NOT NULL,
`number` varchar(10) NOT NULL,
`complement` varchar(100) NULL,
`neighbourhood` varchar(100) NOT NULL,
`city` varchar(50) NOT NULL,
`state` varchar(50) NOT NULL,
`country` varchar(50) NOT NULL,
`zipcode` varchar(20) NOT NULL,
`latidade` int(4) NULL,
`longitude` int(4) NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `endereco`
ADD PRIMARY KEY (`id`);

ALTER TABLE `endereco`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
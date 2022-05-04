create user 'ampSlave'@'%' identified with mysql_native_password by 'cacapoop';
grant replication slave on *.* to 'ampSlave'@'%';

flush privileges;

quit;
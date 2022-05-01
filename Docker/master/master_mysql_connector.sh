mysql -u root -p
cacapoop

create user 'ampSlave'@'%' identified by 'cacapoop';
grant replication slave on *.* to 'ampSlave'@'%';

flush privileges;

quit;
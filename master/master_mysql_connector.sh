mysql -u root -p 'cacapoop' -e "Show databases"
cacapoop

create user 'ampSlave'@'%' identified by 'cacapoop';
grant replication slave on *.* to 'ampSlave'@'%';

flush privileges;

quit;
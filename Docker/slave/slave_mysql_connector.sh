mysql -u root -p
cacapoop

CHANGE MASTER TO
MASTER_HOST='mysql-amp',
MASTER_USER='ampSlave',
MASTER_PASSWORD='cacapoop',
MASTER_LOG_FILE='87e8982d00d1-bin.000004',
MASTER_LOG_POS=349;

set global server_id=2;

start slave;

#Si no funca
reset slave;
start slave IO_THREAD;
stop slave IO_THREAD;
reset slave;
start slave;
########

show slave status\G;
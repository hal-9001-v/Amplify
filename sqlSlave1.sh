CHANGE MASTER TO
MASTER_HOST='mysql-amp',
MASTER_USER='ampSlave',
MASTER_PASSWORD='cacapoop',
MASTER_LOG_FILE='binlog.000002',
MASTER_LOG_POS=0;

set global server_id=2;

start slave;

#Si no funca
reset slave;
start slave IO_THREAD;
stop slave IO_THREAD;
reset slave;
start slave;
########

show slave status\G
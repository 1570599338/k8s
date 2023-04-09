#!/bin/bash

HOST=`hostname -s`
DOMAIN=`hostname -d`
SERVER_PORT=2888
ELECTION_PORT=3888
ZOO_DATA_DIR=/lquan/lquan/data
ZOO_DATA_LOG_DIR=/lquan/lquan/datalog
SERVERS_NUM=2

 if [[ $HOST =~ (.*)-([0-9]+)$ ]]; then
    NAME=${BASH_REMATCH[1]}
    ORD=${BASH_REMATCH[2]}
 else
    echo "Failed to parse name and ordinal of Pod"
    exit 1
 fi

 export MY_ID=$((ORD+1))
 mkdir -p ${ZOO_DATA_DIR}
 mkdir -p ${ZOO_DATA_LOG_DIR}
 echo $SERVERS_NUM
 echo $SERVERS_NUM >> a.log
 chown -Rv lquan:lquan "$ZOO_DATA_DIR" "$ZOO_DATA_LOG_DIR"
 chmod -R 775  ${ZOO_DATA_DIR}
 chmod -R 775  ${ZOO_DATA_LOG_DIR}

 echo $MY_ID > $ZOO_DATA_DIR/myid
 for (( i=1; i<=$SERVERS_NUM; i++ )); do
    echo "server.$i=$NAME-$((i-1)).$DOMAIN:$SERVER_PORT:$ELECTION_PORT" >> /lquan/lquan/zoo.cfg;
 done
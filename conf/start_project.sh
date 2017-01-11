#!/bin/sh
#################################################################################
#
#   start the project jar
#
#################################################################################

APP_NAME=s-workbench

APP_HOME=/data/projects/${APP_NAME}
PID_FILE=${APP_HOME}/project.pid

start() {
    echo "Starting Server...... "
    ## remove cache
    rm -rf $(dirname $0)/work/*

    ## Start Server
    nohup java -jar ${APP_HOME}/${APP_NAME}.jar >${APP_HOME}/logs/"start_`date +%y-%m-%d_%H_%M_%S`.log" 2>>error.log &

    ## Record pid
    echo $! > ${PID_FILE}
    echo "Server Start [ OK ]"
}

stop() {
    echo "Stopping Server......"
    ##
    ## Stop Server
    if [ ! -f "$PID_FILE" ];
    then
      echo 'pid file not exist, Server should has been stopped'
    fi
      JSVC_PID=`cat $PID_FILE`
      kill -9 $JSVC_PID
    echo "Server Server Stop [ OK ]"
}

case "$1" in
  start)
    ## Start Server
    start
    exit $?
    ;;

  stop)
    ## Stop Server
    stop
    exit $?
    ;;

  restart)
    ## Stop Server
    stop
    start
    exit $?
    ;;

  *)
    echo "Usage Server start/stop/restart"
    exit 1
    ;;
esac

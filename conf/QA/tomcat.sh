#!/bin/sh
#################################################################################
#
#   Apache Tomcat whoth jsvc and APR
#
#################################################################################

source $(dirname $0)/env.sh

APP_HOME=/data/projects/${APP_NAME}
APP_CONFIG_HOME=/data/config/${APP_NAME}
APP_LOG_HOME=/data/log/${APP_NAME}
## Only set JAVA_HOME if not already set
[ -z "$JAVA_HOME" ] && JAVA_HOME=/usr/java/default
## Only set CATALINA_HOME if not already set
[ -z "$CATALINA_HOME" ] && CATALINA_HOME=/usr/local/tomcat7
export CATALINA_BASE=${APP_CONFIG_HOME}/tomcat
export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:$CATALINA_HOME/lib
export NLS_LANG=AMERICAN_AMERICA.ZHS16GBK
export LANG=en_US.UTF-8
TMP_DIR=/tmp/${APP_NAME}
PID_FILE=${APP_HOME}/jsvc.pid
export DAEMON_HOME=${CATALINA_HOME}/bin/commons-daemon/unix

CLASSPATH=$JAVA_HOME/lib/tools.jar:${CATALINA_HOME}/lib:\
$CATALINA_HOME/bin/bootstrap.jar:$CATALINA_HOME/bin/commons-daemon.jar:$CATALINA_HOME/bin/tomcat-juli.jar

export CATALINA_OPTS="-Djvm.process.name=${APP_NAME} -Dapp.instance.config=${APP_CONFIG_HOME}
 -Dlog4j.configuration=file://${APP_CONFIG_HOME}/log4j.xml -Dapp.log.home=${APP_LOG_HOME} -Dnet.jawr.debug.on=false"

start() {
    echo "Starting Tomcat Server...... "
    ## remove cache
    #rm -rf ${CATALINA_HOME}/work/*
    ##
    ## Start Tomcat
    ##
    $DAEMON_HOME/jsvc \
    -jvm server \
    -home $JAVA_HOME \
    -Dcatalina.home=$CATALINA_HOME \
    -Dcatalina.base=$CATALINA_BASE \
    $CATALINA_OPTS \
    -Djava.io.tmpdir=$TMP_DIR \
    -wait 60 \
    -pidfile $PID_FILE \
    -outfile ${APP_LOG_HOME}/jsvc.log \
    -errfile ${APP_LOG_HOME}/jsvc.log \
    $JVM_OPTS \
    -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=${APP_LOG_HOME} \
    -cp $CLASSPATH \
    org.apache.catalina.startup.Bootstrap
    echo "Tomcat Server Start [ OK ]"
}

stop() {
    echo "Stopping Tomcat Server......"
    ##
    ## Stop Tomcat
    ##
    if [ ! -f "$PID_FILE" ];
    then
      echo 'pid file not exist, Tomcat should has been stopped'
      exit 0
    fi
    JSVC_PID=`cat $PID_FILE`
    $DAEMON_HOME/jsvc \
    -stop \
    -pidfile $PID_FILE \
    org.apache.catalina.startup.Bootstrap
    if [ -f "/proc/$JSVC_PID/status" ];
    then
      JSVC_PPID=`grep '^PPid:' /proc/$JSVC_PID/status | grep -o '[0-9]*'`
      if [ -f "/proc/$JSVC_PPID/exe" ];
      then
        echo "PPID: $JSVC_PPID, will force kill after 5 seconds"
        sleep 5
        kill -9 $JSVC_PPID
      fi
    fi
    if [ -f "/proc/$JSVC_PID/exe" ];
    then
      echo "PID: $JSVC_PID, will force kill after 5 seconds"
      sleep 5
      kill -9 $JSVC_PID
    fi
    echo "Tomcat Server Stop [ OK ]"
}

case "$1" in
  start)
    ## Start Tomcat
    start
    exit $?
    ;;

  stop)
    ## Stop Tomcat
    stop
    exit $?
    ;;

  *)
    echo "Usage tomcat start/stop"
    exit 1
    ;;
esac
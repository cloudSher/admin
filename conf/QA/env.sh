#!/bin/sh

APP_NAME=s-workbench
export JVM_OPTS="-Xmx2048m -Xms1024m -Xmn512m -Xss512k -XX:PermSize=256m -XX:SurvivorRatio=8 -XX:ParallelGCThreads=20
 -XX:ParallelCMSThreads=20 -XX:+UseConcMarkSweepGC -XX:+UseCMSCompactAtFullCollection
 -XX:CMSFullGCsBeforeCompaction=5 -XX:CMSInitiatingOccupancyFraction=70 -XX:+PrintClassHistogram"
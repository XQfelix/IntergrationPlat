#!/bin/sh
cd `dirname $0`

MAIN=com.uinv.inter.intergration.IntergrationApplication

export PRO_PID=`ps -ef | grep $MAIN|grep -v grep|awk '{print $2}'`
echo $PRO_PID

if [ -n "$PRO_PID" ]; then
   kill -9 $PRO_PID
fi

sleep 2

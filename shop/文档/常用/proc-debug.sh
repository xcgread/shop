#!/bin/bash
starttime=`date +'%Y-%m-%d %H:%M:%S'`
echo '开始执行时间-'$starttime >> /data/kh_shell/proc_debug.log



su - oracle -c "sqlplus VJSP_JSWZ_190601/wzld9999<< EOF
var a1 VARCHAR2;
var a2 VARCHAR2;
var a3 VARCHAR2;
var a4 VARCHAR2;
var a5 VARCHAR2;
var a6 VARCHAR2;
var a7 VARCHAR2;
var V8 refcursor;
CALL PROC_WZCHECK_COUNT_BY_MONTH(:a1,:a2,:a3,:a4,:a5,:a6,:a7,:V8);
commit;
disconnect
quit
EOF"

etime=`date +'%Y-%m-%d %H:%M:%S'`
echo 'PROC_WZCHECK_COUNT_BY_MONTH完成时间-'$etime >> /data/kh_shell/proc_debug.log
########################################################
su - oracle -c "sqlplus VJSP_JSWZ_190601/wzld9999<< EOF
var a1 VARCHAR2;
var a2 VARCHAR2;
var a3 VARCHAR2;
var a4 VARCHAR2;
var a5 VARCHAR2;
var a6 VARCHAR2;
var a7 VARCHAR2;
var V8 refcursor;
CALL PROC_WZCHECK_COUNT_BY_MONTH_SQ(:a1,:a2,:a3,:a4,:a5,:a6,:a7,:V8);
commit;
disconnect
quit
EOF"

etime=`date +'%Y-%m-%d %H:%M:%S'`
echo 'PROC_WZCHECK_COUNT_BY_MONTH_SQ完成时间-'$etime >> /data/kh_shell/proc_debug.log
########################################################
su - oracle -c "sqlplus VJSP_JSWZ_190601/wzld9999<< EOF
var a1 VARCHAR2;
var a2 VARCHAR2;
var a3 VARCHAR2;
var a4 VARCHAR2;
var a5 VARCHAR2;
var a6 VARCHAR2;
var a7 VARCHAR2;
var V8 refcursor;
CALL PROC_WZCHECK_COUNT_BY_MONTH_WG(:a1,:a2,:a3,:a4,:a5,:a6,:a7,:V8);
commit;
disconnect
quit
EOF"

etime=`date +'%Y-%m-%d %H:%M:%S'`
echo 'PROC_WZCHECK_COUNT_BY_MONTH_WG完成时间-'$etime >> /data/kh_shell/proc_debug.log





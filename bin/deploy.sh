#!/bin/bash

# 修改APP_NAME为GiteeGo应用名
APP_NAME=melon-admin


PROG_NAME=$0
ACTION=$1
APP_START_TIMEOUT=30    # 等待应用启动的时间
APP_PORT=8080         # 应用端口
HEALTH_CHECK_URL=http://127.0.0.1:8080  # 应用健康检查URL
HEALTH_CHECK_TEXT="欢迎使用Melon后台管理框架，当前版本：v1.0.0，请通过前端地址访问。"  # 应用健康检查URL
HEALTH_CHECK_FILE_DIR=/root/jar/status   # 脚本会在这个目录下生成nginx-status文件
APP_HOME=/root/jar/${APP_NAME}        # 从output.tar.gz中解压出来的jar包放到这个目录下
JAR_NAME=${APP_HOME}/target/${APP_NAME}.jar    # jar包的名字
JAVA_OUT=${APP_HOME}/logs/start.log     #应用的启动日志

# 创建出相关目录
mkdir -p ${HEALTH_CHECK_FILE_DIR}
mkdir -p ${APP_HOME}
mkdir -p ${APP_HOME}/logs
usage() {
    echo "Usage: $PROG_NAME {start|stop|restart}"
    exit 2
}

health_check() {
    exptime=0
    echo "checking ${HEALTH_CHECK_URL}"
    while true
        do
            if [ $exptime -gt ${APP_START_TIMEOUT} ]; then
                echo 'app start failed'
                exit 1
            fi

            ConsulResult=$(curl -s --connect-timeout 500 --max-time 2  ${HEALTH_CHECK_URL} )

            if [ $HEALTH_CHECK_TEXT = $ConsulResult ]; then
                echo "app is started ！"
                break
            fi

            sleep 1
            ((exptime++))
            echo -e "\rWait app to pass health check: $exptime..."

        done
    echo "check ${HEALTH_CHECK_URL} success"
}
start_application() {
    echo "starting java process"
    nohup java -jar -Xmx256m -Xms128m ${JAR_NAME} > ${JAVA_OUT} 2>&1 &
    echo "started java process"
}

stop_application() {
   checkjavapid=`ps -ef | grep java | grep ${APP_NAME} | grep -v grep |grep -v 'deploy.sh'| awk '{print$2}'`
   
   if [[ ! $checkjavapid ]];then
      echo -e "\rno java process"
      return
   fi

   echo "stop java process"
   times=60
   for e in $(seq 60)
   do
        sleep 1
        COSTTIME=$(($times - $e ))
        checkjavapid=`ps -ef | grep java | grep ${APP_NAME} | grep -v grep |grep -v 'deploy.sh'| awk '{print$2}'`
        if [[ $checkjavapid ]];then
            kill -9 $checkjavapid
            echo -e  "\r        -- stopping java lasts `expr $COSTTIME` seconds."
        else
            echo -e "\rjava process has exited"
            break;
        fi
   done
   echo ""
}
start() {
    start_application
    health_check
}
stop() {
    stop_application
}
case "$ACTION" in
    start)
        start
    ;;
    stop)
        stop
    ;;
    restart)
        stop
        start
    ;;
    *)
        usage
    ;;
esac

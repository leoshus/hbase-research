#!/usr/bin/env bash
#
#/**
# * Licensed to the Apache Software Foundation (ASF) under one
# * or more contributor license agreements.  See the NOTICE file
# * distributed with this work for additional information
# * regarding copyright ownership.  The ASF licenses this file
# * to you under the Apache License, Version 2.0 (the
# * "License"); you may not use this file except in compliance
# * with the License.  You may obtain a copy of the License at
# *
# *     http://www.apache.org/licenses/LICENSE-2.0
# *
# * Unless required by applicable law or agreed to in writing, software
# * distributed under the License is distributed on an "AS IS" BASIS,
# * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# * See the License for the specific language governing permissions and
# * limitations under the License.
# */

# Modelled after $HADOOP_HOME/bin/start-hbase.sh.

# Start hadoop hbase daemons.
# Run this on master node.
usage="Usage: start-hbase.sh"

bin=`dirname "${BASH_SOURCE-$0}"` #把脚本所在目录赋予bin  
bin=`cd "$bin">/dev/null; pwd`#校验bin的正确性  

. "$bin"/hbase-config.sh #初始化hbase配置,设置全局环境变量   linux 脚本前加“.   ”即“点+空格”，脚本执行结束后，脚本中的export命令影响全局环境变量。

# start hbase daemons
errCode=$?
if [ $errCode -ne 0 ]
then
  exit $errCode
fi


if [ "$1" = "autorestart" ]
then
  commandToRun="autorestart"
else 
  commandToRun="start"
fi

# HBASE-6504 - only take the first line of the output in case verbose gc is on
#hbase.cluster.distributed  Hbase的运行模式。false是单机模式，true是分布式模式。若为false,Hbase和Zookeeper会运行在同一个JVM里面。默认:false  
distMode=`$bin/hbase --config "$HBASE_CONF_DIR" org.apache.hadoop.hbase.util.HBaseConfTool hbase.cluster.distributed | head -n 1`


if [ "$distMode" == 'false' ] 
then
  "$bin"/hbase-daemon.sh --config "${HBASE_CONF_DIR}" $commandToRun master $@  #包装hbase master,regionserver,zookeeper等服务从后台启动  
else
  "$bin"/hbase-daemons.sh --config "${HBASE_CONF_DIR}" $commandToRun zookeeper
  "$bin"/hbase-daemon.sh --config "${HBASE_CONF_DIR}" $commandToRun master 
  "$bin"/hbase-daemons.sh --config "${HBASE_CONF_DIR}" \
    --hosts "${HBASE_REGIONSERVERS}" $commandToRun regionserver
  "$bin"/hbase-daemons.sh --config "${HBASE_CONF_DIR}" \
    --hosts "${HBASE_BACKUP_MASTERS}" $commandToRun master-backup
fi

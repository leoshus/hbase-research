/*
 * Copyright 2010 The Apache Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hadoop.hbase;

import java.io.IOException;

/**
 * Coprocess interface.
 */
public interface Coprocessor {
  static final int VERSION = 1;

  /** Highest installation priority */
  static final int PRIORITY_HIGHEST = 0;
  /** High (system) installation priority */
  static final int PRIORITY_SYSTEM = Integer.MAX_VALUE / 4; //高优先级 定义最先被执行的协处理器   系统级协处理器
  /** Default installation priority for user coprocessors */
  static final int PRIORITY_USER = Integer.MAX_VALUE / 2; //定义其他的协处理器 按顺序执行    用户级协处理器
  /** Lowest installation priority */
  static final int PRIORITY_LOWEST = Integer.MAX_VALUE;

  /**
   * Lifecycle state of a given coprocessor instance.
   */
  public enum State {
    UNINSTALLED,//协处理器最初的状态,没有环境 也没有被初始化
    INSTALLED,//实例装载了它的环境参数
    STARTING,//协处理器将要开始工作,也就是说start() 方法将要被调用
    ACTIVE,//一旦start() 方法被调用 当前状态设置为active
    STOPPING,//stop()方法被调用之前的状态
    STOPPED//一旦stop()方法将控制权交给框架 协处理器会被设置为状态stopped
  }

  /**
   * 协处理器 开始和结束时被调用   CoprocessorEnvironment用来 在协处理器的生命周期中保持其状态,协处理器实例一直被保存在提供的环境中
   * @author shangyd
   * @date 2015年9月30日 上午10:39:55
   * 
   * @param env
   * @throws IOException
   */
  // Interface
  void start(CoprocessorEnvironment env) throws IOException;

  void stop(CoprocessorEnvironment env) throws IOException;
}

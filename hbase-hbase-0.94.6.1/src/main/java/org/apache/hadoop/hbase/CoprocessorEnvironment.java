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

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.HTableInterface;

/**
 * Coprocessor environment state.
 */
public interface CoprocessorEnvironment {

  /** @return the Coprocessor interface version */
	//协处理器接口版本
  public int getVersion();

  /** @return the HBase version as a string (e.g. "0.21.0") */
  //HBase版本
  public String getHBaseVersion();

  /** @return the loaded coprocessor instance */
  //加载的协处理器实例
  public Coprocessor getInstance();

  /** @return the priority assigned to the loaded coprocessor */
  //加载的协处理器的优先级
  public int getPriority();

  /** @return the load sequence number */
  //协处理器的序号,当协处理器加载时被设置,这反映了它的执行顺序
  public int getLoadSequence();

  /** @return the configuration */
  public Configuration getConfiguration();

  /**
   * @return an interface for accessing the given table
   * @throws IOException
   */
  //返回与传入表名参数对应的HTable实例,这允许协处理器访问实际的表数据
  public HTableInterface getTable(byte[] tableName) throws IOException;
}

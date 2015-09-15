package com.sdw.soft.test.put;

import java.io.IOException;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

/**
 * @date 2015年9月15日 上午10:57:38
 **/
public class PutDemo {

	/**
	 *	test put 
	 * @date 2015年9月15日 上午11:02:09
	 * 
	 * @throws IOException
	 */
	@Test
	public void test01() throws IOException{
		HBaseConfiguration config = (HBaseConfiguration)HBaseConfiguration.create();
		byte[] rowkey = Bytes.toBytes("p123");
		byte[] family = Bytes.toBytes("puts");
		byte[] qualifier = Bytes.toBytes("name");
		byte[] value = Bytes.toBytes("testput");
		HTable htable = new HTable(config, "test");
		Put put = new Put(rowkey);
		put.add(family, qualifier, value);
		htable.put(put);
	}
}

package com.sdw.soft.test.put;

import java.io.IOException;
import java.io.OutputStreamWriter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;


/**
 * @author shangyd
 * @date 2015年9月28日 下午4:52:34
 **/
public class Demo {

	private static Configuration config = null;
	static {
		config = HBaseConfiguration.create();
		config.set("hbase.zookeeper.property.clientport", "2181");
		config.set("hbase.zookeeper.quorum", "192.168.183.133");
		config.set("hbase.master", "192.168.183.133:60000");
		config.set("hbase.rpc.timeout", "120");
	}
	/**
	 * hbase默认配置
	 * @author shangyd
	 * @date 2015年9月29日 下午2:29:16
	 *
	 */
	@Test
	public void test01(){
		try {
			HBaseConfiguration.dumpConfiguration(config, new OutputStreamWriter(System.out));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void test02(){
		try {
			HTable table = new HTable(config, "test_standalone");
			Get get = new Get(Bytes.toBytes("row1"));
			Result res = table.get(get);
			for(KeyValue kv : res.raw()){
				System.out.println(new String(kv.getFamily()) + "----------" + new String(kv.getValue()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test03(){
		try {
			HTable table = new HTable(config, "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}

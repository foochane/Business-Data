package com.uestc.demo

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object AnalysisTest {
  def main(args: Array[String]): Unit = {

    // 创建Spark配置
    //    val sparkConf = new SparkConf().setAppName("DataGenerate").setMaster("spark://Node02:7077")
    val sparkConf = new SparkConf().setAppName("AnalysisTest").setMaster("local[*]")

    // 创建Spark SQL 客户端
    val spark = SparkSession.builder().config(sparkConf).enableHiveSupport().getOrCreate()

    spark.sql("select a.interesthobbyname,collect_set(b.customerid) " +
      "from mysql_data.c_interesthobbies a " +
      "join mysql_data.c_cust_interesthobby b " +
      "on a.id = b.interesthobbyid group by a.interesthobbyname").collect.foreach(println)
  }
}

package com.uestc.demo

import com.uestc.utils.SaveData
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object AnalysisTest {
  def main(args: Array[String]): Unit = {

    // 创建Spark配置
    //    val sparkConf = new SparkConf().setAppName("DataGenerate").setMaster("spark://Node02:7077")
    val sparkConf = new SparkConf().setAppName("AnalysisTest").setMaster("local[*]")

    // 创建Spark SQL 客户端
    val spark = SparkSession.builder().config(sparkConf).enableHiveSupport().getOrCreate()

    val hobbiesAnalysis = spark.sql("select a.interesthobbyname,count(b.customerid) " + //collect_set(b.customerid)列出每个customerid
      "from mysql_data.c_interesthobbies a " +
      "join mysql_data.c_cust_interesthobby b " +
      "on a.id = b.interesthobbyid group by a.interesthobbyname")
      .withColumnRenamed("count(customerid)", "count")// 重命名列名
//hobbiesAnalysis中的数据
//    +-----------------+-----+
//    |interesthobbyname|count|
//    +-----------------+-----+
//    |       大自然生态|   26|
//    |             表演|    7|
//    |         数码科技|   27|
//    |         家居生活|   22|
//    |         咖啡甜品|   16|
//    |       手工艺制作|   17|
//      ...

    //保存到hive中
    SaveData.insertHive(spark, "hobbies_classify", hobbiesAnalysis)
  }
}

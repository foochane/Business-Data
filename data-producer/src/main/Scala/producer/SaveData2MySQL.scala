package producer

import java.util.Properties

import org.apache.spark.SparkConf
import org.apache.spark.sql.{SaveMode, SparkSession}
import utils.SaveData

object SaveData2MySQL {
  def main(args: Array[String]): Unit = {


    val sparkConf = new SparkConf().setAppName("Test Spark ").setMaster("local[*]")
    // 创建sparkSession
    val spark = SparkSession.builder().config(sparkConf).enableHiveSupport().getOrCreate()

    val USER_INFO_TABLE = "user_info"

    //读取数据
    val rdd = spark.sql("SELECT * FROM user_info")
    rdd.show()

    //保存数据
    SaveData.insertMySQL(USER_INFO_TABLE,rdd)

    spark.stop()
  }
}

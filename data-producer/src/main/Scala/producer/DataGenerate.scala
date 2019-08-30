package producer

import model.UserInfo
import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

/**
 * Created by fucheng on 2019/8/29
 */

object MockDataGenerate {


  /**
   * 模拟用户信息表
   *
   * @return
   */
  private def mockUserInfo(): Array[UserInfo] = {

    val rows = ArrayBuffer[UserInfo]()
    val sexes = Array("male", "female")
    val random = new Random()

    // 随机产生100个用户的个人信息
    for (i <- 0 to 10) {
      val userid = i
      val username = "user" + i
      val name = "name" + i
      val age = random.nextInt(60)
      val professional = "professional" + random.nextInt(100)
      val city = "city" + random.nextInt(100)
      val sex = sexes(random.nextInt(2))
      rows += UserInfo(userid, username, name, age,
        professional, city, sex)
    }
    rows.toArray
  }



  /**
   * 将DataFrame插入到Hive表中
   *
   * @param spark     SparkSQL客户端
   * @param tableName 表名
   * @param dataDF    DataFrame
   */
  private def insertHive(spark: SparkSession, tableName: String, dataDF: DataFrame): Unit = {
    spark.sql("DROP TABLE IF EXISTS " + tableName)
    dataDF.write.saveAsTable(tableName)
  }

  val USER_INFO_TABLE = "user_info"

  /**
   * 主入口方法
   *
   * @param args 启动参数
   */
  def main(args: Array[String]): Unit = {

    // 创建Spark配置
//    val sparkConf = new SparkConf().setAppName("DataGenerate").setMaster("spark://Node02:7077")
    val sparkConf = new SparkConf().setAppName("DataGenerate").setMaster("local[*]")

    // 创建Spark SQL 客户端
    val spark = SparkSession.builder().config(sparkConf).enableHiveSupport().getOrCreate()


    // 模拟数据
    val userInfoData = this.mockUserInfo()

    // 将模拟数据装换为RDD
    val userInfoRdd = spark.sparkContext.makeRDD(userInfoData)

    // 加载SparkSQL的隐式转换支持
    import spark.implicits._

    // 将用户信息数据转换为DF保存到Hive表中
    val userInfoDF = userInfoRdd.toDF()
    insertHive(spark, USER_INFO_TABLE, userInfoDF)

    spark.close
  }

}


/*
注意要打开metastore的服务端：hive --service metastore
提交命令：
spark-submit \
--class SparkHiveDemo \
--master spark://Node02:7077 \
--jars /usr/share/java/mysql-connector-java-5.1.45.jar \
./jar包.jar
*/
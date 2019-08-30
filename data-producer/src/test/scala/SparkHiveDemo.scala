import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
 * Created by fucheng on 2019/8/29.
 */
object SparkHiveDemo {
  def main(args: Array[String]): Unit = {


    val sparkConf = new SparkConf()
      .setAppName("Test Spark ")
//      .setMaster("spark://Node02:7077")
      .setMaster("local[*]")
    // 创建sparkSession
    val spark = SparkSession
      .builder()
      .config(sparkConf)
      .enableHiveSupport()
      .getOrCreate()

    spark.sql("show databases").show()
    spark.sql("SELECT * FROM analysis.test_transheader").show()
    spark.close()
  }
}

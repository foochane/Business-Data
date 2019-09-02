package com.uestc.utils

import java.util.Properties


import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}

object SaveData {

  /**
   * 将DataFrame插入到Hive表中
   *
   * @param spark     SparkSQL客户端
   * @param tableName 表名
   * @param dataDF    DataFrame
   */
  def insertHive(spark: SparkSession, tableName: String, dataDF: DataFrame): Unit = {
    spark.sql("DROP TABLE IF EXISTS " + tableName)
    dataDF.write.saveAsTable(tableName)
  }

  /**
   * 插入数据到MySQL
   * @param tableName 表名
   * @param dataDF    DataFrame
   */
  def insertMySQL(tableName: String, dataDF:DataFrame): Unit = {

    //数据库相关配置
    val url = PropertiesUtils.loadProperties.getProperty("jdbc.url")
    val prop = new Properties()
    prop.setProperty("user", PropertiesUtils.loadProperties.getProperty("jdbc.user"))
    prop.setProperty("password", PropertiesUtils.loadProperties.getProperty("jdbc.password"))
    prop.setProperty("useSSL", PropertiesUtils.loadProperties.getProperty("jdbc.useSSL"))

    //写入mysql数据库 ， Overwrite:覆盖 ;Append: 追加; Ignore:忽略
    dataDF.toDF().write.mode(SaveMode.Overwrite) jdbc(url, tableName, prop)

  }
}



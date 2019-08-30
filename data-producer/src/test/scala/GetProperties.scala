import java.io.FileInputStream
import java.util.Properties

object GetProperties {

  def main(args: Array[String]): Unit = {
    val properties = new Properties()
    val path = Thread.currentThread().getContextClassLoader.getResource("jdbc.properties").getPath //文件要放到resource文件夹下
    properties.load(new FileInputStream(path))
    println(properties.getProperty("jdbc.user"))

  }
}

# 商业大数据分析系统
## 1 整体框架

![框架图](https://github.com/foochane/business-data/raw/master/images/框架图.png)

## 2 代码结构
|模块名称|模块功能|
|:--|:--|
|data-producer  |数据生成模块|
|data-analysis  |数据分析模块|
|web-api        |服务端接口模块|
|web-ui         |可视化模块|

## 3 要写的代码

用Scala或者Java编写如下接口：
- 读取hdfs上的数据（spark sql）
- 写入数据到hdfs（spark sql）
- 查询hive中的数据（spark sql）
- 在hive中创建表并插入数据（spark sql）
- hive中数据导入到mysql（spark sql）
- 读取hbase中的数据
- 存储数据到hbase
- 从kafka中读取数据写入hbase（spark streaming）
- 从kafka中读取数据写入到mysql （spark streaming）
- 常见机器学习算法封装
- 读取mysql中数据发送到浏览器页面（spring boot，mybatis）


## 4 代码提交和下载

**代码各个工程分别用IDE打开，不要直接打开根目录**

### 下载代码
```
git clone https://github.com/foochane/business-data.git
```

### 上传代码
1. add
```
git add .
```
2. commit
```
git commit -m "添加注释"
```
3. push
```
git push origin master
```
**如果push失败，先pull一下代码**

### 下拉代码
```
git push origin master
```
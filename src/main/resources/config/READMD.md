## 在此文件夹新建数据库配置文件`druid.properties`

>
> 这个文件夹的配置文件不会同步到仓库

```properties
driverClassName = net.sf.log4jdbc.DriverSpy
url = jdbc:log4jdbc:mysql://localhost:3306/PXX?useUnicode=true&characterEncoding=utf-8&useSSL=true
username = pxx_admin
password = 123456
filter = slf4j
testWhileIdle = false
```

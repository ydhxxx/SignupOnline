#demo项目

####环境
1. SpringBoot-2.3.3版本,JDK1.8及以上
2. Mysql-5.7及以上版本


####开发
1. 执行sql脚本初始化数据库
2. 开发时请修改*application.properties*数据库配置信息
3. 运行*DemoApplication*，访问*http://localhost:8080/swagger-ui.html*可查看Swagger2接口文档




####打包部署
1. 修改*application.properties*的*spring.profiles.active*属性
2. Run/Debug Configurations  --> + maven --> Command line : *clean package -DskipTests=true*  -->OK









####项目说明
1. 项目是前后端分离开发模式，后端只提供接口
2. 开发时请统一返回的数据格式
3. 开发前建议先看一下本项目从DAO到Controller层的编码风格
4. 访问swagger2的登陆用户名和密码是admin:123,可查看application-dev.properties的swagger2的具体配置
5. 系统根据swagger.enabled的开启与否来决定是否需要授权，所以开发模式时并不需要授权

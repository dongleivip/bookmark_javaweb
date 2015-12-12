# bookmark_javaweb

##使用的开发环境
--数据库：mysql 5
--服务器：tomcat 7
--IDE：MyEclipse 10.7
--jdk：1.7.0_45

##数据库配置
--需要创建数据库：执行工程目录下sql目录中有数据库脚本：bookmark.sql  
--必要的修改：修改config目录下c3p0-config.xml配置中数据库链接地址
--初始化数据：InitDataBaseServlet随web服务启动时自动运行，将config目录下的bookmark.json中的数据导入数据库
--ps:如果不再需要InitDataBaseServlet随web服务启动,删除WebRoot/WEB-INF/web.xml中InitDataBaseServlet的<load-on-startup>属性



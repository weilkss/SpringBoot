# Spring Boot
##### springboot整合mybits （XML配置文件）
##### JDBC连接数据库
##### mysql增删改查实现
##### mysql上传文件功能



### 在linux上部署springboot：

```shell

##查看端口
netstat -tunlp|grep 8081

##杀死进程
kill -9 {PID}

##永久启动服务
nohup java -jar demo-0.0.1-SNAPSHOT.jar --spring.profiles.active=pro > system.log 2>&1&

```
 
### nginx 重启

启动代码格式：nginx安装目录地址：/usr/local/nginx/sbin/
    
```shell

./nginx -s reload

```


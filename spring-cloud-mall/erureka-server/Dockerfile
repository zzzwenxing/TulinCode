# 基于哪个镜像
From java:8
# 复制文件到容器
ADD erureka-server-0.0.1-SNAPSHOT.jar /app.jar
# 配置容器启动后执行的命令
ENTRYPOINT ["java","-jar","/app.jar"]
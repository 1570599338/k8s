# 基础镜像
FROM openjdk:8

# 作者
MAINTAINER lquan

# 指定在创建容器后。终端默认登录的进来工作目录，一个落脚点
#WORKDIR /usr/local/work

# VOLUME 指定临时文件目录为/tmp，在主机/var/lib/docker目录下创建了一个临时文件并链接到容器的/tmp
# 容器数据卷，用于数据保存和持久化的工作
VOLUME /tmp


# 将宿主机目录下的文件拷贝进镜像且会自动处理URL和解压tar压缩包
#ADD ./target/docker-demo-0.0.1-SNAPSHOT.jar /lquanDocker.jar

ADD  docker-demo-0.0.1-SNAPSHOT.jar /lquandocker.jar

#运行jar包
RUN bash -c 'touch  /lquandocker.jar'

# 声明时区
#RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone

# 也是用来指定一个容器启动时要运行的命令
ENTRYPOINT ["java","-jar","/lquandocker.jar"]

#暴露6001端口为微服务
EXPOSE 8080





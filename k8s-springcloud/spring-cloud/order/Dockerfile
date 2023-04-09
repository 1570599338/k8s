# 基础镜像
FROM openjdk:8

# 作者
MAINTAINER lquan

#切换root用户，后面的命令以root用户来执行
USER root

#  设置环境变量
ENV LANG  zh_CN.UTF-8
ENV LC_CTYPE zh_CN.UTF-8
ENV USNAME  lquan
ENV USID 601

# RUN 后面为linux命令，groupadd和useradd相当于在基础镜像系统内创建属组合用户
#RUN groupadd #{USNAME}  -g ${USID};useradd ${USNAME} -u ${USID} -g ${USNAME} -G  bes -d /${USNAME}
# 创建一个新的组，并添加组 ID。
RUN groupadd ${USNAME}  -g ${USID}

#添加用户
# -d  如果不指定，就在/home下，创建一个同名的家目录，但是我们如果是指定的，不想放在/home下面，或者你放在/home下面不想跟用户名同名，都可以用-d来指定。
# -g  不要去尝试修改初始组，一旦这样回车就会发现，你的user1用户根本就不会生成一个叫user1用户的用户组，因为初始组是root用户组，这样会让管理员非常难以理解
# -G  附加组中的分隔符要用逗号分割多个组，那怕你把一个用户加到100个组里面都是可以的。
RUN useradd ${USNAME} -u ${USID}  -g ${USNAME}  -G root,bin -d /${USNAME}


#Copy 将当前执行dockerfile 文件机器上的目录下文件拷贝到容器内的某个目录下，
# --chown同事给文件设置用户和属组
ADD  --chown=lquan:lquan   ./target/*.jar /${USNAME}/order.jar
COPY --chown=lquan:lquan   ./bootstrap.sh   /${USNAME}/


# 设置权限
RUN chmod  -R 775  /${USNAME}
RUN chmod  -R 775  /${USNAME}/*.sh


# 设置工作目录，即基于次镜像创建的容器启动后的默认目录
WORKDIR /${USNAME}

# VOLUME 指定临时文件目录为/tmp，在主机/var/lib/docker目录下创建了一个临时文件并链接到容器的/tmp
# 容器数据卷，用于数据保存和持久化的工作
VOLUME /tmp


# 将宿主机目录下的文件拷贝进镜像且会自动处理URL和解压tar压缩包
#ADD ./target/docker-demo-0.0.1-SNAPSHOT.jar /lquanDocker.jar
#ADD  ./target/*.jar /lquan/eurekaDocker.jar

#运行jar包
#RUN bash -c 'touch  /lquandocker.jar'

# 声明时区
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone

# 切换用户，后面的命令以该用户执行
USER ${USNAME}

# 也是用来指定一个容器启动时要运行的命令
#ENTRYPOINT ["java","-jar","/lquan/eurekaDocker.jar"]
ENTRYPOINT ["sh","/lquan/bootstrap.sh"]

#暴露6001端口为微服务
EXPOSE 8082

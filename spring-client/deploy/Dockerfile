#FROM openjdk:8-jdk-alpine
FROM java:8-jre
VOLUME /tmp
ADD app.jar /app.jar
#RUN bash -c 'touch app.jar'

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

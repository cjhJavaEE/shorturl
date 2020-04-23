FROM  java:8
MAINTAINER chenjunhan "759272817@qq.com"
VOLUME /tmp
ADD shorturl-2.0.1.RELEASE.jar shorturl.jar
RUN bash -c 'touch /shorturl.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/shorturl.jar"]

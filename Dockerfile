FROM java:8
VOLUME /tmp
ADD target/spring-boot-features-1.0-SNAPSHOT.jar features.jar
RUN bash -c 'touch /features.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Xdebug","-Xrunjdwp:server=y,transport=dt_socket,suspend=n","-jar","/features.jar"]
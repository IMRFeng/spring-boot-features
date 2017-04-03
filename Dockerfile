FROM java:8
VOLUME /tmp
ADD target/spring-boot-features-1.0-SNAPSHOT.jar spring-boot-features.jar
RUN bash -c 'touch /spring-boot-features.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Xdebug","-Xrunjdwp:server=y,transport=dt_socket,suspend=n","-jar","/spring-boot-features.jar"]
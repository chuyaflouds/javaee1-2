FROM openjdk:8-jre
MAINTAINER wyf

ENTRYPOINT ["/usr/bin/java","-jar","/usr/share/myservice/myservice.war"]

ADD target/2-02/WEB-INF/lib         /usr/share/myservice/lib
ARG JAR_FILE
ADD target/${JAR_FILE}      /usr/share/myservice/myservice.war
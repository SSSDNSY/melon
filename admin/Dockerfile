FROM openjdk:7-jre-slim
MAINTAINER sssdnsy

ENV PARAMS=""

ADD target/melon-admin-*.jar /app.jar

#ENTRYPOINT ["java", "-jar", "$PARAMS /app.jar"]
ENTRYPOINT ["sh","-c","java -jar /app.jar $PARAMS"]
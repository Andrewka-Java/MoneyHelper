FROM openjdk:8-jdk-alpine

ENV MYSQL_USER_DB=test
ENV MYSQL_USER=mysql
ENV MYSQL_USER_PWD=mysqlpassword
ENV MYSQL_HOST=db-container
ENV MYSQL_PORT=3306

ADD app.jar app.jar
ENTRYPOINT ["java", "-jar", "./app.jar"]
EXPOSE 9090

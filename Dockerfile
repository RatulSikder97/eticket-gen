FROM openjdk:21
ARG JAR_FILE=target/*.war
COPY ${JAR_FILE} app.war
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.war", "--address=0.0.0.0"]
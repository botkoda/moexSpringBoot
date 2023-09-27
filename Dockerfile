FROM openjdk:11
COPY target/moex-1.jar moex.jar
EXPOSE 8080
CMD ["java", "-jar", "moex.jar"]
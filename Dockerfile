FROM openjdk:8-jre-alpine
MAINTAINER Nmikz <trinhthenguyen1997@gmail.com>
ADD ./target/location-api-0.0.1-SNAPSHOT.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/location-api-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080
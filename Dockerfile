FROM  openjdk:17-oracle
ADD build/libs/* shipping-service-0.0.1-SNAPSHOT.jar
EXPOSE 8083
ENTRYPOINT [ "java", "-jar", "shipping-service-0.0.1-SNAPSHOT.jar"]
FROM openjdk:8-jdk-alpine
ADD target/Core_Product-*.jar coreproduct.jar
EXPOSE 8091
ENTRYPOINT ["java","-jar", "coreproduct.jar"]
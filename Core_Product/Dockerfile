FROM openjdk:8
ADD target/Core_Product-*.jar coreproduct.jar
EXPOSE 8091
ENTRYPOINT ["java","-jar", "coreproduct.jar"]
FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/*.jar
ARG JAVA_AGENT_FILE=/elastic-apm-agent-1.39.0.jar

COPY ${JAR_FILE} spring-products-proof-0.0.1-SNAPSHOT.jar
COPY ${JAVA_AGENT_FILE} elastic-apm-agent-1.39.0.jar

ENTRYPOINT ["java", "-javaagent:/elastic-apm-agent-1.39.0.jar", "-Delastic.apm.service_name=nueva-prueba-products", "-Delastic.apm.application_packages=com.springproducts", "-Delastic.apm.server_url=http://172.24.1.16:8200", "-jar", "/spring-products-proof-0.0.1-SNAPSHOT.jar"]

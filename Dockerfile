FROM maven:3.8.3-openjdk-17

WORKDIR /usr/local/backend

COPY . /usr/local/backend

RUN mvn clean package -Pdev

EXPOSE 8080

ENTRYPOINT ["java","-jar","target/QRescue-0.0.1-SNAPSHOT.jar"]

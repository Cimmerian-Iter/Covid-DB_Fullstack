FROM eclipse-temurin:17-jdk-jammy

RUN apt-get update
RUN apt-get --yes install git
COPY . /var/project
WORKDIR /var/project
RUN ./gradlew build

CMD java -jar build/libs/covid-api-0.0.1-SNAPSHOT.jar
FROM eclipse-temurin:17-jdk-jammy

RUN apt-get update
RUN apt-get --yes install git
WORKDIR /src1
RUN git clone https://github.com/Cimmerian-Iter/Covid-DB_Fullstack
WORKDIR /src1/Covid-DB_Fullstack
RUN ./gradlew build
CMD java -jar build/libs/covid-api-0.0.1-SNAPSHOT.jar
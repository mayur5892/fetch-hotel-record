FROM openjdk:8-jre
MAINTAINER mapandey@tavisca.com

COPY ./target/hotel-record-fetch-0.0.1-SNAPSHOT.jar /usr/src/hotelrecord/
WORKDIR /usr/src/hotelrecord
EXPOSE 8100
CMD ["java", "-jar", "hotel-record-fetch-0.0.1-SNAPSHOT.jar"]
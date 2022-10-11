FROM openjdk:11.0-jre-slim

COPY target/highflip-server-*.jar /home/highflip/

EXPOSE 8751

WORKDIR /home/highflip/

ENTRYPOINT java -jar highflip-server-*.jar
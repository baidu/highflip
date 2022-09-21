FROM openjdk:11

COPY target/highflip-server-*.jar /home/highflip/

EXPOSE 8751

WORKDIR /home/highflip/

ENTRYPOINT java -jar highflip-server-*.jar
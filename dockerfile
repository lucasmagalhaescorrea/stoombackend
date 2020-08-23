FROM openjdk:8
COPY ./build/libs/backend.jar /usr/src/backend/
WORKDIR /usr/src/backend
EXPOSE 8080/tcp
CMD ["java", "-jar", "backend.jar"]
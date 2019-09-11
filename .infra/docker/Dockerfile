FROM java:8
COPY target/To-Do-RestAPI-0.0.1-SNAPSHOT.jar /var/www/to-doRestAPI/

WORKDIR /var/www/to-doRestAPI/
EXPOSE 8080
RUN chmod 777 To-Do-RestAPI-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","To-Do-RestAPI-0.0.1-SNAPSHOT.jar"]
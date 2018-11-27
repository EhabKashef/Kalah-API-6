# Start with a base image containing Java runtime
FROM openjdk:8-jre

# Add Maintainer Info
LABEL maintainer="ehab.mn.elkashef@gmail.com"

 
# The application's jar file
ARG JAR_FILE=target/technicalAssessment-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} technicalAssessment.jar

# Run the jar file 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/technicalAssessment.jar"]



 
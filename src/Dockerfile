# Use the official Tomcat base image
FROM tomcat:9.0-jdk17-temporal

# Remove default web apps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy the built WAR file into Tomcat's webapps directory
COPY target/E-Library-Management.war /usr/local/tomcat/webapps/ROOT.war

# Expose the port used by Tomcat
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
FROM openjdk:21-jdk

ADD build/libs/c4bank-0.0.1-SNAPSHOT.jar c4bank.jar

ENTRYPOINT [ "java", "-jar","c4bank.jar" ]
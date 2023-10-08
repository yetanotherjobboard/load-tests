FROM eclipse-temurin:17-jdk
COPY . /workspace
WORKDIR /workspace
CMD ./run-gatling.sh

#keytool -import -alias tesco -keystore $JAVA_HOME/lib/security/cacerts -file TescoRootCA.pem -keypass changeit -storepass changeit -noprompt
./gradlew generateSources gatlingRun
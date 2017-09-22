cd ..
mvn clean compile install
cd JSwu-client
mvn clean
mvn org.apache.maven.plugins:maven-assembly-plugin:3.1.0:single
mv ./target/*.jar ../build/JSwu.jar
rm -rf ./target/
cd ..
mvn clean
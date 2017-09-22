cd ..
call mvn clean compile install
cd JSwu-client
call mvn clean
call mvn org.apache.maven.plugins:maven-assembly-plugin:3.1.0:single
mv ./target/*.jar ../build/JSwu.jar
rm -rf ./target/
cd ..
call mvn clean
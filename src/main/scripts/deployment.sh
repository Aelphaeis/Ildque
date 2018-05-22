cp .travis.settings.xml $HOME/.m2/settings.xml
sed -i 's/${env.prefix}/'$prefix'/g'  src/main/resources/configuration.xml
sed -i 's/${env.token}/'$token'/g'  src/main/resources/configuration.xml
mvn tomcat7:redeploy 
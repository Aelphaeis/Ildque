cp .travis.settings.xml $HOME/.m2/settings.xml
sed -i 's/${env.prefix}/'$prefix'/g' .travis.configuration.xml
sed -i 's/${env.token}/'$token'/g' .travis.configuration.xml
cp .travis.configuration.xml src/main/resources/configuration.xml
mvn tomcat7:redeploy 
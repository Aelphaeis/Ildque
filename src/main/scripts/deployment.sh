cp .travis.settings.xml $HOME/.m2/settings.xml
cp .travis.configuration.xml src/main/resources/configuration.xml
mvn tomcat7:redeploy
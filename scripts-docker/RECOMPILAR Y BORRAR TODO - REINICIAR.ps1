cd \
cd .\Users ... \GitHub\Amplify\AmplifyInternalServiceProject\
mvn compile jib:build -Dimage=dexaxi/amplify-internal -D"jib.to.auth.username= ... " -D"jib.to.auth.password= ... "
cd \
cd C:\Users ... \GitHub\Amplify\AmplifyApplicationProject\Amplify\
mvn compile jib:build -Dimage=dexaxi/amplify-docker -D"jib.to.auth.username= ... " -D"jib.to.auth.password= ... "
docker-compose down
docker volume rm $(docker volume ls -q)
docker rmi $(docker images -q)
docker rmi $(docker images -q)
cd \
cd .\Users ... \GitHub\Amplify
docker-compose up
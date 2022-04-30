cd \
cd .\Users ... \GitHub\Amplify
docker-compose down
docker volume rm $(docker volume ls -q)
docker rmi $(docker images -q)
docker-compose up

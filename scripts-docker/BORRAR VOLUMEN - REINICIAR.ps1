cd \
cd .\Users ... \GitHub\Amplify
docker-compose down
docker volume rm $(docker volume ls -q)
docker-compose up
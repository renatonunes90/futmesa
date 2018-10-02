docker service rm futmesa_php
docker build -t futmesa-backend .
docker stack deploy -c docker-compose.yml futmesa

docker-compose build
docker-compose up


docker exec -it futmesa_db_1 bash
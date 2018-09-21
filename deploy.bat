docker service rm futmesa_php

docker build -t futmesa-backend .

docker stack deploy -c docker-compose.yml futmesa
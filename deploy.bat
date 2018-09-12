docker service rm test_php

docker build -t futmesa-backend .

docker stack deploy -c docker-compose.yml test
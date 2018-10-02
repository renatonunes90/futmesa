# futmesa
Sistema para gerenciamento de campeonatos de futebol de mesa.

//compila a imagem do docker da aplicação php (a partir da raiz do projeto)
>> docker build -t futmesa-backend . 
OU
>> docker-compose build

// sobe a aplicação na porta 8081 com o firebird 3
>> docker stack deploy -c docker-compose.yml test (não funciona)
OU
>> docker-compose up




FROM php:7.2-apache

RUN pear install DB

RUN apt-get update 
RUN apt install -y firebird-dev
RUN docker-php-ext-install interbase

COPY Front-End/GWT/war/futmesa/ /var/www/html/futmesa/futmesa
COPY Front-End/GWT/war/index.css /var/www/html/futmesa
COPY Front-End/GWT/war/index.html /var/www/html/futmesa
COPY Back-End/php/ /var/www/html/futmesa/server

EXPOSE 80
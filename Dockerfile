FROM php:7.2-apache

RUN pear install DB

RUN apt-get update 
RUN apt install -y firebird-dev
RUN docker-php-ext-install interbase

COPY Back-End/php/ /var/www/html/

EXPOSE 80
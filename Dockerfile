FROM php:7.2-apache

RUN pear install DB

COPY Back-End/php/ /var/www/html/

EXPOSE 80
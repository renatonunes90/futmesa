FROM php:7.2-apache
#FROM alpine:3.7

#RUN apk --no-cache add \
#		php7 \
#        php7-pear \
#        php7-simplexml

#COPY Config/php.ini /usr/local/etc/php/
COPY Back-End/php/ /var/www/html/
EXPOSE 80
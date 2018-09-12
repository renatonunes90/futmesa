FROM php:7.2-apache

#RUN apt-get update \
#  && apt-get install -y --no-install-recommends \
#    php-pear \
#  && rm -rf /var/lib/apt/lists/*
RUN pear install DB

ADD https://phar.phpunit.de/phpunit-7.3.5.phar /usr/local/bin/
RUN chmod +x /usr/local/bin/phpunit-7.3.5.phar \phpunit


#COPY Config/php.ini /usr/local/etc/php/
COPY Back-End/php/ /var/www/html/
EXPOSE 80
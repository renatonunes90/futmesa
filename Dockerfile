FROM php:7.2-apache

#RUN apt-get update \
#  && apt-get install -y --no-install-recommends \
#    php-pear \
#  && rm -rf /var/lib/apt/lists/*
RUN pear install DB
#RUN pear install PHPUnit

# Install PHPUnit
#RUN cd /tmp && curl https://phar.phpunit.de/phpunit-7.3.5.phar > phpunit-7.3.5.phar && \
#    chmod +x phpunit-7.3.5.phar && \
#    mv /tmp/phpunit-7.3.5.phar /usr/local/bin/PHPUnit
	
ADD https://phar.phpunit.de/phpunit-7.3.5.phar /usr/local/etc/
RUN chmod +x /usr/local/etc/phpunit-7.3.5.phar
RUN mv /usr/local/etc/phpunit-7.3.5.phar /usr/local/bin/phpunit

#COPY Config/php.ini /usr/local/etc/php/
COPY Back-End/php/ /var/www/html/

#RUN cd /var/www/html/dblib && ./UnitTest/AllTests.bat

EXPOSE 80
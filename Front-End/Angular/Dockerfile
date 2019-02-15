# Imagem base
FROM node:8-alpine as futmesa-frontend

# Pasta de trabalho
WORKDIR /usr/src/app

# Copia os arquivos para dentro do container
COPY package.json ./

# Install angular CLI
RUN npm install
RUN npm install -g @angular/cli

# Bundle app source
COPY . .

# Compila
RUN ng build
#RUN ng build --configuration=dsv
#RUN ng build --env=dsv

# Stage 2 - Publish nginx
FROM nginx:1.13.12-alpine

COPY --from=futmesa /usr/src/app/dist /usr/share/nginx/html

COPY ./nginx.conf /etc/nginx/conf.d/default.conf
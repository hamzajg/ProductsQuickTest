# Set the base image
FROM node:lts-alpine3.9 AS dependencies
# Define working directory
WORKDIR /var/www/app

COPY src/webapp/package.json ./tmp/
COPY src/webapp/src/app/package.json ./tmp/src/app/
COPY src/webapp/src/ui/package.json ./tmp/src/ui/

RUN cd ./tmp && yarn install --pure-lockfile --non-interactive

FROM dependencies  AS link
# Define working directory
WORKDIR /var/www/app
COPY src/webapp/src/app/src ./tmp/src/app/src/
RUN ln -s /var/www/app/tmp/node_modules .

FROM link AS build
WORKDIR /var/www/app
COPY src/webapp/package.json .
COPY src/webapp/src/app ./src/app
COPY src/webapp/src/ui ./src/ui
RUN yarn workspaces run build

# Expose port
EXPOSE 3000
ENTRYPOINT ["yarn", "workspace", "@webapp/ui", "start"]
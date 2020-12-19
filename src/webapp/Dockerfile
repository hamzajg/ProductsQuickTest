# Set the base image
FROM node:lts-alpine3.9 AS base
# Define working directory
WORKDIR /var/www/app/tmp

COPY src/webapp/package.json .
# COPY yarn.lock .
COPY src/webapp/src/app/package.json ./src/app/package.json
COPY src/webapp/src/ui/package.json ./src/ui/package.json

FROM base AS dependencies
# install node packages
# RUN yarn install --pure-lockfile --non-interactive --production
# # copy production node_modules aside
# RUN cp -R node_modules prod_node_modules
# # install ALL node_modules, including 'devDependencies'
RUN yarn install --pure-lockfile --non-interactive

FROM dependencies AS build
WORKDIR /var/www/app/tmp

COPY src/webapp/src/app/ ./src/app/
COPY src/webapp/src/ui/ ./src/ui/

RUN yarn workspaces run build

FROM node:lts-alpine3.9
WORKDIR /var/www/app
COPY src/webapp/package.json .
COPY --from=dependencies /var/www/app/tmp/node_modules ./node_modules

COPY --from=build /var/www/app/tmp/src/ui/ /var/www/app/src/ui/

# Expose port
EXPOSE 3000
ENTRYPOINT ["yarn", "workspace", "@webapp/ui", "start"]
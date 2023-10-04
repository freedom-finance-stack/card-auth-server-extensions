FROM public.ecr.aws/docker/library/node:18-alpine3.14 AS development
ENV NODE_ENV development

WORKDIR /app

COPY package.json .
RUN npm install
RUN mkdir node_modules/.cache && chmod -R 777 node_modules/.cache

COPY . .

EXPOSE 3000

CMD [ "npm", "start" ]
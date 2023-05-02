FROM node:20-alpine

WORKDIR /app

ENV PATH /app/node_modules/.bin:$PATH

COPY . /app

RUN npm install
RUN npm install @vue/cli@3.7.0 -g

EXPOSE 5173

CMD ["npm", "run", "dev"]
FROM node:lts-alpine3.14
# Adding build tools to make yarn install work on Apple silicon / arm64 machines
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
EXPOSE 3000
CMD ["npm", "start"]
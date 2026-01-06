# Use Node.js image to build the Angular app
FROM node:16 as build

# Set the working directory
WORKDIR /app

# Copy package.json and package-lock.json
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the rest of the application code
COPY . .

# Build the Angular application
RUN npm run build --prod

# Use NGINX to serve the application
FROM nginx:alpine

# Copy the built files from the previous stage
COPY --from=build /app/dist/coin-bomb-frontend /usr/share/nginx/html

# Copy custom NGINX configuration
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Expose the application port
EXPOSE 80

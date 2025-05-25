# Etapa de construcción
FROM node:18-alpine as builder

WORKDIR /app

# Copiar archivos de dependencias
COPY package.json package-lock.json ./
RUN npm install

# Copiar el resto de archivos
COPY . .

# Instalar jwt-decode explícitamente
RUN npm install jwt-decode

# Construir la aplicación
RUN npm run build

# Etapa de producción
FROM nginx:alpine

# Copiar la configuración de nginx
COPY nginx/default.conf /etc/nginx/conf.d/default.conf

# Copiar los archivos construidos desde la etapa de construcción
COPY --from=builder /app/dist /usr/share/nginx/html

# Exponer el puerto 80
EXPOSE 80

# Iniciar nginx
CMD ["nginx", "-g", "daemon off;"]

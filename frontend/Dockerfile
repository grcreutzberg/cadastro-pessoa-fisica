# Etapa 1: Build da aplicação
FROM node:18-alpine AS build

# Definir o diretório de trabalho dentro do container
WORKDIR /app

# Copiar os arquivos package.json e package-lock.json
COPY package.json package-lock.json ./

# Instalar as dependências do projeto
RUN npm install

# Copiar o restante do código-fonte
COPY . .

# Gerar o build otimizado da aplicação
RUN npm run build

# Etapa 2: Servir com nginx
FROM nginx:alpine

# Copiar o build gerado para a pasta do nginx
COPY --from=build /app/dist /usr/share/nginx/html

# Copiar o arquivo de configuração do nginx
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Expor a porta padrão do nginx
EXPOSE 80

# Comando para iniciar o nginx
CMD ["nginx", "-g", "daemon off;"]

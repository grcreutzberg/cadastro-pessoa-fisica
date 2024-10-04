# Cadastro de Pessoa Física

## Opção 1 - Rodar usando docker

`docker-compose up`
<br/>

Docker frontend image: [DockerHub](https://hub.docker.com/layers/grcreutzberg/cadastro-pessoa-fisica-frontend/latest/images/sha256:373473ee2ec6748578797f429267c224f28d8106020c130602e3a3f8a638851a?uuid=369098a3-33d9-42f0-b2b1-d07d59915844%0A)
<br/>

Docker backend image: [DockerHub](https://hub.docker.com/layers/grcreutzberg/cadastro-pessoa-fisica-backend/latest/images/sha256:e4cc12b3b8ca919d92514173d53582d96c19d42b0015c4a40511e4e27e25b5b2?uuid=369098a3-33d9-42f0-b2b1-d07d59915844%0A)
<br/>

Frontend: [http://localhost:3000](http://localhost:3000)<br/>
Backend: [http://localhost:8080/](http://localhost:8080/)<br/>

## Opção 2 - Rodar backend usando Docker e frontend localmente

`docker-compose up`<br/>

`cd frontend`<br/>

`npm install`<br/>

`npm run dev`<br/>

Frontend: [http://localhost:3000](http://localhost:3000)<br/>
Backend: [http://localhost:8080/](http://localhost:8080/)<br/>

## Opção 3 - Alternativa caso algum problema ocorra

Abra o backend na IDE<br/>
Execute `gradle build`<br/>
Execute `gradle bootJar`<br/>

Para subir o Jar buildado e banco no docker:<br/>
`docker-compose up`<br/>

Execute frontend localmente:<br/>
`cd frontend`<br/>
`npm install`<br/>
`npm run dev`<br/>

Frontend: [http://localhost:3000](http://localhost:3000)<br/>
Backend: [http://localhost:8080/](http://localhost:8080/)<br/>

# Projeto

![Listagem de pessoas](image.png)<br/>
![Tela de cadastro](image-1.png)<br/>
![Visualizar registro](image-2.png)<br/>
![Consistência CPF já cadastrado](image-3.png)<br/>
![Feedback de cadastro com sucesso](image-4.png)<br/>
![Relatório CSV](image-5.png)<br/>

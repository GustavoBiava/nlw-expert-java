# NLW-EXPERT: Trilha de Java

O projeto foi construído ao decorrer das aulas do evento Next Level Week Expert da plataforma Rocketseat. O principal objetivo do projeto foi construir uma aplicação Spring Boot com o intuito de aprofundar-se na linguagem Java, além também de trabalhar com as ferramentas do ecossistema Spring. A aplicação consiste em um sistema que possibilita a obtenção de certificações em determinadas tecnologias para estudantes através de perguntas e respostas, as quais são correspondentes a tecnolgia escolhida pelo estudante. Após o envio das respostas, é criada uma certificação vinculada ao id do estudante possuindo a quantidade de acertos além de outras informações. Após obter uma certificação de determinada tecnologia o estudante não pode mais responder as perguntas da mesma, apenas de outras tecnologias.
  
## Modelo conceitual:
![Modelo conceitual](https://raw.githubusercontent.com/GustavoBiava/nlw-expert-java/main/assets/Modelo%20Conceitual.png)

## Para rodar o projeto você precisa:
- Java
- Apache Maven
- REST Client (Postman, Insomnia e etc) para efetuar as requisições HTTP

### 1 - Inicie um repositório git e dê um git clone no repositório:
```
git init
git clone https://github.com/GustavoBiava/nlw-expert-java.git
```
### 2 - Dentro do diretório do projeto utilizando o CMD ou Git Bash digite o código abaixo para rodar a aplicação Spring Boot via Maven
```
mvn spring-boot:run
```
### 3 - Pronto. Agora você tem acesso aos end-points da aplicação!

- #### GET - Listar todas as perguntas e alternativas de uma determinada tecnologia:
```
http://localhost:8080/nlw-java/questions/technology/{NOME_DA_TECNOLOGIA}
```
- #### GET - Lista os 10 alunos com a maior quantidades de acertos de todas as certificações:
```
http://localhost:8080/nlw-java/ranking/top10
```
- #### POST - Verificar se um estudante já fez alguma prova de determinada tecnologia passando email e tecnologia via body:
```
Requisição:
http://localhost:8080/nlw-java/students/verifyIfHasCertification

Body:
{
    "email": "exemplo@email.com",
    "technology":"JAVA"
}
```
- #### POST - Enviar respostas das perguntas para atribuir ao estudante uma certificação de determinada tecnologia:
```
Body:
{
    "email" : "exemplo@email.com",
    "technology" : "JAVA",
    "questionsAnswers" : [
        {
            "questionId" : "{ID_DA_QUESTÃO}",
            "alternativeId" : "{ID_DA_ALTERNATIVA}"
        },
        {
            "questionId" : "{ID_DA_QUESTÃO}",
            "alternativeId" : "{ID_DA_ALTERNATIVA}"
        },
        {
            "questionId" : "{ID_DA_QUESTÃO}",
            "alternativeId" : "{ID_DA_ALTERNATIVA}"
        }
    ]
}
```
### Observações!
- Caso deseje mudar a porta em que a aplicação roda, altere a mesma utilizando a propriedade <b>server.port={PORTA}</b> em <b>application.properties</b>;
- Caso deseje encerrar a aplicação, aperte Ctrl + C e confirme a parada no terminal;
- Mude as configurações do banco de dados dentro do arquivo <b>application.properties</b>, presente no diretório: <b>nlw-expert-java/src/main/resources/application.properties</b>.
  
#### Arquivo application.properties
```
#Postgre
spring.datasource.url=jdbc:postgresql://localhost:{SUA_PORTA}/{NOME_DO_SEU_BANCO}
spring.datasource.username={SEU_USUARIO}
spring.datasource.password={SUA_SENHA}
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

#Context Path
server.servlet.context-path=/nlw-java
```
## Agradecimentos
Agradeço primeiramente a Prof. Daniele Leão por sua dedicação durante as aulas, e também a toda equipe da Rocketseat por proporciOnar esse evento incrível. E por fim, agradeço também você leitor por sua atenção. Obrigado!

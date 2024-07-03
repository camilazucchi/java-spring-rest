## Evolutionary Database Design
O termo "evolutionary database design" refere-se a uma abordagem para o desenvolvimento e manutenção
de bancos de dados que suporta a <b>evolução contínua</b> das necessidades de negócios e
requisitos de software. Essa abordagem contrasta com a prática tradicional de tentar antecipar
todas as necessidades futuras e projetar um esquema de banco de dados fixo e abrangente
desde o início.
<br>https://martinfowler.com/articles/evodb.html
## Flyway
https://documentation.red-gate.com/flyway
<br>Ocorreu um problema ao usar Flyway com a versão do PostgreSQL 16.3, por isso, tive que atualizar
a versão do Flyway para a mais recente, que suporta a versão do PostgreSQL 16.3, que é a
10.5.2.
<br>https://mvnrepository.com/artifact/org.flywaydb/flyway-core/10.15.2
<br>Além disso, tive que adicionar outra dependência no pom.xml, flyway-database-postgresql.
<br>Links úteis que me ajudaram na resolução do problema:
https://stackoverflow.com/questions/78539678/using-postgres-16-with-spring-boot-3-3-0
https://github.com/flyway/flyway/issues/3807
### Lembretes
- O caminho para a pasta contendo migrations deve ser src - resources - db - migration.
- Seguir o padrão de nomenclatura das migrations V#__Nome_Da_Migration.sql.
## Content Negotiation
A Content Negotiation (Negociação de Conteúdo) é uma técnica utilizada em sistemas baseados
em HTTP (como API's RESTful) para determinar a melhor representação de um recurso a ser entregue
ao cliente, com base em critérios como as preferências do cliente e as capacidades do servidor.
No Spring Framework, a negociação de conteúdo é amplamente utilizada para servir diferentes formatos
de resposta, como JSON, XML, HTML, entre outros.
### Como funciona a Content Negotiation?
No Spring MVC, a negociação de conteúdo é gerenciada por várias classes e interfaces, como
"ContentNegotiationManager", "ContentNegotiationConfigurer" e "ContentNegotiationStrategy".
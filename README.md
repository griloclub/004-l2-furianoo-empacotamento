Empacotamento de Pedidos - API Spring Boot
Visão Geral
Este projeto consiste em uma API RESTful para empacotamento inteligente de pedidos, que organiza produtos em caixas com base nas dimensões e volumes disponíveis. A solução visa otimizar a logística e reduzir custos de transporte ao automatizar o processo de embalagem, respeitando as restrições físicas de cada item e das caixas.

O sistema é desenvolvido em Java utilizando Spring Boot, seguindo princípios de arquitetura limpa e boas práticas de desenvolvimento, garantindo escalabilidade, manutenibilidade e fácil integração.

Tecnologias Utilizadas
Java 17+ — Linguagem principal do projeto
Spring Boot 2.x/3.x — Framework para desenvolvimento de API REST
Maven — Gerenciamento de dependências e build
Lombok — Redução de código padrão (getters, setters, construtores)
Swagger/OpenAPI — Documentação interativa da API
JUnit 5 — Testes unitários e de integração
Logback/SLF4J — Registro
MapStruct (opcional) — Mapeamento DTO para entidades e vice-versa
Funcionalidades Principais
Recebimento de pedidos com lista de produtos e suas dimensões
Validação dos dados de entrada com respostas claras para erros
Algoritmo para distribuir produtos em caixas disponíveis, respeitando dimensões e volume máximo
Geração de resposta com detalhamento das caixas e produtos embalados
Registro de observações para itens que não podem ser embalados
Documentação Swagger para facilitar testes e integração
Endpoints da API
PUBLICAR/api/v1/empacotamento/pedidos
Descrição: Recebe uma lista de pedidos para empacotamento e devoluções a distribuição dos produtos nas caixas.
Corpo da solicitação: JSON contendo pedidos, produtos e dimensões.
Corpo da resposta: JSON detalhando caixas usadas e produtos embalados.
Códigos HTTP:
200 OK— Empacotamento realizado com sucesso
400 Bad Request— Dados inválidos ou incompletos
500 Internal Server Error— Erro interno no servidor
Exemplo de solicitação:
{
"pedidos": [
{
"id": "pedido-123",
"produtos": [
{
"id": "prod-001",
"nome": "Produto A",
"dimensoes": {
"altura": 10,
"largura": 20,
"comprimento": 30
},
"peso": 2.5
},
{
"id": "prod-002",
"nome": "Produto B",
"dimensoes": {
"altura": 5,
"largura": 10,
"comprimento": 15
},
"peso": 1.0
}
]
}
]
}
Instalação e Execução
Pré-requisitos
Java 17+ instalado e configurado no PATH

Maven 3.6+ (ou utilize o wrapper Maven que acompanha o projeto)

Passos para rodar localmente
Clone o repositório:

bash
Copiar
Editar
git clone https://github.com/seuusuario/empacotamento.git
cd empacotamento
Compile e execute a aplicação:

bash
Copiar
Editar
./mvnw clean spring-boot:run
Acesse a API na URL padrão:

bash
Copiar
Editar
http://localhost:8080/api/v1/
Para documentação interativa da API via Swagger UI, acesse:

bash
Copiar
Editar
http://localhost:8080/swagger-ui.html
Testes
Os testes unitários e de integração estão localizados em src/test/java

Execute os testes com o comando:

bash
Copiar
Editar
./mvnw test
Roadmap e Melhorias Futuras
Suporte a empacotamento considerando peso máximo por caixa

Algoritmos avançados de empacotamento 3D para melhor aproveitamento do espaço

Persistência dos pedidos e embalagens em banco de dados relacional (PostgreSQL/MySQL)

Implementação de autenticação e autorização (JWT/OAuth2) para segurança da API

Dockerização da aplicação para facilitar deploy e escalabilidade

Monitoramento e métricas via Spring Actuator com integração Prometheus/Grafana

Suporte a múltiplos perfis de ambiente via Spring Profiles

Documentação ampliada e exemplos via Postman

Como Contribuir
Faça um fork do repositório

Crie uma branch para sua feature (feature/nome-da-feature)

Faça commits claros e objetivos

Envie um Pull Request detalhando as mudanças realizadas

Sinta-se à vontade para abrir issues para bugs ou sugestões.

Contato
Andre Azevedo de Oliveira
Email: furianomal22@gmail.com
LinkedIn: linkedin.com/in/andré-azevedo-de-oliveira-09032424a
GitHub: github.com/Furianoo (Portfólio)

Licença
Este projeto está licenciado sob a licença MIT. Veja o arquivo LICENSE para detalhes.

Obrigado por usar o Empacotamento de Pedidos!
Desenvolvido para tornar a logística mais eficiente.

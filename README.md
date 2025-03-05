# Sistema de Alocação de Salas - IFBA

## Descrição do Projeto
O Sistema de Alocação de Salas do Instituto Federal da Bahia (IFBA) é uma aplicação web desenvolvida para facilitar o gerenciamento da alocação de salas para disciplinas e aulas, garantindo a organização eficiente dos recursos disponíveis. O sistema permite o cadastro, edição e exclusão de salas, disciplinas e aulas, bem como a prevenção de conflitos de horários.

## Funcionalidades

### Gerenciamento de Salas
- 📌 Cadastrar nova sala  
- 📌 Listar salas cadastradas  
- 📌 Editar e excluir salas  

### Gerenciamento de Disciplinas
- 📌 Cadastrar nova disciplina  
- 📌 Listar disciplinas cadastradas  
- 📌 Editar e excluir disciplinas  

### Gerenciamento de Aulas
- 📌 Alocar uma disciplina a uma sala em um determinado horário  
- 📌 Listar aulas agendadas  
- 📌 Impedir conflitos de horários (uma sala não pode ser ocupada por mais de uma disciplina ao mesmo tempo)  
- 📌 Editar e excluir alocações de aulas  

### Interface de Usuário
- 📌 Dashboard com visão geral das alocações  
- 📌 Tabela de horários semanal  
- 📌 Pesquisa e filtros por sala, disciplina e professor  

## 🛠 Tecnologias Utilizadas
- **Backend**: Spring Boot (Java) com JPA/Hibernate para persistência  
- **Banco de Dados**: PostgreSQL  
- **Frontend**: React.js com Axios para integração com a API  
- **Autenticação**: JWT para controle de acesso dos usuários  
- **Estilização**: CSS, Material-UI ou Bootstrap  


# Review Service API

Um microsserviço de avaliação e moderação construído em **Java com Spring Boot**, focado na receção, processamento e moderação automática de avaliações de produtos (Reviews). O projeto utiliza **Apache Kafka** para comunicação assíncrona orientada a eventos e implementa testes unitários robustos seguindo as melhores práticas de mercado.

## 🛠 Tecnologias Utilizadas

*   **Linguagem:** Java 17+
*   **Framework:** Spring Boot
*   **Mensageria:** Apache Kafka (Producer / Consumer)
*   **Banco de Dados:** PostgreSQL (via Spring Data JPA)
*   **Mapeamento de Objetos:** Mapper (Conversão de DTOs e Entidades)
*   **Testes:** JUnit 5, Mockito, AssertJ

## ⚙️ Arquitetura e Padrões de Projeto

O projeto segue uma arquitetura em camadas limpa, garantindo a separação de responsabilidades:
*   **Isolamento de Domínio:** Utilização rigorosa de `RequestDTO` e `ResponseDTO` para comunicação externa, protegendo as entidades do banco de dados (JPA).
*   **Moderação em Memória:** Serviço de moderação (`ModerationService`) otimizado com Expressões Regulares (Regex) para validação rápida de conteúdo impróprio sem onerar o banco de dados.
*   **Event-Driven:** Integração com Kafka para consumir pedidos de avaliação e publicar eventos de avaliações processadas.

## ✨ Funcionalidades Principais

*   **Processamento de Avaliações:** Recebe avaliações contendo produto, cliente, nota e comentário.
*   **Atribuição de Status:** Avaliações recém-criadas recebem automaticamente o status `PENDING` (via Enums).
*   **Filtro de Moderação:** Sistema inteligente que barra:
    *   Palavrões e termos ofensivos.
    *   Links externos (Spam).
    *   Tentativas de fraude (promessas de "ganho rápido", "renda extra", etc).
*   **Notificação de Eventos:** Publicação de mensagens no Kafka (`ReviewProducer`) informando outros microsserviços sobre o status da avaliação.

## 🧪 Qualidade e Testes Unitários

A qualidade do código é garantida através de uma suíte de testes unitários desenvolvida sob o padrão **AAA (Arrange, Act, Assert)**.
*   **Isolamento Total:** Testes de serviço (`ReviewServiceTest`) não sobem o contexto do Spring, utilizando `@InjectMocks` e `@Mock` para simular Repositórios, Mappers e Producers.
*   **Previsibilidade:** Uso de dados fixos (como `LocalDateTime.of()` e UUIDs fixos) para garantir que os testes sejam 100% determinísticos.
*   **Validações Fluidas:** Validações de saída estruturadas com AssertJ (`assertThat`) cobrindo o "Caminho Feliz" e cenários de erro.

## 🚀 Como Executar o Projeto

**Pré-requisitos:**
*   JDK instalado (versão 17 ou superior).
*   Maven.
*   Apache Kafka e Zookeeper (ou Docker com as imagens do Kafka).
*   Banco de dados relacional configurado.

**Passos:**
1. Clone o repositório:
   ```bash
   git clone [https://github.com/seu-usuario/review_system.git](https://github.com/seu-usuario/review_system.git)

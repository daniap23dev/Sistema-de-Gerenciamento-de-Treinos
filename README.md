# Sistema de Gerenciamento de Treinos

![Java](https://img.shields.io/badge/Java-8%2B-orange?logo=openjdk)
![Status](https://img.shields.io/badge/status-em%20desenvolvimento-yellow)
![License](https://img.shields.io/badge/license-MIT-blue)

Um aplicativo de terminal em Java que permite cadastrar alunos, organizar suas divisões musculares e registrar detalhadamente o acompanhamento de séries, cargas e rotinas aeróbicas ao longo da semana — com todos os dados salvos automaticamente entre execuções.

---

## Sumário

- [Sobre o projeto](#sobre-o-projeto)
- [Funcionalidades](#funcionalidades)
- [Conceitos de Java aplicados](#conceitos-de-java-aplicados)
- [Estrutura do projeto](#estrutura-do-projeto)
- [Como executar](#como-executar)
- [Menu e fluxo de uso](#menu-e-fluxo-de-uso)
- [Persistência de dados](#persistência-de-dados)
- [Exportação para CSV](#exportação-para-csv)
- [Diagrama de classes](#diagrama-de-classes)
- [Autor](#autor)
- [Licença](#licença)

---

## Sobre o projeto

O **Sistema de Gerenciamento de Treinos** é uma aplicação de linha de comando desenvolvida em Java que simula um sistema de acompanhamento de academia. O usuário cadastra seu perfil (peso, altura, idade, objetivo), monta blocos de treino organizados por divisão muscular (ex: "Peito, Tríceps e Ombro"), registra os exercícios de força (séries, repetições, carga) ou cardio (séries, tempo) que compõem cada bloco, e vincula cada bloco a um dia da semana — montando assim um cronograma semanal completo.

O projeto foi desenvolvido como exercício acadêmico de Programação Orientada a Objetos, aplicando conceitos como herança, polimorfismo, composição, encapsulamento, tratamento de exceções personalizadas e persistência de dados via serialização.

## Funcionalidades

- **Cadastro de usuário** — nome, peso, altura, idade e objetivo.
- **Criação de blocos de treino (divisões musculares)** — quantos o usuário quiser.
- **Registro de exercícios**, com dois tipos possíveis:
  - **Força**: nome, séries, repetições e carga (kg).
  - **Cardio**: nome, séries e tempo (minutos).
- **Vínculo de treino a dia da semana**, com trava automática que impede o mesmo bloco de treino em dois dias adjacentes.
- **Exibição do cronograma semanal completo** no terminal, dia a dia, com os detalhes de cada exercício.
- **Exportação de qualquer treino para arquivo `.csv`**, incluindo os dados do usuário no topo do arquivo.
- **Persistência automática entre execuções** — o programa salva o estado (usuário + treinos) em disco a cada alteração e recarrega tudo sozinho na próxima vez que é aberto, sem precisar recadastrar nada.

## Conceitos de Java aplicados

| Conceito | Onde aparece |
|---|---|
| **Herança** | `Exercicio` (classe abstrata) → `ExercicioForca` e `ExercicioCardio` |
| **Polimorfismo** | Método `exibirDetalhes()`, sobrescrito de forma diferente em cada subclasse de exercício |
| **Composição** | `Usuario` possui `Treino`, que possui `Exercicio` |
| **Encapsulamento** | Atributos `private`/`protected` com getters controlados |
| **Exceções personalizadas** | `CargaInvalidaException`, `TempoInvalidoException`, `TreinoRepetidoException` |
| **Collections** | `ArrayList` (exercícios/treinos) e `HashMap` (calendário semanal) |
| **Enum** | `DiaSemana`, padronizando os dias da semana |
| **Serialização** | `Serializable` + `ObjectOutputStream`/`ObjectInputStream` para persistir o estado da aplicação em disco |

## Estrutura do projeto

```
Sistema-de-Gerenciamento-de-Treinos/
└── Projeto/
    ├── Main.java                    # Menu do terminal e orquestração geral
    ├── Usuario.java                 # Perfil do aluno + calendário semanal
    ├── DiaSemana.java               # Enum com os dias da semana
    ├── Treino.java                  # Bloco/divisão de treino
    ├── Exercicio.java               # Classe abstrata base dos exercícios
    ├── ExercicioForca.java          # Exercício de força (série, reps, carga)
    ├── ExercicioCardio.java         # Exercício aeróbico (série, tempo)
    ├── Dados.java                   # Wrapper serializável (usuário + treinos)
    ├── CargaInvalidaException.java
    ├── TempoInvalidoException.java
    └── TreinoRepetidoException.java
```

## Como executar

Pré-requisito: JDK 8 ou superior instalado.

```bash
# Clone o repositório
git clone https://github.com/<seu-usuario>/Sistema-de-Gerenciamento-de-Treinos.git
cd Sistema-de-Gerenciamento-de-Treinos

# Compile
javac -encoding UTF-8 -d out Projeto/*.java

# Execute
java -cp out Projeto.Main
```

Na primeira execução, o programa começa vazio. Nas próximas, ele carrega automaticamente o que foi salvo no arquivo `dados.ser`.

## Menu e fluxo de uso

```
SISTEMA DE GERENCIAMENTO DE TREINOS:
1 - Cadastrar usuário
2 - Criar treino
3 - Adicionar exercício a um treino
4 - Vincular treino a um dia da semana
5 - Exibir cronograma
6 - Exportar o treino para arquivo CSV
0 - Sair
```

Fluxo típico de uso:

1. **Cadastre o usuário** (opção 1).
2. **Crie os blocos de treino** que quiser (opção 2) — ex: PEITO, COSTAS, PERNA...
3. **Adicione exercícios** a cada bloco (opção 3), escolhendo entre Força ou Cardio.
4. **Vincule cada bloco a um dia da semana** (opção 4) — o sistema bloqueia repetir o mesmo treino em dias adjacentes.
5. **Veja o cronograma completo** a qualquer momento (opção 5).
6. **Exporte para CSV** quando quiser (opção 6).
7. **Saia** (opção 0) — os dados já foram salvos automaticamente a cada passo, mas a saída garante o salvamento final.

## Persistência de dados

Todas as classes de domínio (`Usuario`, `Treino`, `Exercicio` e suas subclasses, `Dados`) implementam `Serializable`. A cada ação que altera o estado da aplicação, o `Main` grava o objeto `Dados` (que agrupa o usuário e a lista de treinos) no arquivo `dados.ser`, usando `ObjectOutputStream`. Ao iniciar, o programa tenta ler esse arquivo com `ObjectInputStream` e reconstrói o estado exatamente como estava — se o arquivo não existir (primeira execução), começa vazio normalmente.

## Exportação para CSV

A opção 6 gera um arquivo `.csv` com os dados do usuário e o detalhamento do treino escolhido:

```csv
Nome,Peso(kg),Altura(m),Idade,Objetivo
Daniel,78.5,1.78,22,Hipertrofia

Divisao,Exercicio,Series,Tipo,Detalhe
PEITO,Supino Reto,4,Forca,40.0kg x 12reps
```

## Diagrama de classes

O diagrama de classes (UML) do projeto está disponível em [`docs/diagrama.puml`](docs/diagrama.puml) — pode ser renderizado colando o conteúdo em [plantuml.com/plantuml](https://www.plantuml.com/plantuml/uml/) ou usando a extensão **PlantUML** no VS Code.

## Autor

- Daniel Aires Pereira

## Licença

Este projeto está sob a licença especificada no arquivo [`LICENSE`](LICENSE).

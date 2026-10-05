# DuoCode — Trabalho 2

App Android de quiz para programadores, no estilo Duolingo, feito em Kotlin com Jetpack Compose.

## Integrantes

- André Caires
- Gustavo Cáceres
- Brenno Soares

## Como rodar

1. Requisitos: Android Studio (versão recente) e um emulador ou celular com Android 7.0 (API 24) ou superior.
2. Clone o repositório:
   ```
   git clone https://github.com/acairesm/Duo_Code_Parte_Dois.git
   ```
3. Abra a pasta `Duo_Code_Parte_Dois` no Android Studio e aguarde o Gradle Sync terminar (ele baixa sozinho o Navigation Compose e os ícones estendidos do Material usados pelo app).
4. Escolha um emulador (ou conecte um celular com depuração USB ativada) e clique em **Run ▶** (ou `Shift+F10`).

## Telas

O app tem 8 telas, todas navegáveis pelo `NavHost` em `AppNavigation.kt`:

- **Trilha** (`trilha`) — mapa de lições e progresso da unidade atual
- **Linguagens** (`linguagens`) — lista de linguagens, com adicionar e remover
- **Questões** (`questoes`) — lista de questões por linguagem, com adicionar e remover
- **Perfil** (`perfil`) — perfil do usuário, badges e ranking da liga
- **Pergunta** (`pergunta`) — quiz de múltipla escolha
- **Resultado** (`resultado/{acertos}/{total}`) — resumo da lição concluída
- **Detalhe da linguagem** (`detalhe_linguagem/{id}`) — progresso e questões de uma linguagem específica
- **Detalhe da questão** (`detalhe_questao/{id}`) — enunciado, resposta e linguagem de uma questão específica

A navegação principal (Trilha, Linguagens, Questões, Perfil) fica num `BottomNavigation`.

## Modelo de dados

- `Linguagem` — `id`, `nome`, `sigla`, `descricao`
- `Questao` — `id`, `enunciado`, `resposta`, `linguagemId`, `dificuldade`, `resolvida`
- `Dificuldade` (enum) — `FACIL`, `MEDIO`, `DIFICIL`

Nada é persistido (sem Room/DataStore): as listas vivem em memória e se perdem ao fechar o app.

## Tecnologias

- Kotlin 2.2.10
- Jetpack Compose (BOM 2026.02.01) com Material 3
- Navigation Compose 2.10.0
- minSdk 24 · targetSdk/compileSdk 37

## Documentação

O processo e as decisões do trio, com prints do código e dos protótipos, estão em
[`DuoCode_Documentacao_T2.pdf`](./DuoCode_Documentacao_T2.pdf), nesta mesma raiz do repositório.

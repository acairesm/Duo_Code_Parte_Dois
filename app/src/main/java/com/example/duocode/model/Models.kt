package com.example.duocode.model

data class Linguagem(val id: Int, val nome: String, val sigla: String)

data class Questao(val id: Int, val enunciado: String, val resposta: String, val linguagemId: Int)

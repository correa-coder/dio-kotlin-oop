enum class Nivel { BASICO, INTERMEDIARIO, AVANCADO }

enum class TipoConteudo { CURSO, DESAFIO_CODIGO, DESAFIO_PROJETO }

data class Usuario(val id: Int, val nome: String)

data class ConteudoEducacional(val nome: String, val tipo: TipoConteudo, val duracaoEmMinutos: Int = 60) {
    
    fun duracaoEmHoras(): Int {
        /* retorna a duração do conteúdo em horas */
        return duracaoEmMinutos / 60
    }
}

data class Formacao(val nomeFormacao: String, val nivel: Nivel, var conteudos: List<ConteudoEducacional>) {

    val inscritos = mutableListOf<Usuario>()
    
    fun matricular(usuario: Usuario) {
        inscritos.add(usuario)
        println("${usuario.nome} efetuou sua inscrição em ${nomeFormacao}!")
    }
    
    fun cancelarMatricula(usuario: Usuario) {
        /* cancela a matrícula de um aluno */
        inscritos.remove(usuario)
        println("${usuario.nome} cancelou sua inscrição em ${nomeFormacao}!")
    }
    
    fun duracaoTotalEmHoras(): String {
        /* retorna a soma das durações de todos os $conteudos em horas */
        var somaDuracoes: Int = 0
        for (conteudo in conteudos) {
            somaDuracoes += conteudo.duracaoEmHoras()
        }
        return "${somaDuracoes}h"
    }
    
    fun mostrarInscritos() {
        /* mostra o nome dos alunos matriculados */
        if (inscritos.size < 1) {
            println("Nenhum inscrito")
        } else {
            println("\nLista de inscritos em $nomeFormacao (${inscritos.size}):")
            for (inscrito in inscritos) {
                println("- ${inscrito.nome}")
            }
        }
    }
}

fun main() {
    val user1: Usuario = Usuario(1, "Rodrigo")
    val user2: Usuario = Usuario(2, "Fernanda")
    val user3: Usuario = Usuario(3, "Pedro")
    val user4: Usuario = Usuario(4, "Maria")
    
    val conteudosKotlin: List<ConteudoEducacional> = listOf(
    	ConteudoEducacional("Conhecendo Kotlin e Sua Documentação Oficial", TipoConteudo.CURSO),
        ConteudoEducacional("Introdução Prática à Linguagem de Programação Kotlin", TipoConteudo.CURSO, 120),
        ConteudoEducacional("Estruturas de Controle de Fluxo e Coleções em Kotlin", TipoConteudo.CURSO, 120),
        ConteudoEducacional("O Poder das Funções em Kotlin", TipoConteudo.CURSO),
        ConteudoEducacional("Abstraindo Formações da DIO", TipoConteudo.DESAFIO_PROJETO, 120),
        ConteudoEducacional("Avançando Tecnicamente Com a Expressão When", TipoConteudo.DESAFIO_CODIGO)
    )
    
    val formacaoKotlin: Formacao = Formacao("Kotlin Experience", Nivel.BASICO, conteudosKotlin)

    println(formacaoKotlin.duracaoTotalEmHoras())
    
    formacaoKotlin.mostrarInscritos()
    
    formacaoKotlin.matricular(user1)
    formacaoKotlin.matricular(user2)
    formacaoKotlin.matricular(user3)
    formacaoKotlin.matricular(user4)
    
    formacaoKotlin.cancelarMatricula(user3)
    
    formacaoKotlin.mostrarInscritos()
}

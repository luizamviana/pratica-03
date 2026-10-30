
package br.com.local.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import br.com.local.myapplication.screens.Cadastro
import br.com.local.myapplication.screens.Detalhes
import br.com.local.myapplication.screens.Sobre
import br.com.local.myapplication.screens.Usuario
import br.com.local.myapplication.screens.telainicial

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {

                var telaAtual by remember {
                    mutableStateOf("cadastro")
                }

                var receitaSelecionadaId by remember {
                    mutableStateOf<Int?>(null)
                }

                val listaUsuarios = remember {
                    mutableStateListOf<Usuario>()
                }

                when (telaAtual) {

                    "cadastro" -> Cadastro(
                        onVoltar = {
                            // Mantém o comportamento original.
                        },
                        onCadastrar = { usuario ->
                            listaUsuarios.add(usuario)
                            telaAtual = "inicio"
                        }
                    )

                    "inicio" -> telainicial(
                        onCadastrar = {
                            telaAtual = "cadastro"
                        },
                        onDetalhes = { id ->
                            receitaSelecionadaId = id
                            telaAtual = "detalhes"
                        },
                        onSobre = {
                            telaAtual = "sobre"
                        }
                    )

                    "detalhes" -> Detalhes(
                        receitaId = receitaSelecionadaId,
                        onVoltar = {
                            telaAtual = "inicio"
                        }
                    )

                    "sobre" -> Sobre(
                        onVoltar = {
                            telaAtual = "inicio"
                        }
                    )
                }
            }
        }
    }
}



package br.com.local.myapplication.screens

import android.content.ActivityNotFoundException
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun Sobre(
    onVoltar: () -> Unit
) {
    val contexto = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Sobre / Ajuda",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Meu Livro de Receitas",
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            text = "Finalidade: organizar e consultar receitas culinárias."
        )

        Text(text = "Como usar:")

        Text(text = "1. Na tela inicial, veja as receitas disponíveis.")

        Text(text = "2. Toque em uma receita para consultar seus detalhes.")

        Text(text = "3. Utilize o botão Cadastro para acessar o cadastro de usuário.")

        Text(text = "4. Utilize esta tela para consultar as informações do aplicativo.")

        Text(text = "Autores: coloque aqui o nome dos autores.")

        Button(
            onClick = onVoltar,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Voltar")
        }

        Button(
            onClick = {
                val textoCompartilhamento =
                    "Meu Livro de Receitas\n\n" +
                            "Aplicativo para organizar e consultar receitas culinárias."

                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(
                        Intent.EXTRA_TEXT,
                        textoCompartilhamento
                    )
                }

                try {
                    contexto.startActivity(
                        Intent.createChooser(
                            intent,
                            "Compartilhar aplicativo"
                        )
                    )
                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(
                        contexto,
                        "Nenhum aplicativo disponível para compartilhar.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Compartilhar aplicativo")
        }
    }
}

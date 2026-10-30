
package br.com.local.myapplication.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Detalhes(
    receitaId: Int?,
    onVoltar: () -> Unit
) {
    val receita = receitas.firstOrNull {
        it.id == receitaId
    }

    var etapasConcluidas by remember(receitaId) {
        mutableStateOf(setOf<Int>())
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = "Minha Receita",
            style = MaterialTheme.typography.headlineMedium
        )

        if (receita == null) {

            Text(
                text = "Receita não encontrada.",
                color = MaterialTheme.colorScheme.error
            )

        } else {

            Text(
                text = receita.nome,
                style = MaterialTheme.typography.headlineSmall
            )

            Text(
                text = "Categoria: ${receita.categoria}"
            )

            Text(
                text = "Tempo de preparo: ${receita.tempo}"
            )

            HorizontalDivider()

            // INGREDIENTES

            Text(
                text = "Ingredientes",
                style = MaterialTheme.typography.titleLarge
            )

            receita.ingredientes.forEach { ingrediente ->

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "•",
                        modifier = Modifier.padding(end = 8.dp)
                    )

                    Text(
                        text = ingrediente,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            HorizontalDivider()

            // MODO DE PREPARO

            Text(
                text = "Modo de preparo",
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = "Marque cada etapa depois de concluí-la."
            )

            Text(
                text = "Etapas concluídas: " +
                        "${etapasConcluidas.size} de ${receita.modoPreparo.size}"
            )

            LinearProgressIndicator(
                progress = {
                    if (receita.modoPreparo.isEmpty()) {
                        0f
                    } else {
                        etapasConcluidas.size.toFloat() /
                                receita.modoPreparo.size
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            receita.modoPreparo.forEachIndexed { index, etapa ->

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Checkbox(
                        checked = index in etapasConcluidas,
                        onCheckedChange = { marcado ->

                            etapasConcluidas =
                                if (marcado) {
                                    etapasConcluidas + index
                                } else {
                                    etapasConcluidas - index
                                }
                        }
                    )

                    Text(
                        text = "${index + 1}. $etapa",
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            if (
                etapasConcluidas.size ==
                receita.modoPreparo.size
            ) {
                Text(
                    text = "Parabéns! Você concluiu todas as etapas!",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onVoltar,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Voltar para receitas")
        }
    }
}
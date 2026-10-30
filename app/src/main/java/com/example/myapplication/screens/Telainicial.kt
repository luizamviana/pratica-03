
package br.com.local.myapplication.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class Receita(
    val id: Int,
    val nome: String,
    val categoria: String,
    val tempo: String,
    val ingredientes: List<String>,
    val modoPreparo: List<String>
)

val receitas = listOf(

    Receita(
        id = 1,
        nome = "Bolo de chocolate",
        categoria = "Sobremesa",
        tempo = "40 minutos",
        ingredientes = listOf(
            "2 xícaras de farinha de trigo",
            "1 xícara de açúcar",
            "1 xícara de chocolate em pó",
            "2 ovos",
            "1 xícara de leite",
            "1/2 xícara de óleo",
            "1 colher de sopa de fermento"
        ),
        modoPreparo = listOf(
            "Preaqueça o forno a 180 °C.",
            "Misture os ovos, o açúcar e o óleo.",
            "Acrescente o leite, a farinha e o chocolate em pó.",
            "Misture até formar uma massa homogênea.",
            "Adicione o fermento e misture delicadamente.",
            "Despeje a massa em uma forma untada.",
            "Asse por aproximadamente 35 a 40 minutos e verifique se está pronto com um palito."
        )
    ),

    Receita(
        id = 2,
        nome = "Panqueca",
        categoria = "Café da manhã",
        tempo = "20 minutos",
        ingredientes = listOf(
            "1 xícara de farinha de trigo",
            "1 xícara de leite",
            "1 ovo",
            "1 pitada de sal",
            "1 colher de chá de óleo para untar a frigideira"
        ),
        modoPreparo = listOf(
            "Coloque o leite, o ovo, a farinha e o sal em uma tigela.",
            "Misture até obter uma massa lisa.",
            "Aqueça uma frigideira levemente untada.",
            "Despeje uma pequena porção da massa na frigideira.",
            "Cozinhe até a parte de baixo firmar e vire a panqueca.",
            "Doure o outro lado e repita com o restante da massa."
        )
    ),

    Receita(
        id = 3,
        nome = "Salada de frutas",
        categoria = "Lanche",
        tempo = "10 minutos",
        ingredientes = listOf(
            "1 banana",
            "1 maçã",
            "1 fatia de mamão",
            "1 laranja",
            "Suco de 1/2 laranja"
        ),
        modoPreparo = listOf(
            "Lave bem as frutas.",
            "Descasque as frutas que desejar descascar.",
            "Corte as frutas em pedaços pequenos.",
            "Coloque todos os pedaços em uma tigela.",
            "Acrescente o suco de laranja e misture delicadamente.",
            "Sirva imediatamente ou mantenha refrigerada."
        )
    )
)

@Composable
fun telainicial(
    onCadastrar: () -> Unit,
    onDetalhes: (Int) -> Unit,
    onSobre: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Meu Livro de Receitas",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = onCadastrar) {
                Text("Cadastro")
            }

            Button(onClick = onSobre) {
                Text("Sobre")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Receitas",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(receitas) { receita ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onDetalhes(receita.id)
                        }
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = receita.nome,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text(text = "Categoria: ${receita.categoria}")
                        Text(text = "Tempo: ${receita.tempo}")

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Ver receita completa",
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
}
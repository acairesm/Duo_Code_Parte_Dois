package com.example.duocode.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.duocode.model.Linguagem
import com.example.duocode.model.Questao
import com.example.duocode.ui.screens.LinguagensScreen
import com.example.duocode.ui.screens.PerfilScreen
import com.example.duocode.ui.theme.DuoBg
import com.example.duocode.ui.theme.DuoBlue
import com.example.duocode.ui.theme.DuoCard
import com.example.duocode.ui.theme.DuoText
import com.example.duocode.ui.theme.DuoTextSecondary

private data class AbaNav(val rota: String, val label: String, val icone: androidx.compose.ui.graphics.vector.ImageVector)

private val abas = listOf(
    AbaNav(Rotas.TRILHA, "trilha", Icons.Default.Timeline),
    AbaNav(Rotas.LINGUAGENS, "linguagens", Icons.Default.Code),
    AbaNav(Rotas.QUESTOES, "questões", Icons.AutoMirrored.Filled.List),
    AbaNav(Rotas.PERFIL, "perfil", Icons.Default.Person)
)

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val linguagens = remember {
        mutableStateListOf(
            Linguagem(id = 1, nome = "JavaScript", sigla = "JS"),
            Linguagem(id = 2, nome = "Python", sigla = "PY"),
            Linguagem(id = 3, nome = "Kotlin", sigla = "KT"),
            Linguagem(id = 4, nome = "SQL", sigla = "SQL")
        )
    }
    val questoes = remember { mutableStateListOf<Questao>() }

    Scaffold(
        containerColor = DuoBg,
        bottomBar = {
            val backStackEntry by navController.currentBackStackEntryAsState()
            val rotaAtual = backStackEntry?.destination

            NavigationBar(containerColor = DuoCard) {
                abas.forEach { aba ->
                    val selecionada = rotaAtual?.hierarchy?.any { it.route == aba.rota } == true
                    NavigationBarItem(
                        selected = selecionada,
                        onClick = {
                            navController.navigate(aba.rota) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(aba.icone, contentDescription = aba.label) },
                        label = { Text(aba.label) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = DuoBlue,
                            selectedTextColor = DuoBlue,
                            unselectedIconColor = DuoTextSecondary,
                            unselectedTextColor = DuoTextSecondary,
                            indicatorColor = DuoBg
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Rotas.LINGUAGENS,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Rotas.TRILHA) { EmConstrucao() }
            composable(Rotas.LINGUAGENS) { LinguagensScreen(navController, linguagens, questoes) }
            composable(Rotas.QUESTOES) { EmConstrucao() }
            composable(Rotas.PERFIL) { PerfilScreen() }
            composable(Rotas.DETALHE_LINGUAGEM) { EmConstrucao() }
        }
    }
}

@Composable
private fun EmConstrucao() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DuoBg)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text("Em construção", color = DuoText)
    }
}

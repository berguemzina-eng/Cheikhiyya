package com.cheikhiyya.tariqa.nav

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cheikhiyya.tariqa.data.findArticle
import com.cheikhiyya.tariqa.ui.screens.ArticleScreen
import com.cheikhiyya.tariqa.ui.screens.DrawerContent
import com.cheikhiyya.tariqa.ui.screens.HadraScreen
import com.cheikhiyya.tariqa.ui.screens.HizbAlFalahScreen
import com.cheikhiyya.tariqa.ui.screens.HomeScreen
import com.cheikhiyya.tariqa.ui.screens.PendingScreen
import com.cheikhiyya.tariqa.ui.screens.YaqoutaScreen
import kotlinx.coroutines.launch
import java.net.URLDecoder
import java.net.URLEncoder

private fun encodeRoute(id: String): String =
    URLEncoder.encode(id, "UTF-8")

private fun decodeRoute(id: String): String =
    URLDecoder.decode(id, "UTF-8")

@Composable
fun RootNavigation() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navController: NavHostController = rememberNavController()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(onNavigate = { destination ->
                scope.launch { drawerState.close() }
                if (destination == "home" || destination == "hizb_al_falah" || destination == "hadra" || destination == "yaqouta") {
                    navController.navigate(destination)
                } else {
                    navController.navigate("article/${encodeRoute(destination)}")
                }
            })
        },
    ) {
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                HomeScreen(
                    onOpenDrawer = { scope.launch { drawerState.open() } },
                    onNavigate = { destination ->
                        if (destination == "home" || destination == "hizb_al_falah" || destination == "hadra" || destination == "yaqouta") {
                            navController.navigate(destination)
                        } else {
                            navController.navigate("article/${encodeRoute(destination)}")
                        }
                    },
                )
            }

            composable("hizb_al_falah") {
                HizbAlFalahScreen(onBack = { navController.popBackStack() })
            }

            composable("hadra") {
                HadraScreen(onBack = { navController.popBackStack() })
            }

            composable("yaqouta") {
                YaqoutaScreen(onBack = { navController.popBackStack() })
            }

            // Cualquier otra ruta: si es un artículo real lo mostramos,
            // si empieza por "pending:" mostramos la pantalla de aviso.
            composable("article/{articleId}") { backStackEntry ->
                val raw = backStackEntry.arguments?.getString("articleId").orEmpty()
                val id = decodeRoute(raw)
                if (id.startsWith("pending:")) {
                    PendingScreen(title = id.removePrefix("pending:"), onBack = { navController.popBackStack() })
                } else {
                    val article = findArticle(id)
                    if (article != null) {
                        ArticleScreen(article = article, onBack = { navController.popBackStack() })
                    } else {
                        PendingScreen(title = id, onBack = { navController.popBackStack() })
                    }
                }
            }
        }
    }
}

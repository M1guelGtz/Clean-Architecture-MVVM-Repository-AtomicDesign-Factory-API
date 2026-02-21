package com.m1guelgtz.templatecarsapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.m1guelgtz.templatecarsapi.Demo.Core.Di.AppConteiner
import com.m1guelgtz.templatecarsapi.Demo.Core.Ui.theme.TemplateCarsAPITheme
import com.m1guelgtz.templatecarsapi.Demo.Core.rutes.AppNavHost
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Di.BooksModule
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.Screens.LibraryScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    lateinit var appContainer: AppConteiner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appContainer= AppConteiner(this)
        enableEdgeToEdge()
        setContent {
            TemplateCarsAPITheme {
                AppNavHost(navController = rememberNavController(), appContainer)
            }
        }
    }
}
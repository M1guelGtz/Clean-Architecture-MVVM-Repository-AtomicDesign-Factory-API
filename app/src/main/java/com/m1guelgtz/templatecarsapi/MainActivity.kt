package com.m1guelgtz.templatecarsapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.m1guelgtz.templatecarsapi.Demo.Core.Di.AppConteiner
import com.m1guelgtz.templatecarsapi.Demo.Core.Ui.theme.TemplateCarsAPITheme
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Di.BooksModule
import com.m1guelgtz.templatecarsapi.Demo.Features.Library.Presentation.Screens.LibraryScreen

class MainActivity : ComponentActivity() {
    lateinit var appContainer: AppConteiner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appContainer= AppConteiner(this)
        val libraryModule = BooksModule(appContainer)
        enableEdgeToEdge()
        setContent {
            TemplateCarsAPITheme {
                LibraryScreen(libraryModule.provideBooksViewModelFactory())
            }
        }
    }
}